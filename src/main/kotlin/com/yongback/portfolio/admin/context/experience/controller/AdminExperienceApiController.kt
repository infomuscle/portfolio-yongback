package com.yongback.portfolio.admin.context.experience.controller

import com.yongback.portfolio.admin.context.experience.form.ExperienceForm
import com.yongback.portfolio.admin.context.experience.service.AdminExperienceService
import com.yongback.portfolio.admin.data.ApiResponse
import com.yongback.portfolio.admin.data.TableDTO
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/admin/api/experiences")
class AdminExperienceApiController(
    private val adminExperienceService: AdminExperienceService
) {

    @PostMapping
    fun postExperience(@RequestBody @Validated form: ExperienceForm): ResponseEntity<Any> {
        adminExperienceService.save(form)

        return ApiResponse.successCreate()
    }

    @PutMapping("/{id}")
    fun putExperience(@PathVariable id: Long, @RequestBody form: ExperienceForm): ResponseEntity<Any> {
        adminExperienceService.update(id, form)

        return ApiResponse.successUpdate()
    }

    @GetMapping("/{id}/details")
    fun getExperienceDetails(@PathVariable id: Long): TableDTO {
        return adminExperienceService.getExperienceDetailTable(id)
    }
}
