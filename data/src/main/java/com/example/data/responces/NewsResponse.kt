package com.example.data.responces

data class NewsResponse (
    val id: Int,
    val sessionId: Int,
    val title: String,
    val content: String,
    val dateTime: String,
    val imageUrl: String?,
)