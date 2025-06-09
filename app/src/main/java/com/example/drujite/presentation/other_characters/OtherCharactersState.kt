package com.example.drujite.presentation.other_characters

import com.example.domain.models.CharacterModel

sealed class OtherCharactersState {
    data object Initialization: OtherCharactersState()
    data class Main(
        val characters: List<CharacterModel>,
        val filteredCharacers: List<CharacterModel>,
        val displayedCharacters: List<CharacterModel>,
        val clans: List<String>,
        val query: String = ""
    ) : OtherCharactersState()
}

sealed class OtherCharactersEvent {
    data object LoadData: OtherCharactersEvent()
    data object ClearFilter: OtherCharactersEvent()
    data class FilterByClan(val clan: String): OtherCharactersEvent()
    data class Search(val query: String): OtherCharactersEvent()
    data class QueryChanged(val query: String): OtherCharactersEvent()
}