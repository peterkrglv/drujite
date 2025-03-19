package com.example.drujite.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.use_cases.GetCharactersByUserIdUseCase
import com.example.domain.use_cases.GetCurrentUser
import com.example.domain.use_cases.GetSessionsOfUserUseCase
import com.example.domain.use_cases.LogOutUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProfileViewModel (
    private val getCurrentUser: GetCurrentUser,
    private val logOutUseCase: LogOutUseCase,
    private val getSessionsOfUserUseCase: GetSessionsOfUserUseCase,
    private val getCharactersByUserIdUseCase: GetCharactersByUserIdUseCase
) : ViewModel() {
    private val _viewState = MutableStateFlow<ProfileState>(ProfileState.Initialization)
    val viewState: StateFlow<ProfileState>
        get() = _viewState
    private val _viewAction = MutableStateFlow<ProfileAction?>(null)
    val viewAction: StateFlow<ProfileAction?>
        get() = _viewAction

    fun obtainEvent(event: ProfileEvent) {
        when (event) {
            is ProfileEvent.LoadData -> loadData()
            is ProfileEvent.LogOut -> logOut()
        }
    }
    fun clearAction() {
        _viewAction.value = null
    }

    private fun logOut() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                logOutUseCase.execute()
                _viewAction.value = ProfileAction.NavigateToGreeting
            }
        }

    }

    private fun loadData() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val user = getCurrentUser.execute()
                val sessions = getSessionsOfUserUseCase.execute(user.id)
                val characters = getCharactersByUserIdUseCase.execute(user.id)
                _viewState.value = ProfileState.Main(user, sessions, characters)
            }
        }
    }
}