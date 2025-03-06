package com.example.domain.use_cases

import com.example.domain.repos.SharedPrefsRepository
import com.example.domain.repos.UserRepository

class LoginUseCase(
    private val repo: UserRepository,
    private val sharedPrefs: SharedPrefsRepository
) {
    suspend fun execute(phone: String, password: String): LoginResult {
        val phonePattern = "^\\+?\\d+$"
        if (!phone.matches(phonePattern.toRegex())) {
            LoginResult.INVALID_PHONE
        }
        val response = repo.login(phone, password)
        val result = response.result
        if (result == LoginResult.SUCCESS && response.id != null) {
            sharedPrefs.saveUserId(response.id)
        }
        return result
    }
}

enum class LoginResult(val message: String) {
    SUCCESS(""),
    INVALID_PHONE("Введите номер в формате +7XXXXXXXXXX"),
    WRONG_PHONE("Пользователя с таким номером телефона не существует"),
    WRONG_PASSWORD("Неверный пароль"),
    NETWORK_ERROR("Ошибка сети,"),
}

data class LoginResponse(
    val result: LoginResult,
    val id: Int? = null
)