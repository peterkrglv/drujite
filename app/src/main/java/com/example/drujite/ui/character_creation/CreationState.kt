package com.example.drujite.ui.character_creation

import com.example.domain.models.ClanModel

sealed class CreationState {
    data object Initialization : CreationState()
    data class Main(
        val name: String = "",
        val clans: List<ClanModel> = emptyList<ClanModel>(),
        val chosenClan: ClanModel? = null,
        val error: String = ""
    ) : CreationState()
    data object Loading : CreationState()
}

sealed class CreationEvent {
    data class NameChanged(val name: String) : CreationEvent()
    data class ClanChosen(val clan: ClanModel) : CreationEvent()
    data object TransferClicked : CreationEvent()
    data object ProceedClicked: CreationEvent()
    data object LoadClans: CreationEvent()
}

sealed class CreationAction {
    data object NavigateToTransfer: CreationAction()
    data object NavigateToCustomisation: CreationAction()
}