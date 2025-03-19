package com.example.drujite.presentation.greeting

sealed class GreetingState {
    data object Main : GreetingState()
    data object Loading: GreetingState()
    data object Initialization: GreetingState()
}

sealed class GreetingEvent {
    data object ProceedButtonClicked : GreetingEvent()
    data object CheckIfUserIsLoggedIn : GreetingEvent()
}

sealed class GreetingAction {
    data object NavigateToLogin : GreetingAction()
    data class NavigateToMainView(val userId: Int, val sessionId: Int, val characterId: Int): GreetingAction()
}