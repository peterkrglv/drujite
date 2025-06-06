package com.example.data.repos

import android.util.Log
import com.example.data.api.ClanApi
import com.example.data.responces.ClanResponse
import com.example.domain.models.ClanModel
import com.example.domain.repos.ClanRepository
import com.example.domain.repos.SharedPrefsRepository

class ClanRepositoryImpl(
    private val clanApi: ClanApi,
    private val sharedPrefs: SharedPrefsRepository
) : ClanRepository {
    override suspend fun getClans(sessionId: Int): List<ClanModel> {
        try {
            val token = sharedPrefs.getUserToken() ?: return emptyList()
            val response = clanApi.getClans("Bearer $token", sessionId)
            Log.d("server", "Fetched clans: ${response.size}")
            return response.map {
                it.toModel()
            }
        } catch (e: Exception) {
            Log.e("server", "Error fetching clans: ${e.message}")
            return emptyList()
        }
    }
}

private fun ClanResponse.toModel() =
    ClanModel(
        id = id,
        name = name,
        description = description
    )
