package com.example.drujite.presentation.character_transfer

import androidx.lifecycle.ViewModel
import com.example.domain.models.CharacterModel
import com.example.domain.use_cases.AccessSharedPrefsUseCase
import kotlinx.coroutines.flow.MutableStateFlow

class TransferViewModel(
    private val sharedPrefsUseCase: AccessSharedPrefsUseCase,
) : ViewModel() {
    private val _viewState = MutableStateFlow<TransferState>(TransferState.Initialization)
    val viewState: MutableStateFlow<TransferState>
        get() = _viewState
    private val _viewAction = MutableStateFlow<TransferAction?>(null)
    val viewAction: MutableStateFlow<TransferAction?>
        get() = _viewAction

    fun obtainEvent(event: TransferEvent) {
        when (event) {
            is TransferEvent.ProceedClicked -> proceedClicked()
            is TransferEvent.CharacterChosen -> characterChosen(event.character)
            is TransferEvent.ReasonChanged -> reasonChanged(event.reason)
            is TransferEvent.LoadCharacters -> loadCharacters(event.userId)
        }
    }

    private fun clearAction() {
        _viewAction.value = null
    }

    private fun loadCharacters(userId: Int) {

    }

    private fun reasonChanged(reason: String) {
        val state = _viewState.value
        if (state !is TransferState.Main) {
            return
        }
        _viewState.value = state.copy(reason = reason)
    }

    private fun characterChosen(character: CharacterModel) {
        val state = _viewState.value
        if (state !is TransferState.Main) {
            return
        }
        _viewState.value = state.copy(chosenCharacter = character)
    }

    private fun proceedClicked() {
        val state = _viewState.value
        if (state !is TransferState.Main) {
            return
        }
        if (state.chosenCharacter == null) {
            _viewState.value = state.copy(error = "Выбери персонажа для переноса")
            return
        }
        _viewAction.value = TransferAction.NavigateToCustomisation(state.chosenCharacter.id)
    }
}