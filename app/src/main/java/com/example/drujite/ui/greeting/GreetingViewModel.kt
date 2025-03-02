package com.example.drujite.ui.greeting

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class GreetingViewModel: ViewModel()  {
    private val _viewState = MutableStateFlow<GreetingState>(GreetingState.Main)
    val viewState: StateFlow<GreetingState>
        get() = _viewState
    private val _viewAction = MutableStateFlow<GreetingAction?>(null)
    val viewAction: StateFlow<GreetingAction?>
        get() = _viewAction

    fun obtainEvent(event: GreetingEvent) {
        when (event) {
            is GreetingEvent.proceedButtonClicked -> proceedButtonClicked()
        }
    }

    fun resetAction() {
        _viewAction.value = null
    }

    private fun proceedButtonClicked() {
        _viewAction.value = GreetingAction.NavigateToLogin
    }
}