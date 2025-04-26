package com.example.data.repos

import android.util.Log
import com.example.data.api.CharacterApi
import com.example.data.requests.AddCharacterRequest
import com.example.data.responces.CharacterResponse
import com.example.domain.models.CharacterModel
import com.example.domain.repos.CharacterRepository
import com.example.domain.repos.SharedPrefsRepository

class CharacterRepositoryImpl(
    private val characterApi: CharacterApi,
    private val sharedPrefs: SharedPrefsRepository
) : CharacterRepository {

    override suspend fun addCharacter(name: String, story: String, clanId: Int): Int? {
        try {
            val token = sharedPrefs.getUserToken() ?: return null
            val response = characterApi.addCharacter(
                token = "Bearer $token",
                request = AddCharacterRequest(name, story, clanId)
            )
            Log.d("server", "Character added with ID: ${response.id}")
            return response.id
        } catch (e: Exception) {
            Log.e("server", "Error adding character: ${e.message}")
            return null
        }
    }

    override suspend fun getUsersCharacters(userToken: String): List<CharacterModel> {
        return try {
            val token = sharedPrefs.getUserToken() ?: return emptyList()
            val response = characterApi.getUsersCharacters("Bearer $token")
            Log.d("server", "Fetched characters: ${response.size}")
            response.map {
                it.toModel()
            }
        } catch (e: Exception) {
            Log.e("server", "Error fetching characters: ${e.message}")
            emptyList()
        }
    }

    override suspend fun getCharacterById(characterId: Int): CharacterModel? {
        Log.d("server", "Fetching character by ID: $characterId")
        return try {
            val token = sharedPrefs.getUserToken() ?: return null
            val response = characterApi.getCharacter(
                token = "Bearer $token",
                sessionId = characterId
            )
            Log.d("server", "Fetched character: ${response.name}")
            response.toModel()
        } catch (e: Exception) {
            Log.e("server", "Error fetching character: ${e.message}")
            null
        }
    }

    override suspend fun getCharacterBySessionId(sessionId: Int): CharacterModel? {
        Log.d("server", "Fetching character by session ID: $sessionId")
        return try {
            val token = sharedPrefs.getUserToken() ?: return null
            val response = characterApi.getCharacterBySessionId(
                token = "Bearer $token",
                sessionId = sessionId
            )
            Log.d("server", "Fetched character: ${response.name}")
            response.toModel()
        } catch (e: Exception) {
            Log.e("server", "Error fetching character: ${e.message}")
            null
        }
    }

    override suspend fun getCharactersBySessionId(sessionId: Int): List<CharacterModel> {
        return try {
            val token = sharedPrefs.getUserToken() ?: return emptyList()
            val response = characterApi.getCharactersBySessionId(
                token = "Bearer $token",
                sessionId = sessionId
            )
            Log.d("server", "Fetched characters by session ID: ${response.size}")
            response.map {
                it.toModel()
            }
        } catch (e: Exception) {
            Log.e("server", "Error fetching characters by session ID: ${e.message}")
            emptyList()
        }
    }
}

fun CharacterResponse.toModel(): CharacterModel {
    return CharacterModel(
        id = this.id,
        name = this.name,
        player = this.player,
        story = this.story,
        clan = this.clan,
        imageUrl = this.imageUrl
    )
}