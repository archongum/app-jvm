import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    alias(gradlePlugins.plugins.kotlin.jvm)
    alias(gradlePlugins.plugins.spotless)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

allprojects {
    group = "cn.archongum.app"
    version = "0.0.1"
    repositories {
        mavenLocal()
        maven(url = "https://maven.aliyun.com/repository/public")
        maven(url = "https://maven.aliyun.com/repository/gradle-plugin")
        maven(url = "https://maven.aliyun.com/repository/spring")
    }
    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "17"
        }
    }
    // Formatter
    apply {
        plugin("com.diffplug.spotless")
    }
    spotless {
        kotlin {
            ktlint()
            target("*.kt", "*.kts")
            targetExclude("**/generated/**")
        }
        yaml {
            prettier()
            target("*.yml")
            targetExclude("**/generated/**")
        }
    }
}
