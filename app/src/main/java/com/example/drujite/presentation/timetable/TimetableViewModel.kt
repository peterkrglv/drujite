package com.example.drujite.presentation.timetable

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.use_cases.session.GetTimeTableUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TimetableViewModel(
    private val getTimeTableUseCase: GetTimeTableUseCase
) : ViewModel() {
    private val _viewState = MutableStateFlow<TimetableState>(TimetableState.Initialization)
    val viewState: StateFlow<TimetableState>
        get() = _viewState

    fun obtainEvent(event: TimetableEvent) {
        when (event) {
            is TimetableEvent.LoadData -> loadData()
        }
    }

    private fun loadData() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val timetable = getTimeTableUseCase.execute()
                if (timetable == null) {
                    _viewState.value = TimetableState.Initialization
                } else {
                    _viewState.value = TimetableState.Main(timetable)
                }
            }
        }
    }
}