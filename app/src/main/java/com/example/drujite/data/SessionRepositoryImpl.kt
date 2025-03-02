package com.example.drujite.data

import com.example.drujite.domain.SessionModel
import com.example.drujite.domain.SessionRepository

class SessionRepositoryImpl: SessionRepository {
    override suspend fun getSessions(): List<SessionModel> {
        return listOf(
            SessionModel(name = "Тайны топей", description = "Погрузитесь в мир магии школы колдовстворец, где ученики учатся древним шаманским ритуалам...", dates = "23.03.2025 - 29.03.2025", nOfPlayers = 30, image = null),
            SessionModel(name = "Турнир желаний", description = "Погрузитель в магическую Британию 1921 года и станьте частью ежегодного дуэльного турнира...", dates = "30.04.2025 - 04.05.2025", nOfPlayers = 20, image = null),
        )
    }
}