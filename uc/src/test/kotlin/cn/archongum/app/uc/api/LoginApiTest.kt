package cn.archongum.app.uc.api

import cn.archongum.app.uc.model.LoginPost200Response
import cn.archongum.app.uc.model.LoginResponse
import cn.archongum.app.uc.service.LoginService
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest
class LoginApiTest(@Autowired val mockMvc: MockMvc) {

    @MockkBean
    private lateinit var loginService: LoginService

    @Test
    fun loginPost() {
        every { loginService.loginPost(any()) } returns ResponseEntity.ok(
            LoginPost200Response(
                code = 0,
                data = LoginResponse(
                    tokenType = LoginResponse.TokenType.bearer,
                    accessToken = "accessToken",
                    refreshToken = "refreshToken",
                    expiresIn = 3601,
                    scope = "ALL",
                ),
            ),
        )
        val data = """
            {
              "type": "password"
            }
        """.trimIndent()
        val request = post("/api/v1/login")
            .contentType(MediaType.APPLICATION_JSON)
            .content(data)
        mockMvc.perform(request)
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.code").value(0))
    }

    @Test
    fun logoutGet() {
    }
}
