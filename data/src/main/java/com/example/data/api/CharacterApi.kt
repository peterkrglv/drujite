package com.example.data.api

import com.example.data.requests.AddCharacterRequest
import com.example.data.requests.IdRequest
import com.example.data.responces.CharacterResponse
import com.example.data.responces.IdResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface CharacterApi {
    @POST("character")
    suspend fun addCharacter(
        @Body request: AddCharacterRequest,
        @Header("Authorization") token: String
    ): IdResponse

    @GET("users-characters/user-all")
    suspend fun getUsersCharacters(
        @Header("Authorization") token: String
    ): List<CharacterResponse>

    //is currently get and wont work with a body
    @GET("character")
    suspend fun getCharacter(
        @Body request: IdRequest,
        @Header("Authorization") token: String
    )

    //same as previous
    @GET("users-characters/session-all")
    suspend fun getCharacterBySessionId(
        @Body request: IdRequest,
        @Header("Authorization") token: String
    )
}