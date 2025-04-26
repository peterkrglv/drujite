package com.example.data.requests


data class GetTimetableBySessionAndDate (
    val sessionId: Int,
    val date: String?,
)