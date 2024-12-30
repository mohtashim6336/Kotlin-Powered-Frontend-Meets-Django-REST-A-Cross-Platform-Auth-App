package com.example.companyauthapp

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {
    @POST("api/v1/register/")
    suspend fun register(
        @Body request: RegisterRequest
    ): Response<AuthResponse>

    @POST("api/v1/login/")
    suspend fun login(
        @Body request: LoginRequest
    ): Response<AuthResponse>

    @POST("api/v1/forgot-password/")
    suspend fun forgotPassword(
        @Body request: ForgotPasswordRequest
    ): Response<MessageResponse>

    @GET("api/v1/user-data/")
    suspend fun getUserData(
        @Header("Authorization") token: String
    ): Response<UserResponse>
}