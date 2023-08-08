package cn.archongum.app.uc.api

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.view.RedirectView


@Controller
@RequestMapping("/")
class BaseApi {
    @GetMapping("/")
    fun rootGet(): RedirectView {
        return RedirectView("/swagger-ui/index.html")
    }
}
