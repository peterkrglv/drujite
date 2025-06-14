package com.example.drujite.presentation.greeting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.use_cases.AccessSharedPrefsUseCase
import com.example.domain.use_cases.user.GetCurrentUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GreetingViewModel(
    private val prefsUseCase: AccessSharedPrefsUseCase,
    private val getCurrentUser: GetCurrentUser
): ViewModel()  {
    private val _viewState = MutableStateFlow<GreetingState>(GreetingState.Initialization)
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
        _viewState.value = GreetingState.Loading
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val userToken = prefsUseCase.getUserToken()
                val sessionId = prefsUseCase.getSessionId()
                val characterId = prefsUseCase.getCharacterId()
                if (userToken != null && sessionId != -1 && characterId != -1) {
                    val currentUser = getCurrentUser.execute()
                    if (currentUser == null) _viewState.value = GreetingState.Main
                    _viewAction.value = GreetingAction.NavigateToMainView(userToken, sessionId, characterId)
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