package cn.archongum.app.uc.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Component
class WebConfig : WebMvcConfigurer {

    @Autowired
    private lateinit var restInterceptor: RestInterceptor

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(restInterceptor)
    }
}
