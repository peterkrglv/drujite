package com.example.drujite.presentation.character_transfer

import com.example.domain.models.CharacterModel

sealed class TransferState {
    data object Initialization : TransferState()
    data class Main(
        val characters: List<CharacterModel> = emptyList(),
        val chosenCharacter: CharacterModel? = null,
        val reason: String = "",
        val error: String = "",
        val userToken: String = "",
        val sessionId: Int = 0
    ) : TransferState()
    data object Loading : TransferState()
}

sealed class TransferEvent {
    data class CharacterChosen(val character: CharacterModel) : TransferEvent()
    data class ReasonChanged(val reason: String) : TransferEvent()
    data object ProceedClicked: TransferEvent()
    data object CharacterCreationClicked: TransferEvent()
    data class LoadCharacters(val userToken: String, val sessionId: Int): TransferEvent()
}

sealed class TransferAction {
    data object NavigateToCreation: TransferAction()
    data class NavigateToMain(val userToken: String, val sessionId: Int, val characterId: Int): TransferAction()
}