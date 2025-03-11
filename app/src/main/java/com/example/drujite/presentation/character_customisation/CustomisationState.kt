package com.example.drujite.presentation.character_customisation

import androidx.compose.ui.graphics.vector.ImageVector

sealed class CustomisationState {
    data object Initialization : CustomisationState()
    data class Main(
        val options: Map<CustomisationOptions, List<ImageVector>> = emptyMap(),
        val chosenOption: Map<CustomisationOptions, ImageVector> = emptyMap()
    ) : CustomisationState()
    data object Loading : CustomisationState()
}

sealed class CustomisationEvent {
    data class OptionChosen(val option: CustomisationOptions, val num: Int) : CustomisationEvent()
    data object ProceedClicked: CustomisationEvent()
    data object LoadOptions: CustomisationEvent()
}

enum class CustomisationOptions {
    FACE,
    HAIR,
    BROWS,
    EYES,
    MOUTH
}