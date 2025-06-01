package com.example.drujite.presentation.character_customisation

import androidx.compose.ui.graphics.ImageBitmap
import com.example.domain.use_cases.customisation.CustomisationCategory

sealed class CustomisationState {
    data object Initialization : CustomisationState()
    data class Main(
        val options: List<CustomisationCategory> = emptyList(),
        val chosenOptions: Map<CustomisationCategory, Int> = emptyMap(),
        val characterId: Int
    ) : CustomisationState()
    data object Loading : CustomisationState()
}

sealed class CustomisationEvent {
    data class OptionChosen(val category: CustomisationCategory, val num: Int) : CustomisationEvent()
    data class ProceedClicked(val characterId: Int): CustomisationEvent()
    data class LoadOptions(val characterId: Int): CustomisationEvent()
}

sealed class CustomisationAction {
    data object NavigateToMain: CustomisationAction()
}

