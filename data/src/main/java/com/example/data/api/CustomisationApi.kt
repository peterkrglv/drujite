package com.example.data.api

import com.example.data.requests.SaveCharactersClothingRequest
import com.example.domain.use_cases.customisation.CustomisationCategory
import com.example.domain.use_cases.customisation.CustomisationOption
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface CustomisationApi {
    @GET("clothing-item/all")
    suspend fun getCustomisationOptions(
        @Header("Authorization") token: String
    ): List<CustomisationCategory>

    @GET("characters-clothing")
    suspend fun getCharactersItems(
        @Header("Authorization") token: String,
        @Query("id") characterId: Int
    ): List<CustomisationOption>

    @POST("characters-clothing")
    suspend fun saveCharactersItems(
        @Header("Authorization") token: String,
        @Body request: SaveCharactersClothingRequest
    )

    @POST("images/characters/{characterId}")
    suspend fun uploadCharacterImage(
        @Header("Authorization") token: String,
        @Path("characterId") characterId: Int,
        @Body image: RequestBody
    )
}