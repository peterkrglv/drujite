package com.example.drujite.domain

class SignupUseCase(
    private val repo: UserRepository
) {
    suspend fun execute(name: String, phone: String, password: String, passwordRepeated: String): SignupResult {
        val phonePattern = "^\\+?\\d+$"
        if (!phone.matches(phonePattern.toRegex())) {
            return SignupResult.INVALID_PHONE
        }
        if (password.length < 6) {
            return SignupResult.INVALID_PASSWORD
        }
        if (password != passwordRepeated) {
            return SignupResult.PASSWORDS_DO_NOT_MATCH
        }
        val result = repo.signup(name, phone, password)
        return result
    }
}

enum class SignupResult(val message: String) {
    SUCCESS(""),
    INVALID_PHONE("Введите номер в формате +7XXXXXXXXXX"),
    PHONE_ALREADY_REGISTERED("Пользователь с таким номером уже существует"),
    INVALID_PASSWORD("Пароль должен содержать не менее 6 символов"),
    PASSWORDS_DO_NOT_MATCH("Пароли не совпадают"),
    NETWORK_ERROR("Ошибка сети"),
}
