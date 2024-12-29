package com.yongback.portfolio.presentation.dto

data class ExperienceDTO(
    val title: String,
    val description: String,
    val startYearMonth: String?,
    val endYearMonth: String?,
    val details: List<String>
)
