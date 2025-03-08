package com.example.drujite.ui.greeting

sealed class GreetingState {
    data object Main : GreetingState()
    data object Loading: GreetingState()
    data object Idle: GreetingState()
}

sealed class GreetingEvent {
    data object ProceedButtonClicked : GreetingEvent()
    data object CheckIfUserIsLoggedIn : GreetingEvent()
}

sealed class GreetingAction {
    data object NavigateToLogin : GreetingAction()
    data object NavigateToMainView: GreetingAction()
}