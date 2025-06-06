package com.example.drujite.presentation.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.use_cases.user.SignupResult
import com.example.domain.use_cases.user.SignupUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SignupViewModel(
    private val signupUseCase: SignupUseCase
) : ViewModel() {
    private val _viewState = MutableStateFlow<SignupState>(SignupState.Main())
    val viewState: StateFlow<SignupState>
        get() = _viewState
    private val _viewAction = MutableStateFlow<SignupAction?>(null)
    val viewAction: StateFlow<SignupAction?>
        get() = _viewAction


    fun obtainEvent(event: SignupEvent) {
        when (event) {
            is SignupEvent.NameChanged -> nameChanged(event.name)
            is SignupEvent.PhoneChanged -> phoneChanged(event.phone)
            is SignupEvent.PasswordChanged -> passwordChanged(event.password)
            is SignupEvent.PasswordRepeatedChanged -> passwordRepeatedChanged(event.passwordRepeated)
            is SignupEvent.GenderChanged -> genderChanged(event.gender)
            is SignupEvent.ProceedClicked -> proceedClicked()
            is SignupEvent.LoginClicked -> loginClicked()
        }
    }

    fun clearAction() {
        _viewAction.value = null
    }

    private fun loginClicked() {
        _viewAction.value = SignupAction.NavigateToLogin
    }

    private fun proceedClicked() {
        val state = _viewState.value
        if (state !is SignupState.Main) {
            return
        }
        _viewState.value = SignupState.Loading
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val signupResponse = signupUseCase.execute(
                    state.name,
                    state.phone,
                    state.password,
                    state.passwordRepeated,
                    state.gender.value
                )
                val signupResult = signupResponse.result
                val userToken = signupResponse.token
                if (signupResult == SignupResult.SUCCESS && userToken != null) {
                    _viewAction.value = SignupAction.NavigateToSessionSelection(userToken)
                } else {
                    _viewState.value = state.copy(error = signupResult)
                }
            }
        }
    }

    private fun nameChanged(name: String) {
        val state = _viewState.value
        if (state is SignupState.Main) {
            _viewState.value = state.copy(name = name, error = null)
        }
    }

    private fun phoneChanged(phone: String) {
        val state = _viewState.value
        if (state is SignupState.Main) {
            _viewState.value = state.copy(phone = phone, error = null)
        }
    }

    private fun passwordChanged(password: String) {
        val state = _viewState.value
        if (state is SignupState.Main) {
            _viewState.value = state.copy(password = password, error = null)
        }
    }

    private fun passwordRepeatedChanged(passwordRepeated: String) {
        val state = _viewState.value
        if (state is SignupState.Main) {
            _viewState.value = state.copy(passwordRepeated = passwordRepeated, error = null)
        }
    }

    private fun genderChanged(gender: Gender) {
        val state = _viewState.value
        if (state is SignupState.Main) {
            _viewState.value = state.copy(gender = gender, error = null)
        }
    }
}