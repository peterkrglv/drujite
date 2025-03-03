package com.example.drujite.ui.session_selection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.GetSessionsUseCase
import com.example.domain.SessionModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SessionViewModel(
    private val getSessionsUseCase: GetSessionsUseCase
) : ViewModel() {
    private val _viewState = MutableStateFlow<SessionState>(SessionState.Loading)
    val viewState: StateFlow<SessionState>
        get() = _viewState
    private val _viewAction = MutableStateFlow<SessionAction?>(null)
    val viewAction: StateFlow<SessionAction?>
        get() = _viewAction

    fun obtainEvent(event: SessionEvent) {
        when (event) {
            is SessionEvent.LoadSessions -> loadSessions()
            is SessionEvent.SessionSelected -> sessionSelected(event.session)
        }
    }

    fun clearAction() {
        _viewAction.value = null
    }

    private fun loadSessions() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                Thread.sleep(2000)
                val sessions = getSessionsUseCase.execute()
                _viewState.value = SessionState.Main(sessions)
            }
        }
    }

    private fun sessionSelected(session: SessionModel) {
        _viewAction.value = SessionAction.NavigateToCharacterCreation(session)
    }
}
