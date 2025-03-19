package com.example.data

import android.content.Context
import android.content.SharedPreferences
import com.example.domain.repos.SharedPrefsRepository

class SharedPrefsRepositoryImpl(context: Context) : SharedPrefsRepository {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)

    override suspend fun saveUserId(id: Int): Boolean {
        return sharedPreferences.edit().putInt("user_id", id).commit()
    }

    override suspend fun getUserId(): Int {
        return sharedPreferences.getInt("user_id", -1)
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