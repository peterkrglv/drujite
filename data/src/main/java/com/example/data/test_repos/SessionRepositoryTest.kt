package com.example.data.test_repos

import com.example.domain.models.SessionModel
import com.example.domain.models.TimeTableEventModel
import com.example.domain.models.TimetableModel
import com.example.domain.repos.SessionRepository

class SessionRepositoryTest : SessionRepository {
    override suspend fun getCurrentSession(): SessionModel? {
        return null
    }


    override suspend fun getUsersSessions(): List<SessionModel> {
        return listOf(
            SessionModel(
                id = 1,
                name = "Тайны топей",
                description = "Погрузитесь в мир магии школы колдовстворец, где ученики учатся древним шаманским ритуалам...",
                dates = "23.03.2025 - 29.03.2025",
                imageUrl = null
            ),
            SessionModel(
                id = 2,
                name = "Турнир желаний",
                description = "Погрузитель в магическую Британию 1921 года и станьте частью ежегодного дуэльного турнира...",
                dates = "30.04.2025 - 04.05.2025",
                imageUrl = null
            ),
        )
    }

    override suspend fun getSessionByCode(code: String): SessionModel? {
        return null
    }

    override suspend fun addCharacterToSession(sessionId: Int, characterId: Int): Boolean {
        return true
    }

    override suspend fun getTimetable(sessionId: Int, date: String): TimetableModel? {
        val timeTableEvents = listOf(
            TimeTableEventModel(
                1,
                1,
                "Подъем",
                "8:45",
                true
            ),
            TimeTableEventModel(
                2,
                1,
                "Завтрак", "", true
            ),
            TimeTableEventModel(
                3,
                1,
                "Старший клан 1", "9:00", false
            ),
            TimeTableEventModel(
                4,
                1,
                "Старший клан 2", "9:15", false
            ),
            TimeTableEventModel(
                5,
                1,
                "Младший клан", "9:30", false
            ),
            TimeTableEventModel(
                6,
                1,
                "Уроки", "", true
            ),
            TimeTableEventModel(
                7,
                1,
                "Урок 1", "10:20-11:00", false
            ),
            TimeTableEventModel(
                8,
                1,
                "Урок 2", "11:10-11:50", false
            ),
            TimeTableEventModel(
                9,
                1,
                "Урок 3", "12:00-12:40", false
            ),
            TimeTableEventModel(
                10,
                1,
                "Урок 4", "12:50-13:30", false
            ),
            TimeTableEventModel(
                11,
                1,
                "Обед", "", true
            ),
            TimeTableEventModel(
                12,
                1,
                "Старший клан 1", "13:40", false
            ),
            TimeTableEventModel(
                13,
                1,
                "Старший клан 2", "13:55", false
            ),
            TimeTableEventModel(
                13,
                1,
                "Младший клан", "14:10", false
            ),
            TimeTableEventModel(
                14,
                1,
                "Дневная ролевая игра", "", true
            ),
            TimeTableEventModel(
                15,
                1,
                "Альянсная игра", "16:00", false
            ),
            TimeTableEventModel(
                16,
                1,
                "Час персонажа", "18:00", false
            ),
            TimeTableEventModel(
                17,
                1,
                "Ужин", "", true
            ),
            TimeTableEventModel(
                18,
                1,
                "Старший клан 1", "19:00", false
            ),
            TimeTableEventModel(
                19,
                1,
                "Старший клан 2", "19:15", false
            ),
            TimeTableEventModel(
                20,
                1,
                "Младший клан", "19:30", false
            ),
            TimeTableEventModel(
                21,
                1,
                "Вечерняя ролевая игра", "20:00", true
            ),
            TimeTableEventModel(
                22,
                1,
                "Клановый сбор", "21:30", false
            ),
            TimeTableEventModel(
                23,
                1,
                "Отбой", "22:30", true
            )
        )
        val timeTable = TimetableModel(
            sessionId = 1,
            date = "25.03",
            events = timeTableEvents
        )
        return timeTable
    }
}