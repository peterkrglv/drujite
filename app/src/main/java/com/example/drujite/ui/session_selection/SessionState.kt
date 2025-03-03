package com.example.drujite.ui.session_selection

import com.example.domain.SessionModel

sealed class SessionState {
    data object Loading: SessionState()
    data class Main(
        val sessions: List<SessionModel> = emptyList()
    ): SessionState()
}

sealed class SessionEvent {
    data object LoadSessions: SessionEvent()
    data class SessionSelected(val session: SessionModel): SessionEvent()
}

sealed class SessionAction {
    data class NavigateToCharacterCreation(val session: SessionModel): SessionAction()
}