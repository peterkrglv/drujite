package com.example.drujite.ui.signup

import com.example.drujite.domain.SignupResult

sealed class SignupState {
    data object Loading : SignupState()
    data class Main(
        val name: String = "",
        val phone: String = "",
        val password: String = "",
        val passwordRepeated: String = "",
        val gender: Gender = Gender.MALE,
        val error: SignupResult? = null
    ) : SignupState()
}

sealed class SignupEvent {
    data class NameChanged(val name: String) : SignupEvent()
    data class PhoneChanged(val phone: String) : SignupEvent()
    data class PasswordChanged(val password: String) : SignupEvent()
    data class PasswordRepeatedChanged(val passwordRepeated: String) : SignupEvent()
    data class GenderChanged(val gender: Gender) : SignupEvent()
    data object ProceedClicked : SignupEvent()
    data object LoginClicked : SignupEvent()
}

sealed class SignupAction {
    data object NavigateToLogin: SignupAction()
    data object NavigateToSessionSelection: SignupAction()
}

enum class Gender(val value: String) {
    MALE(value = "М"),
    FEMALE(value = "Ж")
}