package com.example.data.api

import com.example.data.requests.AddSessionByQRRequest
import com.example.data.requests.AddUsersCharacterToSession
import com.example.data.requests.GetTimetableBySessionAndDate
import com.example.data.requests.IdRequest
import com.example.data.responces.EventResponse
import com.example.data.responces.SessionResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface SessionApi {
    @GET("session")
    suspend fun getSession(
        @Query("id") id: Int,
        @Header("Authorization") token: String
    ): SessionResponse

    @GET("users-sessions")
    suspend fun getAllSessions(
        @Header("Authorization") token: String
    ): List<SessionResponse>

    @POST("users-characters")
    suspend fun add(
        @Body request: AddUsersCharacterToSession,
        @Header("Authorization") token: String
    )

    @POST("event/session-date")
    suspend fun getTimetable(
        @Body request: GetTimetableBySessionAndDate,
        @Header("Authorization") authorization: String,
    ): List<EventResponse>

    @POST("users-sessions/qr")
    suspend fun addSessionByQr(
        @Body request: AddSessionByQRRequest,
        @Header("Authorization") token: String
    ): SessionResponse
}