package cn.archongum.app.uc.service

import cn.archongum.app.uc.api.AuthApiDelegate
import cn.archongum.app.uc.model.LoginPost200Response
import cn.archongum.app.uc.model.LoginRequest
import cn.archongum.app.uc.model.LoginResponse
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class LoginService : AuthApiDelegate {

    override fun loginPost(loginRequest: LoginRequest): ResponseEntity<LoginPost200Response> {
        when (loginRequest.type) {
            LoginRequest.Type.password -> {
                if (loginRequest.username == "archon" && loginRequest.password == "archon123") {
                    return ResponseEntity.ok(
                        LoginPost200Response(
                            code = 0,
                            data = LoginResponse(
                                tokenType = LoginResponse.TokenType.bearer,
                                accessToken = "accessToken",
                                refreshToken = "refreshToken",
                                expiresIn = 3600,
                                scope = "ALL",
                            ),
                        ),
                    )
                }
                return ResponseEntity.status(401).build()
            }

            LoginRequest.Type.phone -> TODO()
            LoginRequest.Type.wechat -> TODO()
        }
    }

    override fun logoutGet(authorization: String): ResponseEntity<Unit> {
        if (authorization == "Bearer accessToken") {
            return ResponseEntity.status(204).build()
        }
        val i = 1 / 0
        return ResponseEntity.status(401).build()
    }
}
