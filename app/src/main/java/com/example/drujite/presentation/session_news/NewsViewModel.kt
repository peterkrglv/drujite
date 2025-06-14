package com.example.drujite.presentation.session_news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.use_cases.session.GetSessionsNewsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NewsViewModel(
    private val getSessiosnNews: GetSessionsNewsUseCase
) : ViewModel() {
    private val _viewState = MutableStateFlow<NewsState>(NewsState.Initialization)
    val viewState: StateFlow<NewsState>
        get() = _viewState

    fun obtainEvent(event: NewsEvent) {
        when (event) {
            is NewsEvent.LoadData -> loadData()
            is NewsEvent.Search -> search(event.query)
            is NewsEvent.QueryChanged -> queryChanged(event.query)
        }
    }

    private fun queryChanged(query: String) {
        val state = _viewState.value
        if (state !is NewsState.Main) {
            return
        }
        val displayedNews = if (query == "") {
            state.news
        } else {
//            state.news.filter {
//                it.title.contains(query, ignoreCase = true) || it.content.contains(
//                    query,
//                    ignoreCase = true
//                )
//            }
            state.displayedNews
        }
        _viewState.value = state.copy(
            query = query,
            displayedNews = displayedNews
        )
    }

    private fun search(query: String) {
        val state = _viewState.value
        if (state !is NewsState.Main) {
            return
        }
        val displayedNews = state.news.filter {
            it.title.contains(query, ignoreCase = true) || it.content.contains(
                query,
                ignoreCase = true
            )
        }
        _viewState.value = state.copy(displayedNews = displayedNews)
    }

    private fun loadData() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val news = getSessiosnNews.execute()
                _viewState.value = NewsState.Main(news = news, displayedNews = news)
            }
        }
    }
}