package cn.archongum.app.uc.config

import cn.archongum.app.uc.model.ExceptionRequest
import cn.archongum.app.uc.model.ExceptionRuntime
import cn.archongum.app.uc.model.ExceptionUnknown
import jakarta.servlet.http.HttpServletRequest
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.ServletRequestBindingException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class RestExceptionHandler {

    companion object {
        private val log: Logger = LoggerFactory.getLogger(this::class.java)
    }

    @ExceptionHandler
    fun servletRequestBindingException(
        ex: ServletRequestBindingException,
        request: HttpServletRequest,
    ): ResponseEntity<ExceptionRequest> {
        log.info(ex::class.toString())
        log.info(ex.toString())
        log.info(ex.message)
        log.info(ex.body.toString())
        return ResponseEntity.status(ex.statusCode).body(
            ExceptionRequest(
                data = mapOf(
                    "type" to ex.body.type,
                    "title" to ex.body.title,
                    "detail" to ex.body.detail,
                ),
            ),
        )
    }

    @ExceptionHandler
    fun runtimeException(
        ex: RuntimeException,
        request: HttpServletRequest,
    ): ResponseEntity<ExceptionRuntime> {
        log.info(ex::class.toString())
        log.info(ex.toString())
        log.info(ex.message)
        return ResponseEntity.internalServerError().body(ExceptionRuntime())
    }

    @ExceptionHandler
    fun exception(ex: Exception, request: HttpServletRequest): ResponseEntity<ExceptionUnknown> {
        log.info(ex::class.toString())
        log.info(ex.toString())
        log.info(ex.message)
        return ResponseEntity.internalServerError().body(ExceptionUnknown())
    }
}
