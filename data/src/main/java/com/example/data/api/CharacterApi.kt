package com.example.data.api

import com.example.data.requests.AddCharacterRequest
import com.example.data.responces.CharacterResponse
import com.example.data.responces.IdResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface CharacterApi {
    @POST("character")
    suspend fun addCharacter(
        @Header("Authorization") token: String,
        @Body request: AddCharacterRequest
    ): IdResponse

    @GET("users-characters/user-all")
    suspend fun getUsersCharacters(
        @Header("Authorization") token: String
    ): List<CharacterResponse>

    @GET("character/with-clan-and-player")
    suspend fun getCharacter(
        @Header("Authorization") token: String,
        @Query("id") sessionId: Int
    ): CharacterResponse

    @GET("users-characters")
    suspend fun getCharacterBySessionId(
        @Header("Authorization") token: String,
        @Query("sessionId") sessionId: Int
    ): CharacterResponse

    @GET("users-characters/session-all")
    suspend fun getCharactersBySessionId(
        @Header("Authorization") token: String,
        @Query("sessionId") sessionId: Int
    ): List<CharacterResponse>
}