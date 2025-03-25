package com.example.domain.repos

import com.example.domain.use_cases.character.CustomisationCategory

interface CustomisationRepository {
    suspend fun getCustomisationOptions(): List<CustomisationCategory>
}