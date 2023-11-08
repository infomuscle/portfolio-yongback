package com.yongback.portfolio.domain.repository

import com.yongback.portfolio.domain.entity.Introduction
import org.springframework.data.jpa.repository.JpaRepository

interface IntroductionRepository : JpaRepository<Introduction, Long> {

    // select * from introduction where is_active = :isActive
    fun findAllByIsActive(isActive: Boolean): List<Introduction>

}
