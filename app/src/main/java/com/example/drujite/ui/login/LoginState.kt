package com.example.drujite.ui.login

sealed class LoginState {
    data object Idle : LoginState()
    data object Loading : LoginState()
    data class Main(
        val phone: String = "",
        val password: String = "",
        val gender: String = "",
        val isError: Boolean = false
    )
}

sealed class LoginEvent {

}

sealed class LoginAction {

}