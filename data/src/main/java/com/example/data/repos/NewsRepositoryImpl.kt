package com.example.data.repos

import android.util.Log
import com.example.data.api.NewsApi
import com.example.data.responces.NewsResponse
import com.example.domain.models.NewsModel
import com.example.domain.repos.NewsRepository
import com.example.domain.repos.SharedPrefsRepository

class NewsRepositoryImpl(
    private val newsApi: NewsApi,
    private val sharedPrefs: SharedPrefsRepository
    ): NewsRepository {
    override suspend fun getNewsBySessionId(sessionId: Int): List<NewsModel> {
        val token = sharedPrefs.getUserToken()?: return emptyList()
        Log.d("server", "Fetching news for sessionId: $sessionId with token: $token")
        try {
            val response = newsApi.getNewsBySessionId(
                token = "Bearer $token",
                sessionId = sessionId
            )
            Log.d("server", "Fetched news: ${response.size}")
            return response.map {
               it.toModel()
            }
        } catch (e: Exception) {
            Log.d("server", "Error fetching news: ${e.message}")
            return emptyList()
        }
    }
}

private fun NewsResponse.toModel() =
    NewsModel(
        id = id,
        title = title,
        content = content,
        dateTime = convertTimestampToDateTime(dateTime),
        imageUrl = imageUrl
    )


fun convertTimestampToDateTime(input: String): String {
    val regex = Regex("""(\d{4})-(\d{2})-(\d{2})T(\d{2}):(\d{2}):""")
    val matchResult = regex.find(input)
    return if (matchResult != null) {
        val (year, month, day, hour, minute) = matchResult.destructured
        "$day.$month   $hour:$minute"
    } else {
        ""
    }
}