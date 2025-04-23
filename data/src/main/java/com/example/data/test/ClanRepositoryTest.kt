package com.example.data.test

import com.example.domain.models.ClanModel
import com.example.domain.repos.ClanRepository

class ClanRepositoryTest: ClanRepository {
    override suspend fun getClans(sessionId: Int): List<ClanModel> {
        return listOf(
            ClanModel(
                id = 1,
                name = "Яд волка",
                description = "",
            ),
            ClanModel(
                id = 2,
                name = "Вивианитовая ветвь",
                description = ""
            ),
        )
    }
}