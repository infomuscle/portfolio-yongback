package com.yongback.portfolio.presentation.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class PresentationViewController {

    @GetMapping("/test")
    fun test(): String {
        return "test"
    }

}
