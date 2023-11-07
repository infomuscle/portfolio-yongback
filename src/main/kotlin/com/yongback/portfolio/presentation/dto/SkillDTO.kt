package com.yongback.portfolio.presentation.dto

import com.yongback.portfolio.domain.entity.Skill

data class SkillDTO(
    val name: String,
    val type: String
) {
    constructor(skill: Skill) : this(
        name = skill.name,
        type = skill.type.name
    )
}
