package com.example.domain.repos

import com.example.domain.use_cases.CustomisationCategory

interface CustomisationRepository {
    suspend fun getCustomisationOptions(): List<CustomisationCategory>
}