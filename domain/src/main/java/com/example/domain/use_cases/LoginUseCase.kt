package com.example.domain.use_cases

import com.example.domain.repos.UserRepository

class LoginUseCase(
    private val repo: UserRepository
) {
    suspend fun execute(phone: String, password: String): LoginResult {
        val phonePattern = "^\\+?\\d+$"
        if (!phone.matches(phonePattern.toRegex())) {
            return LoginResult.INVALID_PHONE
        }
        val result = repo.login(phone, password)
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