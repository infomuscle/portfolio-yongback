package com.yongback.portfolio.admin.context.dashboard.controller

import com.yongback.portfolio.admin.context.dashboard.data.VisitorsDTO
import com.yongback.portfolio.admin.context.dashboard.service.AdminDashboardService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/admin")
class AdminDashboardViewController(
    private val adminDashboardService: AdminDashboardService
) {

    @GetMapping
    fun index(model: Model): String {

        var table = adminDashboardService.getHttpInterfaceTable()
        model.addAttribute("table", table)

        val total = adminDashboardService.countVisitorsTotal()
        val weekly = adminDashboardService.countVisitorsWeekly()
        val today = adminDashboardService.countVisitorsToday()

        val visitors = listOf(
            VisitorsDTO(name = "Total", count = total, color = "#4154f1", background = "#f6f6fe"),
            VisitorsDTO(name = "Weekly", count = weekly, color = "#2eca6a", background = "#e0f8e9"),
            VisitorsDTO(name = "Today", count = today, color = "#ff771d", background = "#ffecdf")
        )
        model.addAttribute("visitors", visitors)

        val pageAttributes = mutableMapOf<String, Any>(
            Pair("menuName", ""),
            Pair("pageName", "Dashboard"),
            Pair("editable", false),
            Pair("deletable", false),
            Pair("hasDetails", false),
        )
        model.addAllAttributes(pageAttributes)

        return "admin/index"
    }
}
