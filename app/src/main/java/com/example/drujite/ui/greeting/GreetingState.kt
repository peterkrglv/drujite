package com.example.drujite.ui.greeting

import com.example.drujite.ui.login.LoginState

sealed class GreetingState {
    data object Idle : GreetingState()
    data object Loading : GreetingState()
}

sealed class GreetingEvent {

}

sealed class GreetingAction {

}