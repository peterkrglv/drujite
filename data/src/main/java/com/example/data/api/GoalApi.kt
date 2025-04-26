package com.example.data.api

import com.example.data.requests.IdRequest
import com.example.data.responces.GoalResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PUT
import retrofit2.http.Query

interface GoalApi {
    @GET("goal/character-all")
    suspend fun getGoals(
        @Header("Authorization") token: String,
        @Query("characterId") characterId: Int
    ): List<GoalResponse>
    @PUT("goal/complete")
    suspend fun completeGoal(
        @Header("Authorization") token: String,
        @Body goal: IdRequest
    )
}