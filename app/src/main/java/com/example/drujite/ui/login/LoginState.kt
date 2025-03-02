package com.example.drujite.ui.login

import com.example.drujite.domain.LoginResult

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
    data object NavigateToSessionSelection: LoginAction()
}

