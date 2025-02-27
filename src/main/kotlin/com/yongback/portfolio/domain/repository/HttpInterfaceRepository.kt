package com.yongback.portfolio.domain.repository

import com.yongback.portfolio.domain.entity.HttpInterface
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDateTime

interface HttpInterfaceRepository : JpaRepository<HttpInterface, Long> {

    // select count(*) from http_interface where create_date_time between :start and :end
    fun countAllByCreatedDateTimeBetween(start: LocalDateTime, end: LocalDateTime): Long

}
