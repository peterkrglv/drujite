package com.example.domain.use_cases

import com.example.domain.repos.SharedPrefsRepository
import com.example.domain.repos.UserRepository

class SignupUseCase(
    private val repo: UserRepository,
    private val sharedPrefs: SharedPrefsRepository
) {
    suspend fun execute(name: String, phone: String, password: String, passwordRepeated: String): SignupResult {
        val phonePattern = "^\\+?\\d+$"
        if (!phone.matches(phonePattern.toRegex())) {
            return SignupResult.INVALID_PHONE
        }
        if (password.length < 6 || !password.any { it.isUpperCase() } || !password.any { it.isDigit() }) {
            return SignupResult.INVALID_PASSWORD
        }
        if (password != passwordRepeated) {
            return SignupResult.PASSWORDS_DO_NOT_MATCH
        }
        val responce = repo.signup(name, phone, password)
        val result = responce.result
        if (result == SignupResult.SUCCESS && responce.id != null) {
            sharedPrefs.saveUserId(responce.id)
        }
        return responce.result
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

