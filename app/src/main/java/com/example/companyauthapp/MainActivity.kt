package com.example.companyauthapp
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.companyauthapp.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val scope = CoroutineScope(Dispatchers.Main + Job())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupListeners()
    }

    private fun setupListeners() {
        binding.btnRegister.setOnClickListener { register() }
        binding.btnLogin.setOnClickListener { login() }
        binding.btnForgotPassword.setOnClickListener { forgotPassword() }
        binding.btnGetUserData.setOnClickListener { getUserData() }
    }

    private fun register() {
        val username = binding.etUsername.text.toString()
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()

        if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
            showToast("All fields are required")
            return
        }

        scope.launch {
            try {
                val response = ApiClient.api.register(
                    RegisterRequest(username, email, password)
                )
                if (response.isSuccessful) {
                    val token = response.body()?.access
                    saveToken(token)
                    showToast("Registration successful!")
                } else {
                    showToast("Registration failed: ${response.errorBody()?.string() ?: "Unknown error"}")
                }
            } catch (e: Exception) {
                showToast("Error: ${e.message}")
            }
        }
    }

    private fun login() {
        val username = binding.etUsername.text.toString()
        val password = binding.etPassword.text.toString()

        if (username.isEmpty() || password.isEmpty()) {
            showToast("All fields are required")
            return
        }

        scope.launch {
            try {
                val response = ApiClient.api.login(
                    LoginRequest(username, password)
                )
                if (response.isSuccessful) {
                    val token = response.body()?.access
                    saveToken(token)
                    showToast("Login successful!")
                } else {
                    showToast("Login failed: ${response.errorBody()?.string() ?: "Unknown error"}")
                }
            } catch (e: Exception) {
                showToast("Error: ${e.message}")
            }
        }
    }

    private fun forgotPassword() {
        val email = binding.etEmail.text.toString()

        if (email.isEmpty()) {
            showToast("Email is required")
            return
        }

        scope.launch {
            try {
                val response = ApiClient.api.forgotPassword(
                    ForgotPasswordRequest(email)
                )
                if (response.isSuccessful) {
                    showToast("Password reset email sent!")
                } else {
                    showToast("Failed to send reset email: ${response.errorBody()?.string() ?: "Unknown error"}")
                }
            } catch (e: Exception) {
                showToast("Error: ${e.message}")
            }
        }
    }

    private fun getUserData() {
        val token = getToken()
        if (token == null) {
            showToast("Please login first")
            return
        }

        scope.launch {
            try {
                val response = ApiClient.api.getUserData("Bearer $token")
                if (response.isSuccessful) {
                    val user = response.body()
                    showToast("User data: ${user?.username}, ${user?.email}")
                } else {
                    showToast("Failed to get user data: ${response.errorBody()?.string() ?: "Unknown error"}")
                }
            } catch (e: Exception) {
                showToast("Error: ${e.message}")
            }
        }
    }

    private fun saveToken(token: String?) {
        getSharedPreferences("auth", MODE_PRIVATE)
            .edit()
            .putString("token", token)
            .apply()
    }

    private fun getToken(): String? {
        return getSharedPreferences("auth", MODE_PRIVATE)
            .getString("token", null)
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
