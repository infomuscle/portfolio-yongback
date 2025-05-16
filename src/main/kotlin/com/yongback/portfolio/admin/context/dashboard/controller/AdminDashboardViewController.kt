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
            VisitorsDTO(name = "Total", count = total),
            VisitorsDTO(name = "Weekly", count = weekly),
            VisitorsDTO(name = "Today", count = today)
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
