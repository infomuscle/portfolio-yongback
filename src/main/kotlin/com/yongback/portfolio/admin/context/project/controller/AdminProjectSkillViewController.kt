package com.yongback.portfolio.admin.context.project.controller

import com.yongback.portfolio.admin.context.project.service.AdminProjectSkillService
import com.yongback.portfolio.admin.data.FormElementDTO
import com.yongback.portfolio.admin.data.SelectFormElementDTO
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/admin/project/skill")
class AdminProjectSkillViewController(
    private val adminProjectSkillService: AdminProjectSkillService
) {

    @GetMapping
    fun projectSkill(model: Model): String {

        val proejctList = adminProjectSkillService.getProjectList()
        val skillList = adminProjectSkillService.getSkillList()

        val formElements = listOf<FormElementDTO>(
            SelectFormElementDTO("project", 8, proejctList),
            SelectFormElementDTO("skill", 4, skillList)
        )
        model.addAttribute("formElements", formElements)

        val table = adminProjectSkillService.getProejectSkillTable()
        model.addAttribute("table", table)
        model.addAttribute("detailTable", null)

        val pageAttributes = mutableMapOf<String, Any>(
            Pair("menuName", "Projects"),
            Pair("pageName", table.name),
            Pair("editable", false),
            Pair("deletable", true),
            Pair("hasDetails", false),
        )
        model.addAllAttributes(pageAttributes)

        return "admin/page-table"
    }
}
