package com.example.drujite.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.GoalModel
import com.example.domain.use_cases.AccessSharedPrefsUseCase
import com.example.domain.use_cases.GetCharacterByIdUseCase
import com.example.domain.use_cases.GetGoalsByCharacterIdUseCase
import com.example.domain.use_cases.UpdateGoalStatusUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(
    private val sharedPrefs: AccessSharedPrefsUseCase,
    private val getCharacterByIdUseCase: GetCharacterByIdUseCase,
    private val getGoalsByCharacterIdUseCase: GetGoalsByCharacterIdUseCase,
    private val updateGoalStatusUseCase: UpdateGoalStatusUseCase
): ViewModel() {
    private val _viewState = MutableStateFlow<HomeState>(HomeState.Initialization)
    val viewState: StateFlow<HomeState>
        get() = _viewState
    private val _viewAction = MutableStateFlow<HomeAction?>(null)
    val viewAction: StateFlow<HomeAction?>
        get() = _viewAction

    fun obtainEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.LoadData -> loadData()
            is HomeEvent.goalClicked -> goalClicked(event.goal)
            is HomeEvent.CustomisationClicked -> customisationClicked()
        }
    }

    private fun customisationClicked() {
        _viewAction.value = HomeAction.NavigateToCustomization
    }

    private fun goalClicked(goal: GoalModel) {
        val state = _viewState.value
        if (state !is HomeState.Main) return

        val updatedGoals = state.goals.map {
            if (it.id == goal.id) {
                it.copy(isCompleted = !it.isCompleted)
            } else {
                it
            }
        }
        _viewState.value = state.copy(goals = updatedGoals)

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                updateGoalStatusUseCase.execute(goal.id, !goal.isCompleted)
            }
        }
    }

    private fun loadData() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val characterId = sharedPrefs.getCharacterId()
                val character = getCharacterByIdUseCase.execute(characterId)
                val goals = getGoalsByCharacterIdUseCase.execute(characterId)
                _viewState.value = HomeState.Main(character, goals)
            }
        }
    }

    fun clearAction() {
        _viewAction.value = null
    }


}