package com.example.drujite.ui.character_creation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.ClanModel
import com.example.domain.use_cases.AccessSharedPrefsUseCase
import com.example.domain.use_cases.CreateCharacterUseCase
import com.example.domain.use_cases.GetClansBySessionIdUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CreationViewModel(
    private val sharedPrefsUseCase: AccessSharedPrefsUseCase,
    private val getClansBySessionIdUseCase: GetClansBySessionIdUseCase,
    private val createCharacterUseCase: CreateCharacterUseCase
) : ViewModel() {
    private val _viewState = MutableStateFlow<CreationState>(CreationState.Initialization)
    val viewState: MutableStateFlow<CreationState>
        get() = _viewState
    private val _viewAction = MutableStateFlow<CreationAction?>(null)
    val viewAction: MutableStateFlow<CreationAction?>
        get() = _viewAction

    fun obtainEvent(event: CreationEvent) {
        when (event) {
            is CreationEvent.ProceedClicked -> proceedClicked()
            is CreationEvent.TransferClicked -> transferClicked()
            is CreationEvent.LoadClans -> loadClans()
            is CreationEvent.ClanChosen -> clanChosen(event.clan)
            is CreationEvent.NameChanged -> nameChanged(event.name)
        }
    }

    fun clearAction() {
        _viewAction.value = null
    }

    private fun nameChanged(name: String) {
        val state = _viewState.value
        if (state !is CreationState.Main) {
            return
        }
        _viewState.value = state.copy(name = name)
    }

    private fun clanChosen(clan: ClanModel) {
        val state = _viewState.value
        if (state !is CreationState.Main) {
            return
        }
        _viewState.value = state.copy(chosenClan = clan)
    }

    private fun loadClans() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val session = sharedPrefsUseCase.getSessionId()
                val clans = getClansBySessionIdUseCase.execute(session)
                _viewState.value = CreationState.Main(clans = clans)
            }
        }
    }

    private fun transferClicked() {
        _viewAction.value = CreationAction.NavigateToTransfer
    }

    private fun proceedClicked() {
        val state = _viewState.value
        if (state !is CreationState.Main) return
        _viewState.value = CreationState.Loading
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                //really need to fix this....
                if (state.chosenClan != null) {
                    val creationResult = createCharacterUseCase.execute(
                        userId = 0,
                        name = state.name,
                        clanId = state.chosenClan.id
                    )
                    if (creationResult) {
                        _viewAction.value = CreationAction.NavigateToCustomisation
                    } else {
                        _viewState.value = state.copy(error = "Ошибка сети, попробуйте еще раз")
                    }
                } else {
                    _viewState.value = state.copy(error = "Выберите клан")
                }
            }
        }
    }
}