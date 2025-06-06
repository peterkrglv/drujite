package com.example.data.responces

data class SessionResponse (
    val id: Int,
    val name: String,
    val description: String,
    val startDate: String,
    val endDate: String,
    val imageUrl: String? = null
)