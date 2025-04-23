package com.example.data.test

import com.example.domain.models.NewsModel
import com.example.domain.repos.NewsRepository

class NewsRepositoryTest: NewsRepository {
    override suspend fun getNewsBySessionId(sessionId: Int): List<NewsModel> {
        return listOf(
            NewsModel(
                1,
                "Новые детали истории",
                "Теперь с самой загадочной личностью смены связаны еще две детали, которые мы разместили и с которыми вы можете уже сейчас ознакомиться. \n\n\n Конец",
                "06.06",
                "12:30",
                "imageUrl"
            ),
            NewsModel(
                2,
                "Немного о фракциях...",
                "Вместе с загадочной личностью из Пограничного Мира в ряды встали некоторые фракции, которые до сегодняшнего дня считались слабыми. Что же это за фракции? Давайте разбираться.",
                "05.06",
                "22:30",
                "imageUrl"
            ),
            NewsModel(
                3,
                "Заголовок",
                "Интересная очень интересная новость",
                "дата",
                "время",
                "imageUrl"
            )

        )
    }
}