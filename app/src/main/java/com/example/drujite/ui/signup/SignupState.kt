package com.example.drujite.ui.signup

sealed class SignupState {
    data object Idle : SignupState()
    data object Loading : SignupState()
    data class Main(
        val phone: String = "",
        val password: String = "",
        val passwordRepeated: String = "",
        val gender: String = "",
        val isError: Boolean = false
    )
}

sealed class SignupEvent {
    data class PhoneChanged(val phone: String) : SignupEvent()
    data class PasswordChanged(val password: String) : SignupEvent()
    data class PasswordRepeatedChanged(val passwordRepeated: String) : SignupEvent()
    data class GenderChanged(val gender: String) : SignupEvent()
    data object ProceedClicked : SignupEvent()
    data object LoginClicked : SignupEvent()
}

sealed class SignupAction {
    data object NavigateToLogin: SignupAction()
    data object NavigateToSessionSelection: SignupAction()
}