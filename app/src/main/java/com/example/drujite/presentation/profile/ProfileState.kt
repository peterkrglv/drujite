package com.example.drujite.presentation.profile

import com.example.domain.models.CharacterModel
import com.example.domain.models.SessionModel
import com.example.domain.models.UserModel

sealed class ProfileState {
    data object Initialization: ProfileState()
    data class Main(
        val user: UserModel,
        val sessions: List<SessionModel>,
        val characters: List<CharacterModel>,
        val showingCharacters: Boolean = true
    ) : ProfileState()
    data object Loading: ProfileState()
}

sealed class ProfileEvent {
    data object LoadData: ProfileEvent()
    data object LogOut: ProfileEvent()
    data object ShowCharacters: ProfileEvent()
    data object ShowSessions: ProfileEvent()
    data class ChangeSession(val sessionId: Int): ProfileEvent()
}

sealed class ProfileAction {
    data object NavigateToGreeting: ProfileAction()
    data object NavigateToMain: ProfileAction()
    data object NavigateToCharacterCreation: ProfileAction()
}