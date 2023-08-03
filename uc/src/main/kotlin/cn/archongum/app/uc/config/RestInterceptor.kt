package cn.archongum.app.uc.config

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.MDC
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import java.util.*

@Component
class RestInterceptor : HandlerInterceptor {
    override fun preHandle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any,
    ): Boolean {
        val traceId = request.getHeader("X-Request-ID")
        if (traceId == null || traceId.isBlank()) {
            val uuid = UUID.randomUUID().toString().replace("-", "").substring(0, 16)
            response.setHeader("X-Request-ID", uuid)
            MDC.put("traceid", uuid)
        }
        return super.preHandle(request, response, handler)
    }
}
