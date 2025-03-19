package com.example.drujite.presentation.home

import com.example.domain.models.CharacterModel
import com.example.domain.models.GoalModel

sealed class HomeState {
    data class Main(
        val character: CharacterModel,
        val goals: List<GoalModel>
    ) : HomeState()
    data object Initialization : HomeState()
    data object Loading : HomeState()
}

sealed class HomeEvent {
    data object LoadData : HomeEvent()
    data class goalClicked(val goal: GoalModel) : HomeEvent()
    data object CustomisationClicked : HomeEvent()
}

sealed class HomeAction {
    data object NavigateToCustomization : HomeAction()
}