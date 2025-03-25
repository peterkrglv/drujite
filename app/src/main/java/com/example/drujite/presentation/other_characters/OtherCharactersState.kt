package com.example.drujite.presentation.other_characters

import com.example.domain.models.CharacterModel

sealed class OtherCharactersState {
    data object Initialization: OtherCharactersState()
    data class Main(
        val characters: List<CharacterModel>,
        val displayedCharacters: List<CharacterModel>,
        val clans: List<String>,
    ) : OtherCharactersState()
}

sealed class OtherCharactersEvent {
    data object LoadData: OtherCharactersEvent()
    data object ClearFilter: OtherCharactersEvent()
    data class FilterByClan(val clan: String): OtherCharactersEvent()
}