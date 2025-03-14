package com.example.domain.use_cases

import com.example.domain.repos.CustomisationRepository

class GetCustomisationOptions(
    private val repo: CustomisationRepository
) {
    suspend fun execute() = repo.getCustomisationOptions()
}

data class CustomisationOption(
    val id: Int,
    val imageUrl: String
)

data class CustomisationCategory(
    val id: Int,
    val name: String,
    val priority: Int,
    val x: Int,
    val y: Int,
    val options: List<CustomisationOption>
)