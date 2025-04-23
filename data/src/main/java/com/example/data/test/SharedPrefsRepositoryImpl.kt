package com.example.data.test

import android.content.Context
import android.content.SharedPreferences
import com.example.domain.repos.SharedPrefsRepository

class SharedPrefsRepositoryImpl(context: Context) : SharedPrefsRepository {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)

    override suspend fun saveUserToken(token: String): Boolean {
        return sharedPreferences.edit().putString("user_token", token).commit()
    }

    override suspend fun getUserToken(): String? {
        return sharedPreferences.getString("user_token", "")
    }

    override suspend fun saveSessionId(id: Int): Boolean {
        return sharedPreferences.edit().putInt("session_id", id).commit()
    }

    override suspend fun getSessionId(): Int {
        return sharedPreferences.getInt("session_id", -1)
    }

    override suspend fun saveCharacterId(id: Int): Boolean {
        return sharedPreferences.edit().putInt("character_id", id).commit()
    }

    override suspend fun getCharacterId(): Int {
        return sharedPreferences.getInt("character_id", -1)
    }

    override suspend fun clearAll(): Boolean {
        return sharedPreferences.edit().clear().commit()
    }
}