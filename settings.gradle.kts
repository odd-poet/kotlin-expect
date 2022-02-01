rootProject.name = "kotlin-expect"

/**
 * gradle plugin management.
 *
 * version 정보는 `gradle.properties`에서 관리.
 */
pluginManagement {
    val kotlinPluginVersion: String by settings

    plugins {
        // kotlin
        kotlin("jvm") version kotlinPluginVersion
        id("maven-publish")
    }
}

buildscript {
    repositories {
        mavenCentral()
    }
}


// ref: https://docs.gradle.org/current/userguide/platforms.html
enableFeaturePreview("VERSION_CATALOGS")
dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            version("slf4j", "1.7.35")
            version("logback", "1.2.10")
            version("junit5", "5.8.2")
            version("jacoco-tool", "0.8.7")

            alias("slf4j-api")
                .to("org.slf4j", "slf4j-api")
                .versionRef("slf4j")
            alias("logback-classic")
                .to("ch.qos.logback", "logback-classic")
                .versionRef("logback")

            alias("junit5-api")
                .to("org.junit.jupiter", "junit-jupiter-api")
                .versionRef("junit5")
            alias("junit5-params")
                .to("org.junit.jupiter", "junit-jupiter-params")
                .versionRef("junit5")

            alias("junit5-engine")
                .to("org.junit.jupiter", "junit-jupiter-engine")
                .versionRef("junit5")
        }
    }
}
