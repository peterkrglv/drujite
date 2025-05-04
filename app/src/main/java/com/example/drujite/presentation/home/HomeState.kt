package com.example.drujite.presentation.home

import com.example.domain.models.CharacterModel
import com.example.domain.models.GoalModel
import com.example.domain.models.SessionModel

sealed class HomeState {
    data class Main(
        val character: CharacterModel,
        val goals: List<GoalModel>,
        //val session: SessionModel
    ) : HomeState()

    data object Initialization : HomeState()
    data object Loading : HomeState()
}

sealed class HomeEvent {
    data object LoadData : HomeEvent()
    data class GoalClicked(val goal: GoalModel) : HomeEvent()
    data object CustomisationClicked : HomeEvent()
}

sealed class HomeAction {
    data class NavigateToCustomization(val userToken: String, val sessionId: Int, val characterId: Int) :
        HomeAction()
}