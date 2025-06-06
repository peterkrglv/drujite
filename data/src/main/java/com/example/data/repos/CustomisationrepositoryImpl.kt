package com.example.data.repos

import android.util.Log
import com.example.data.api.CustomisationApi
import com.example.data.baseUrl
import com.example.data.requests.SaveCharactersClothingRequest
import com.example.domain.repos.CustomisationRepository
import com.example.domain.repos.SharedPrefsRepository
import com.example.domain.use_cases.customisation.CustomisationCategory
import com.example.domain.use_cases.customisation.CustomisationOption
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody

class CustomisationrepositoryImpl(
    private val api: CustomisationApi,
    private val sharedPrefs: SharedPrefsRepository
) : CustomisationRepository {
    override suspend fun getCustomisationOptions(): List<CustomisationCategory> {
        try {
            val token = sharedPrefs.getUserToken()
            val options = api.getCustomisationOptions(token = "Bearer $token")
            val categories = options.map {
                CustomisationCategory(
                    id = it.id,
                    name = it.name,
                    items = it.items.map { item ->
                        CustomisationOption(
                            id = item.id,
                            typeId = item.typeId,
                            imageUrl = baseUrl + item.imageUrl,
                            iconUrl = baseUrl + item.iconUrl,
                        )
                    }
                )
            }
            Log.d("server", "Fetched customisation options: ${options.size}")
            return categories
        } catch (e: Exception) {
            Log.e("server", "Error fetching customisation options: ${e.message}")
            return emptyList()
        }
    }

    override suspend fun getCharactersItems(characterId: Int): List<CustomisationOption> {
        try {
            val token = sharedPrefs.getUserToken()
            val items = api.getCharactersItems(
                characterId = characterId,
                token = "Bearer $token"
            )
            val chatacterItems = items.map {
                CustomisationOption(
                    id = it.id,
                    typeId = it.typeId,
                    imageUrl = baseUrl + it.imageUrl,
                    iconUrl = baseUrl + it.iconUrl,
                )
            }
            Log.d("server", "Fetched character items: ${items.size}")
            return chatacterItems
        } catch (e: Exception) {
            Log.e("server", "Error fetching character items: ${e.message}")
            return emptyList()
        }
    }

    override suspend fun saveCharactersItems(
        characterId: Int,
        itemsIds: List<Int>
    ): Boolean {
        try {
            val token = sharedPrefs.getUserToken()
            Log.d("saveCharactersItems", "Request: characterId=$characterId, itemsIds=$itemsIds")

            val result = api.saveCharactersItems(
                request = SaveCharactersClothingRequest(
                    characterId = characterId,
                    itemsIds = itemsIds,
                ),
                token = "Bearer $token"
            )

            Log.d("saveCharactersItems", "Response: $result")
            return true
        } catch (e: Exception) {
            Log.e("saveCharactersItems", "Error: ${e.message}", e)
            return false
        }
    }

    override suspend fun saveCharactersImage(characterId: Int, image: ByteArray): Boolean {
        try {
            val token = sharedPrefs.getUserToken()
            val requestBody = image.toRequestBody("application/octet-stream".toMediaTypeOrNull())
            val result = api.uploadCharacterImage(
                token = "Bearer $token",
                characterId = characterId,
                image = requestBody
            )
            Log.d("server", "Uploaded character image: $result")
            return true
        } catch (e: Exception) {
            Log.e("server", "Error uploading character image: ${e.message}")
            return false
        }
    }
}