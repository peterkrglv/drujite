package com.example.drujite.presentation.character_customisation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.use_cases.CustomisationCategory
import com.example.domain.use_cases.GetCustomisationOptions
import com.example.domain.use_cases.SaveCharacterCustomImageUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CustomisationViewModel(
    private val getCustomisationOptions: GetCustomisationOptions,
    private val saveCustomImage: SaveCharacterCustomImageUseCase
) : ViewModel() {
    private val _viewState = MutableStateFlow<CustomisationState>(CustomisationState.Initialization)
    val viewState: StateFlow<CustomisationState>
        get() = _viewState
    private val _viewAction = MutableStateFlow<CustomisationAction?>(null)
    val viewAction: StateFlow<CustomisationAction?>
        get() = _viewAction

    fun obtainEvent(event: CustomisationEvent) {
        when (event) {
            is CustomisationEvent.OptionChosen -> optionChosen(event.category, event.num)
            is CustomisationEvent.ProceedClicked -> proceedClicked(event.characterId)
            is CustomisationEvent.LoadOptions -> loadOptions()
        }
    }

    fun clearAction() {
        _viewAction.value = null
    }

    private fun loadOptions() {
        viewModelScope.launch {
            _viewState.value = CustomisationState.Loading
            val options = withContext(Dispatchers.IO) {
                getCustomisationOptions.execute()
            }
            _viewState.value = CustomisationState.Main(options = options)
        }
    }

    private fun proceedClicked(characterId: Int) {
        val state = _viewState.value
        if (state !is CustomisationState.Main) {
            return
        }
        _viewState.value = CustomisationState.Loading
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                Thread.sleep(1000)
                val result = saveCustomImage.execute(characterId, state.chosenOptions)
                if (result) {
                    _viewAction.value = CustomisationAction.NavigateToMain
                } else {
                    Log.e("network", "error saving image")
                }
                Thread.sleep(1000)
                _viewState.value = state
            }
        }
    }

    private fun optionChosen(category: CustomisationCategory, num: Int) {
        val state = _viewState.value
        if (state !is CustomisationState.Main) {
            return
        }
        val updatedChosenOptions = state.chosenOptions.toMutableMap()
        updatedChosenOptions[category] = num
        _viewState.value = state.copy(chosenOptions = updatedChosenOptions)
    }
}
