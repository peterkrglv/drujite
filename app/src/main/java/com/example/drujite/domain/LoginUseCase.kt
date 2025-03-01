package com.example.drujite.domain

class LoginUseCase(
    private val repo: UserRepository
) {
    suspend fun execute(phone: String, password: String): LoginResult {
        val phonePattern = "^\\+?\\d+$"
        if (!phone.matches(phonePattern.toRegex())) {
            return LoginResult.INVALID_PHONE
        }
        val result = repo.login(phone, password)
        return if (result) {
            LoginResult.SUCCESS
        } else {
            LoginResult.WRONG_PHONE_OR_PASSWORD
        }
    }
}

enum class LoginResult {
    SUCCESS,
    INVALID_PHONE,
    WRONG_PHONE_OR_PASSWORD,
    NETWORK_ERROR,
}