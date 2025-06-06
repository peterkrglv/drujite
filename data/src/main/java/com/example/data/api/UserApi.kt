package com.example.data.api

import com.example.data.requests.LoginRequest
import com.example.data.requests.SignupRequest
import com.example.data.responces.TokenResponse
import com.example.data.responces.UserResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface UserApi {
    @POST("auth")
    suspend fun login(@Body request: LoginRequest): TokenResponse

    @POST("signup")
    suspend fun signup(@Body request: SignupRequest): TokenResponse

    @GET("user/me")
    suspend fun getUserByToken(@Header("Authorization") token: String): UserResponse
}