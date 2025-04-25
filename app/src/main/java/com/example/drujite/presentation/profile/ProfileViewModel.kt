package com.example.drujite.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.use_cases.character.GetUsersCharactersUseCase
import com.example.domain.use_cases.session.GetUsersSessionsUseCase
import com.example.domain.use_cases.user.GetCurrentUser
import com.example.domain.use_cases.user.LogOutUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProfileViewModel(
    private val getCurrentUser: GetCurrentUser,
    private val logOutUseCase: LogOutUseCase,
    private val getUsersSessionsUseCase: GetUsersSessionsUseCase,
    private val getUsersCharactersUseCase: GetUsersCharactersUseCase
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
                if (user != null) {
                    val sessions = getUsersSessionsUseCase.execute()
                    val characters = getUsersCharactersUseCase.execute(user.token)
                    _viewState.value = ProfileState.Main(user, sessions, characters)
                }
                else {
                    _viewAction.value = ProfileAction.NavigateToGreeting
                }
            }
        }
    }
}