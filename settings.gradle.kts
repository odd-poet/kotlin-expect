rootProject.name = "kotlin-expect"

/**
 * gradle plugin management.
 *
 * version 정보는 `gradle.properties`에서 관리.
 */
pluginManagement {
    val kotlinPluginVersion: String by settings
    val bintrayPluginVersion: String by settings

    plugins {
        // kotlin
        kotlin("jvm") version kotlinPluginVersion

        id("maven-publish")
        id("com.jfrog.bintray") version bintrayPluginVersion
    }
}

buildscript {
    repositories {
        mavenCentral()
        jcenter()
    }
}

