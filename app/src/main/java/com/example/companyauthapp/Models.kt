// Models.kt
package com.example.companyauthapp

data class RegisterRequest(
    val username: String,
    val email: String,
    val password: String
)

data class LoginRequest(
    val username: String,
    val password: String
)

data class ForgotPasswordRequest(
    val email: String
)

data class AuthResponse(
    val access: String,
    val refresh: String
)

data class MessageResponse(
    val message: String
)

data class UserResponse(
    val username: String,
    val email: String
)