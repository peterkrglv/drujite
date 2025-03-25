package com.example.domain.models

data class TimetableModel(
    val id: Int,
    val sessionId: Int,
    val date: String,
    val events: List<TimeTableEventModel>
)

data class TimeTableEventModel(
    val id: Int,
    val timetableId: Int,
    val header: String,
    val time: String,
    val isOutlined: Boolean,
)