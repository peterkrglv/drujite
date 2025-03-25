package com.example.drujite.presentation.session_news

import com.example.domain.models.NewsModel

sealed class NewsState {
    data object Initialization: NewsState()
    data class Main(
        val news: List<NewsModel> = emptyList(),
        val displayedNews: List<NewsModel> = emptyList(),
        val query: String = "",
    ): NewsState()
    data object Loading: NewsState()
}

sealed class NewsEvent {
    data object LoadData: NewsEvent()
    data class Search(val query: String): NewsEvent()
    data class QueryChanged(val query: String): NewsEvent()
}