package com.example.domain.repos

interface SharedPrefsRepository {
    suspend fun saveUserToken(token: String): Boolean
    suspend fun getUserToken(): String?
    suspend fun saveSessionId(id: Int): Boolean
    suspend fun getSessionId(): Int
    suspend fun saveCharacterId(id: Int): Boolean
    suspend fun getCharacterId(): Int
    suspend fun deleteCharacterId(): Boolean
    suspend fun clearAll(): Boolean
}