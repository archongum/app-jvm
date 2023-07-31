package cn.archongum.app.uc

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class UcApp

fun main(args: Array<String>) {
    runApplication<UcApp>(*args)
}
