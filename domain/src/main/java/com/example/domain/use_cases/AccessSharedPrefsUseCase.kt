package com.example.domain.use_cases

import com.example.domain.repos.SharedPrefsRepository

class AccessSharedPrefsUseCase(private val repo: SharedPrefsRepository) {
    suspend fun saveUserToken(token: String) = repo.saveUserToken(token)
    suspend fun getUserToken() = repo.getUserToken()
    suspend fun saveSessionId(id: Int) = repo.saveSessionId(id)
    suspend fun getSessionId() = repo.getSessionId()
    suspend fun saveCharacterId(id: Int) = repo.saveCharacterId(id)
    suspend fun getCharacterId() = repo.getCharacterId()
    suspend fun deleteCharacterId() = repo.deleteCharacterId()
}