package com.example.drujite.presentation.other_characters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.CharacterModel
import com.example.domain.use_cases.AccessSharedPrefsUseCase
import com.example.domain.use_cases.character.GetCharactersBySessionIdUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class OtherCharactersViewModel(
    private val sharedPrefs: AccessSharedPrefsUseCase,
    private val getCharactersBySessionIdUseCase: GetCharactersBySessionIdUseCase
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
            is OtherCharactersEvent.QueryChanged -> queryChanged(event.query)
            is OtherCharactersEvent.Search -> search(event.query)
        }
    }

    private fun search(query: String) {
        val state = _viewState.value
        if (state !is OtherCharactersState.Main) {
            return
        }
        val displayedCharacters =
            filterByQuery(characters = state.filteredCharacers, query = state.query)
        _viewState.value = state.copy(displayedCharacters = displayedCharacters)
    }

    private fun queryChanged(query: String) {
        val state = _viewState.value
        if (state !is OtherCharactersState.Main) {
            return
        }
        val displayedCharacters = if (query == "") {
            state.filteredCharacers
        } else {
            state.displayedCharacters
        }
        _viewState.value = state.copy(displayedCharacters = displayedCharacters, query = query)
    }

    private fun filterByClan(clan: String) {
        val state = _viewState.value as? OtherCharactersState.Main ?: return
        val filteredCharacters = state.characters.filter { character ->
            character.clan == clan
        }
        val displayedCharacters =
            filterByQuery(characters = state.filteredCharacers, query = state.query)
        _viewState.value = state.copy(
            displayedCharacters = displayedCharacters,
            filteredCharacers = filteredCharacters
        )
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
                val characters = getCharactersBySessionIdUseCase.execute(sessionId)
                _viewState.value = OtherCharactersState.Main(
                    characters = characters,
                    displayedCharacters = characters,
                    clans = characters.map { it.clan }.distinct(),
                    filteredCharacers = characters
                )
            }
        }
    }

    private fun filterByQuery(
        characters: List<CharacterModel>,
        query: String
    ): List<CharacterModel> {
        return if (query == "") {
            characters
        } else {
            characters.filter {
                it.name.contains(query, ignoreCase = true) || it.name.contains(
                    query,
                    ignoreCase = true
                )
            }
        }
    }
}