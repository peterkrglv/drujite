package com.example.drujite.presentation.session_selection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.SessionModel
import com.example.domain.use_cases.AccessSharedPrefsUseCase
import com.example.domain.use_cases.session.GetSessionByCodeUseCase
import com.example.domain.use_cases.session.GetUsersSessionsUseCase
import com.example.drujite.presentation.QRScannerResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SessionViewModel(
    private val getUsersSessionsUseCase: GetUsersSessionsUseCase,
    private val getSessionByCodeUseCase: GetSessionByCodeUseCase,
    private val accessSharedPrefsUseCase: AccessSharedPrefsUseCase
) : ViewModel() {
    private val _viewState = MutableStateFlow<SessionState>(SessionState.Loading)
    val viewState: StateFlow<SessionState>
        get() = _viewState
    private val _viewAction = MutableStateFlow<SessionAction?>(null)
    val viewAction: StateFlow<SessionAction?>
        get() = _viewAction

    fun obtainEvent(event: SessionEvent) {
        when (event) {
            is SessionEvent.LoadSessions -> loadSessions(event.userId)
            is SessionEvent.SessionProceed -> sessionSelected(event.session)
            is SessionEvent.QRScannerClicked -> qrScannerClicked()
            is SessionEvent.OnQRScannerClosed -> handleQRScannerResult(
                event.result,
                event.sessionNum
            )
        }
    }

    private fun handleQRScannerResult(result: QRScannerResult, sessionNum: String?) {
        val state = _viewState.value
        _viewAction.value = null
        if (sessionNum != null) {
            if (state !is SessionState.Main) {
                return
            }
            when (result) {
                QRScannerResult.Success -> {
                    viewModelScope.launch {
                        withContext(Dispatchers.IO) {
                            val session = getSessionByCodeUseCase.execute(sessionNum)
                            if (session != null) {
                                sessionSelected(session = session)
                            } else {
                                _viewState.value = state.copy(qrError = "Смена не найдена")
                            }
                        }
                    }
                }
                QRScannerResult.Canceled -> {
                    _viewState.value = state.copy(qrError = "")
                }
                QRScannerResult.Failure -> {
                    _viewState.value = state.copy(qrError = "Ошибка при сканировании QR-кода")

                }
            }
        }
    }

    private fun qrScannerClicked() {
        _viewAction.value = SessionAction.StartQRScanner
    }

    fun clearAction() {
        _viewAction.value = null
    }

    private fun loadSessions(userId: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                Thread.sleep(2000)
                val sessions = getUsersSessionsUseCase.execute(userId)
                _viewState.value = SessionState.Main(sessions)
            }
        }
    }

    private fun sessionSelected(session: SessionModel) {
        _viewAction.value = SessionAction.NavigateToCharacterCreation(session.id)
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                accessSharedPrefsUseCase.saveSessionId(id = session.id)
            }
        }
    }
}
