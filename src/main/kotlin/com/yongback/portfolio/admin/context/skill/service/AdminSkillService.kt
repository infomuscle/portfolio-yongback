package com.yongback.portfolio.admin.context.skill.service

import com.yongback.portfolio.admin.data.TableDTO
import com.yongback.portfolio.domain.entity.Skill
import com.yongback.portfolio.domain.repository.SkillRepository
import org.springframework.stereotype.Service

@Service
class AdminSkillService(
    private val skillRepository: SkillRepository
) {

    fun getSkillTable(): TableDTO {
        val classInfo = Skill::class
        val entities = skillRepository.findAll()

        return TableDTO.from(classInfo, entities)
    }
}
