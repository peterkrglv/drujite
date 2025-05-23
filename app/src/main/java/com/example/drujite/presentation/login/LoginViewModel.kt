package com.example.drujite.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.use_cases.user.LoginResult
import com.example.domain.use_cases.user.LoginUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginViewModel(
    private val loginUseCase: LoginUseCase
) : ViewModel() {
    private val _viewState = MutableStateFlow<LoginState>(LoginState.Main())
    val viewState: StateFlow<LoginState>
        get() = _viewState
    private val _viewAction = MutableStateFlow<LoginAction?>(null)
    val viewAction: StateFlow<LoginAction?>
        get() = _viewAction

    fun obtainEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.PhoneChanged -> phoneChanged(event.phone)
            is LoginEvent.PasswordChanged -> passwordChanged(event.password)
            is LoginEvent.ProceedClicked -> proceedClicked()
            is LoginEvent.SignupClicked -> signupClicked()
        }
    }

    fun clearAction() {
        _viewAction.value = null
    }

    private fun signupClicked() {
        _viewAction.value = LoginAction.NavigateToSignup
    }

    private fun proceedClicked() {
        val state = _viewState.value
        if (state !is LoginState.Main) {
            return
        }
        _viewState.value = LoginState.Loading
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                Thread.sleep(2000)
                val loginReponse = loginUseCase.execute(state.phone, state.password)
                val userId = loginReponse.id
                if (loginReponse.result == LoginResult.SUCCESS && userId != null) {
                    _viewAction.value = LoginAction.NavigateToSessionSelection(userId)
                } else {
                    _viewState.value = state.copy(error = loginReponse.result)
                }
//                val loginResult = loginUseCase.execute(state.phone, state.password)
//                if (loginResult == LoginResult.SUCCESS) {
//                    _viewAction.value = LoginAction.NavigateToSessionSelection
//                } else {
//                    _viewState.value = state.copy(error = loginResult)
//                }
            }
        }
    }

    private fun passwordChanged(password: String) {
        val state = _viewState.value
        if (state is LoginState.Main) {
            _viewState.value = state.copy(password = password, error = null)
        }
    }

    private fun phoneChanged(phone: String) {
        val state = _viewState.value
        if (state is LoginState.Main) {
            _viewState.value = state.copy(phone = phone, error = null)
        }
    }
}