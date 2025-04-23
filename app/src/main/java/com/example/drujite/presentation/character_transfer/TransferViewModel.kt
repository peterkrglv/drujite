package com.example.drujite.presentation.character_transfer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.CharacterModel
import com.example.domain.use_cases.AccessSharedPrefsUseCase
import com.example.domain.use_cases.character.AddCharacterToSessionUseCase
import com.example.domain.use_cases.character.GetUsersCharactersUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TransferViewModel(
    private val sharedPrefsUseCase: AccessSharedPrefsUseCase,
    private val getUsersCharactersUseCase: GetUsersCharactersUseCase,
    private val addCharacterToSessionUseCase: AddCharacterToSessionUseCase
) : ViewModel() {
    private val _viewState = MutableStateFlow<TransferState>(TransferState.Initialization)
    val viewState: StateFlow<TransferState>
        get() = _viewState
    private val _viewAction = MutableStateFlow<TransferAction?>(null)
    val viewAction: StateFlow<TransferAction?>
        get() = _viewAction

    fun obtainEvent(event: TransferEvent) {
        when (event) {
            is TransferEvent.ProceedClicked -> proceedClicked()
            is TransferEvent.CharacterChosen -> characterChosen(event.character)
            is TransferEvent.ReasonChanged -> reasonChanged(event.reason)
            is TransferEvent.LoadCharacters -> loadCharacters(event.userToken, event.sessionId)
            is TransferEvent.CharacterCreationClicked -> characterCreationClicked()
        }
    }

    fun clearAction() {
        _viewAction.value = null
    }

    private fun characterCreationClicked() {
        _viewAction.value = TransferAction.NavigateToCreation
    }

    private fun loadCharacters(userToken: String, sessionId: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val characters = getUsersCharactersUseCase.execute(userToken)
                _viewState.value = TransferState.Main(
                    characters = characters,
                    userToken = userToken,
                    sessionId = sessionId
                )
            }
        }
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
        } else if (state.reason == "") {
            _viewState.value = state.copy(error = "Введите причину переноса")
        }
        _viewState.value = TransferState.Loading
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val result = addCharacterToSessionUseCase.execute(
                    sessionId = state.sessionId,
                    characterId = state.chosenCharacter.id
                )
                if (result) {
                    sharedPrefsUseCase.saveCharacterId(state.chosenCharacter.id)
                    _viewAction.value = TransferAction.NavigateToMain(
                        state.userToken,
                        state.sessionId,
                        state.chosenCharacter.id
                    )
                } else {
                    _viewState.value =
                        state.copy(error = "Не получилось добавить персонажа. Попробуйте еще раз")
                }
            }
        }
    }
}