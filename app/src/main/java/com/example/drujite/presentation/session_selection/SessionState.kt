package com.example.drujite.presentation.session_selection

import com.example.domain.models.SessionModel
import com.example.drujite.presentation.QRScannerResult

sealed class SessionState {
    data object Loading: SessionState()
    data class Main(
        val sessions: List<SessionModel> = emptyList(),
        val qrError: String = "",
        val isLoading: Boolean = false
    ): SessionState()
}

sealed class SessionEvent {
    data object LoadSessions: SessionEvent()
    data class SessionpRroceed(val session: SessionModel): SessionEvent()
    data object QRScannerClicked: SessionEvent()
    data class OnQRScannerClosed(val result: QRScannerResult, val sessionNum: String?): SessionEvent()
}

sealed class SessionAction {
    data class NavigateToCharacterCreation(val sessionId: Int): SessionAction()
    data object StartQRScanner: SessionAction()
}