package com.example.drujite.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.GoalModel
import com.example.domain.use_cases.AccessSharedPrefsUseCase
import com.example.domain.use_cases.character.GetCharacterByIdUseCase
import com.example.domain.use_cases.customisation.GetCharacterItemsUseCase
import com.example.domain.use_cases.goal.GetCharacterGoals
import com.example.domain.use_cases.goal.UpdateGoalStatusUseCase
import com.example.domain.use_cases.session.GetCurrentSessionUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(
    private val sharedPrefs: AccessSharedPrefsUseCase,
    private val getCharacterByIdUseCase: GetCharacterByIdUseCase,
    private val getCurrentSessionUseCase: GetCurrentSessionUseCase,
    private val getCharacterItemsUseCase: GetCharacterItemsUseCase,
    private val getCharacterGoals: GetCharacterGoals,
    private val updateGoalStatusUseCase: UpdateGoalStatusUseCase,
) : ViewModel() {
    private val _viewState = MutableStateFlow<HomeState>(HomeState.Initialization)
    val viewState: StateFlow<HomeState>
        get() = _viewState
    private val _viewAction = MutableStateFlow<HomeAction?>(null)
    val viewAction: StateFlow<HomeAction?>
        get() = _viewAction

    fun obtainEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.LoadData -> loadData()
            is HomeEvent.GoalClicked -> goalClicked(event.goal)
            is HomeEvent.CustomisationClicked -> customisationClicked()
        }
    }

    private fun customisationClicked() {
        val state = _viewState.value
        if (state !is HomeState.Main) return
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val userToken = sharedPrefs.getUserToken()
                val sessionId = sharedPrefs.getSessionId()
                val characterId = state.character.id
                _viewAction.value = userToken?.let {
                    HomeAction.NavigateToCustomization(
                        it,
                        sessionId,
                        characterId
                    )
                }
            }
        }
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
                if (character == null) {
                    Log.d("HomeViewModel", "Character not found")
                    _viewState.value = HomeState.Initialization
                } else {
                    val session = getCurrentSessionUseCase.execute()
                    if (session == null) {
                        Log.d("HomeViewModel", "Session not found")
                        _viewState.value = HomeState.Initialization
                        return@withContext
                    }
                    val goals = getCharacterGoals.execute()
                    val items = getCharacterItemsUseCase.execute(characterId)
                    _viewState.value = HomeState.Main(
                        character = character,
                        goals = goals,
                        items = items,
                        session = session
                    )
                }
            }
        }
    }

    fun clearAction() {
        _viewAction.value = null
    }
}