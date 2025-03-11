package com.example.domain.use_cases

import com.example.domain.repos.SharedPrefsRepository
import com.example.domain.repos.UserRepository

class SignupUseCase(
    private val repo: UserRepository,
    private val sharedPrefs: SharedPrefsRepository
) {
    suspend fun execute(name: String, phone: String, password: String, passwordRepeated: String, gender: String): SignupResponse {
        val phonePattern = "^\\+?\\d+$"
        if (!phone.matches(phonePattern.toRegex())) {
            return SignupResponse(SignupResult.INVALID_PHONE)
        }
        if (password.length < 6 || !password.any { it.isUpperCase() } || !password.any { it.isDigit() }) {
            return SignupResponse(SignupResult.INVALID_PASSWORD)
        }
        if (password != passwordRepeated) {
            return SignupResponse(SignupResult.PASSWORDS_DO_NOT_MATCH)
        }
        val response = repo.signup(name, phone, password, gender)
        val result = response.result
        if (result == SignupResult.SUCCESS && response.id != null) {
            sharedPrefs.saveUserId(response.id)
        }
        return response
    }
}

enum class SignupResult(val message: String) {
    SUCCESS(""),
    INVALID_PHONE("Введите номер в формате +7XXXXXXXXXX"),
    PHONE_ALREADY_REGISTERED("Пользователь с таким номером уже существует"),
    INVALID_PASSWORD("Слабый пароль"),
    PASSWORDS_DO_NOT_MATCH("Пароли не совпадают"),
    NETWORK_ERROR("Ошибка сети"),
}

data class SignupResponse(
    val result: SignupResult,
    val id: Int? = null
)