package com.example.drujite.presentation.greeting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.use_cases.AccessSharedPrefsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GreetingViewModel(
    private val prefsUseCase: AccessSharedPrefsUseCase
): ViewModel()  {
    private val _viewState = MutableStateFlow<GreetingState>(GreetingState.Loading)
    val viewState: StateFlow<GreetingState>
        get() = _viewState
    private val _viewAction = MutableStateFlow<GreetingAction?>(null)
    val viewAction: StateFlow<GreetingAction?>
        get() = _viewAction

    fun obtainEvent(event: GreetingEvent) {
        when (event) {
            is GreetingEvent.ProceedButtonClicked -> proceedButtonClicked()
            is GreetingEvent.CheckIfUserIsLoggedIn -> checkIfUserIsLoggedIn()
        }
    }

    private fun checkIfUserIsLoggedIn() {
        _viewState.value = GreetingState.Idle
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                Thread.sleep(1000)
                val userId = prefsUseCase.getUserId()
                val sessionId = prefsUseCase.getSessionId()
                val characterId = prefsUseCase.getCharacterId()
                if (userId != -1 && sessionId != -1 && characterId != -1) {
                    _viewAction.value = GreetingAction.NavigateToMainView
                } else {
                    _viewState.value = GreetingState.Main
                }
            }
        }
    }

    fun clearAction() {
        _viewAction.value = null
    }

    private fun proceedButtonClicked() {
        _viewAction.value = GreetingAction.NavigateToLogin
    }
}