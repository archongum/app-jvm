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
        maven(url = "https://maven.datastory.com.cn/nexus/content/groups/public/")
        maven(url = "https://maven.aliyun.com/nexus/content/groups/public")
        maven(url = "https://maven.aliyun.com/repository/gradle-plugin")
        maven(url = "https://maven.aliyun.com/repository/central")
        maven(url = "https://maven.aliyun.com/repository/jcenter")
        maven(url = "https://maven.aliyun.com/repository/google")
        maven(url = "https://packages.confluent.io/maven/")
    }
    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "17"
        }
    }
}

subprojects {
    apply {
        plugin("com.diffplug.spotless")
    }
    spotless {
        kotlin {
            ktlint()
            target("**/src/main/kotlin/*.kt")
            targetExclude("**/generated/**")
        }
    }
}
