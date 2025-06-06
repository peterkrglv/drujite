package com.example.domain.models

data class NewsModel (
    val id: Int,
    val title: String,
    val content: String,
    val dateTime: String,
    val imageUrl: String?,
)