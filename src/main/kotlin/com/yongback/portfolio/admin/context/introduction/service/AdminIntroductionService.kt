package com.yongback.portfolio.admin.context.introduction.service

import com.yongback.portfolio.admin.data.TableDTO
import com.yongback.portfolio.domain.entity.Introduction
import com.yongback.portfolio.domain.repository.IntroductionRepository
import org.springframework.stereotype.Service

@Service
class AdminIntroductionService(
    private val introductionRepository: IntroductionRepository
) {

    fun getIntroductionTable(): TableDTO {
        val classInfo = Introduction::class
        val entities = introductionRepository.findAll()

        return TableDTO.from(classInfo, entities)
    }
}
