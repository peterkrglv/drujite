package com.example.drujite.presentation.character_transfer

import com.example.domain.models.CharacterModel

sealed class TransferState {
    data object Initialization : TransferState()
    data class Main(
        val characters: List<CharacterModel> = emptyList<CharacterModel>(),
        val chosenCharacter: CharacterModel? = null,
        val reason: String,
        val error: String = ""
    ) : TransferState()
    data object Loading : TransferState()
}

sealed class TransferEvent {
    data class CharacterChosen(val character: CharacterModel) : TransferEvent()
    data class ReasonChanged(val reason: String) : TransferEvent()
    data object ProceedClicked: TransferEvent()
    data class LoadCharacters(val userId: Int): TransferEvent()
}

sealed class TransferAction {
    data object NavigateToTransfer: TransferAction()
    data class NavigateToCustomisation(val characterId: Int): TransferAction()
}