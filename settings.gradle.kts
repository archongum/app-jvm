rootProject.name = "app-jvm"
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
        // Common
        val gson = "2.10.1"
        // Convention
        val spotless = "6.20.0"
        // Test
        val springmockk = "4.0.2"
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
            // Convention
            plugin("spotless", "com.diffplug.spotless").version(spotless)
        }
        create("libs") {
            // Spring Boot
            library("spring-boot-starter-web", "org.springframework.boot", "spring-boot-starter-web").withoutVersion()
            // OpenAPI
            library("openapi-webmvc-ui", "org.springdoc:springdoc-openapi-starter-webmvc-ui:$openapi")
            // Common
            library("gson", "com.google.code.gson:gson:$gson")
            // Test
            library("test-spring-boot-starter", "org.springframework.boot", "spring-boot-starter-test").withoutVersion()
            library("test-junit-jupiter-api", "org.junit.jupiter", "junit-jupiter-api").withoutVersion()
            library("test-junit-jupiter-engine", "org.junit.jupiter", "junit-jupiter-engine").withoutVersion()
            library("test-spring-mockk", "com.ninja-squad:springmockk:$springmockk")
            bundle(
                "openapi",
                listOf(
                    "openapi-webmvc-ui",
                ),
            )
            bundle(
                "test",
                listOf(
                    "test-spring-boot-starter",
                    "test-junit-jupiter-api",
                    "test-junit-jupiter-engine",
                    "test-spring-mockk",
                ),
            )
        }
    }
}
