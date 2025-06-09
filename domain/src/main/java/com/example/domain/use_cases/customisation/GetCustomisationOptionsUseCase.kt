package com.example.domain.use_cases.customisation

import com.example.domain.repos.CustomisationRepository

class GetCustomisationOptionsUseCase(
    private val repo: CustomisationRepository
) {
    suspend fun execute() = repo.getCustomisationOptions()
}

data class CustomisationOption(
    val id: Int,
    val typeId: Int,
    val imageUrl: String?,
    val iconUrl: String?
)

data class CustomisationCategory(
    val id: Int,
    val name: String,
    val isEditable: Boolean,
    val items: List<CustomisationOption>
)