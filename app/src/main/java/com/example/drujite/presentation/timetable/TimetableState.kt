package com.example.drujite.presentation.timetable

import com.example.domain.models.TimetableModel

sealed class TimetableState {
    data object Initialization : TimetableState()
    data class Main(
        val timeTable: TimetableModel
    ) : TimetableState()
}

sealed class TimetableEvent {
    data object LoadData : TimetableEvent()
}