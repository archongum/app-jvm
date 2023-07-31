plugins {
    alias(gradlePlugins.plugins.kotlin.jvm)
    // Spring Boot
    alias(gradlePlugins.plugins.springboot.parent)
    alias(gradlePlugins.plugins.springboot.dependencymanagement)
    // OpenAPI
    alias(gradlePlugins.plugins.openapi.generator)
}

dependencies {
    // Spring Boot
    implementation(libs.spring.boot.starter.web)
    // OpenAPI
    implementation(libs.bundles.openapi)
    // Tests
    testImplementation(libs.spring.boot.starter.test)
}

openApiGenerate {
    generatorName.set("kotlin-spring")
    inputSpec.set("$projectDir/src/main/resources/specs/uc.yml")
    outputDir.set("$buildDir/generated")
    apiPackage.set("cn.archongum.app.uc.api")
    modelPackage.set("cn.archongum.app.uc.model")
    configOptions.set(
        mapOf(
            "delegatePattern" to "true",
            "useSpringBoot3" to "true",
        )
    )
}

sourceSets {
    main {
        kotlin {
            // OpenAPI
            srcDirs("$buildDir/generated/src/main/kotlin")
        }
    }
}

tasks.compileKotlin {
    // OpenAPI
    dependsOn(tasks.openApiGenerate)
}

val thisMainClass = "cn.archongum.app.uc.UcAppKt"

tasks.bootJar {
    mainClass.set(thisMainClass)
    archiveFileName.set("${projectDir.name}.jar")
    // Clean jar to prevent more than one jar
    doFirst {
        File(listOf(buildDir, "libs").joinToString(File.separator)).listFiles()?.forEach { it.deleteRecursively() }
    }
}
