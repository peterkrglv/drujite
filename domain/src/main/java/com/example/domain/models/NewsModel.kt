package com.example.domain.models

data class NewsModel (
    val id: Int,
    val title: String,
    val content: String,
    val date: String,
    val time: String,
    val imageUrl: String,
)