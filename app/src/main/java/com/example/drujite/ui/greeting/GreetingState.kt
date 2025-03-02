package com.example.drujite.ui.greeting

sealed class GreetingState {
    data object Main : GreetingState()
}

sealed class GreetingEvent {
    data object proceedButtonClicked : GreetingEvent()
}

sealed class GreetingAction {
    data object NavigateToLogin : GreetingAction()
}