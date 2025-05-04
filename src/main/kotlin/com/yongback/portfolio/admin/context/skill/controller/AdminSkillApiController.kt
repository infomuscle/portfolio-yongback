package com.yongback.portfolio.admin.context.skill.controller

import com.yongback.portfolio.admin.context.skill.form.SkillForm
import com.yongback.portfolio.admin.context.skill.service.AdminSkillService
import com.yongback.portfolio.admin.data.ApiResponse
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/admin/api/skills")
class AdminSkillApiController(
    private val adminSkillService: AdminSkillService
) {

    @PostMapping
    fun postSkill(@RequestBody @Validated form: SkillForm): ResponseEntity<Any> {
        adminSkillService.save(form)

        return ApiResponse.successCreate()
    }

    @PutMapping("/{id}")
    fun putSkill(@PathVariable id: Long, @RequestBody form: SkillForm): ResponseEntity<Any> {
        adminSkillService.update(id, form)

        return ApiResponse.successUpdate()
    }
}
