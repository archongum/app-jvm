rootProject.name = "app-java"
include("uc")

dependencyResolutionManagement {
    versionCatalogs {
        val kotlin = "1.8.22"
        // Spring Boot
        val springboot = "3.1.2"
        val springbootDependencymanagement = "1.1.2"
        // OpenAPI
        val openapi = "2.1.0"
        val openapiGenerator = "6.6.0"
        create("platforms") {
            // Spring Boot
            library("spring-boot-dependencies", "org.springframework.boot:spring-boot-dependencies:$springboot")
        }
        create("gradlePlugins") {
            plugin("kotlin-jvm", "org.jetbrains.kotlin.jvm").version(kotlin)
            // Spring Boot
            plugin("kotlin-plugin-spring", "org.jetbrains.kotlin.plugin.spring").version(kotlin)
            plugin("springboot-parent", "org.springframework.boot").version(springboot)
            plugin("springboot-dependencymanagement", "io.spring.dependency-management").version(springbootDependencymanagement)
            // OpenAPI
            plugin("openapi-generator", "org.openapi.generator").version(openapiGenerator)
        }
        create("libs") {
            // Spring Boot
            library("spring-boot-starter-web", "org.springframework.boot", "spring-boot-starter-web").withoutVersion()
            library("spring-boot-starter-test", "org.springframework.boot", "spring-boot-starter-test").withoutVersion()
            // OpenAPI
            library("openapi-webmvc-ui", "org.springdoc:springdoc-openapi-starter-webmvc-ui:$openapi")
            bundle(
                "openapi",
                listOf(
                    "openapi-webmvc-ui",
                )
            )
        }
    }
}
