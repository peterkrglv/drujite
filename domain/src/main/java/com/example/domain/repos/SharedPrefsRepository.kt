package com.example.domain.repos

interface SharedPrefsRepository {
    suspend fun saveUserId(id: Int): Boolean
    suspend fun getUserId(): Int
    suspend fun saveSessionId(id: Int): Boolean
    suspend fun getSessionId(): Int
    suspend fun saveCharacterId(id: Int): Boolean
    suspend fun getCharacterId(): Int
}