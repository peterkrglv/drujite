package com.example.drujite.presentation.login

import com.example.domain.use_cases.user.LoginResult

sealed class LoginState {
    data object Loading : LoginState()
    data class Main(
        val phone: String = "",
        val password: String = "",
        val error: LoginResult? = null
    ): LoginState()
}

sealed class LoginEvent {
    data class PhoneChanged(val phone: String) : LoginEvent()
    data class PasswordChanged(val password: String) : LoginEvent()
    data object ProceedClicked : LoginEvent()
    data object SignupClicked : LoginEvent()
}

sealed class LoginAction {
    data object NavigateToSignup: LoginAction()
    data class NavigateToSessionSelection(val userToken: String) : LoginAction()
}

