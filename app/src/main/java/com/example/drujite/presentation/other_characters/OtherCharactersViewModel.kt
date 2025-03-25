package com.example.drujite.presentation.other_characters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.use_cases.AccessSharedPrefsUseCase
import com.example.domain.use_cases.character.GetCharactersBySessionId
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class OtherCharactersViewModel(
    private val sharedPrefs: AccessSharedPrefsUseCase,
    private val getCharactersBySessionId: GetCharactersBySessionId
) : ViewModel() {
    private val _viewState =
        MutableStateFlow<OtherCharactersState>(OtherCharactersState.Initialization)
    val viewState: StateFlow<OtherCharactersState>
        get() = _viewState

    fun obtainEvent(event: OtherCharactersEvent) {
        when (event) {
            is OtherCharactersEvent.LoadData -> loadData()
            is OtherCharactersEvent.ClearFilter -> clearFilter()
            is OtherCharactersEvent.FilterByClan -> filterByClan(event.clan)
        }
    }

    private fun filterByClan(clan: String) {
        val state = _viewState.value as? OtherCharactersState.Main ?: return
        val displayedCharacters = state.characters.filter { character ->
            character.clan == clan
        }
        _viewState.value = state.copy(displayedCharacters = displayedCharacters)
    }

    private fun clearFilter() {
        val state = _viewState.value as? OtherCharactersState.Main ?: return
        val displayedCharacters = state.characters
        _viewState.value = state.copy(displayedCharacters = displayedCharacters)
    }

    private fun loadData() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val sessionId = sharedPrefs.getSessionId()
                val characters = getCharactersBySessionId.execute(sessionId)
                _viewState.value = OtherCharactersState.Main(
                    characters = characters,
                    displayedCharacters = characters,
                    clans = characters.map { it.clan }.distinct()
                )
            }
        }
    }
}