package cn.archongum.app.uc.service

import cn.archongum.app.uc.model.LoginRequest
import com.google.gson.Gson
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.junit5.MockKExtension
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

private val gson: Gson = Gson()

@ExtendWith(MockKExtension::class)
class LoginServiceTest {

    @InjectMockKs
    lateinit var loginService: LoginService

    @Test
    fun loginPost_willReturnOk() {
        val data = """
            {
              "type": "password",
              "username": "archon",
              "password": "archon123"
            }
        """.trimIndent()
        val request = gson.fromJson(data, LoginRequest::class.java)
        val response = loginService.loginPost(request)

        assertThat(response.statusCode.value()).isEqualTo(200)
        response.body?.let {
            assertThat(it.code).isEqualTo(0)
            it.data?.run {
                assertThat(this.tokenType.value).isEqualTo("bearer")
            }
        }
    }

    @Test
    fun loginPost_willReturn401() {
        val data = """
            {
              "type": "password",
              "username": "archon",
              "password": "bad password"
            }
        """.trimIndent()
        val request = gson.fromJson(data, LoginRequest::class.java)
        val response = loginService.loginPost(request)

        assertThat(response.statusCode.value()).isEqualTo(401)
        assertThat(response.body).isNull()
    }
}
