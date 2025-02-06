package com.example.drujite.ui.signup

sealed class SignupState {
    data object Idle : SignupState()
    data object Loading : SignupState()
    data class Main(
        val phone: String = "",
        val password: String = "",
        val gender: String = "",
        val isError: Boolean = false
    )
}

sealed class SignupEvent {

}

sealed class SignupAction {

}