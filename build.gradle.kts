plugins {
    `java-library`
    kotlin("jvm")
    id("maven-publish")
    id("io.github.gradle-nexus.publish-plugin")
    jacoco
    idea
    signing
}

group = "net.oddpoet"
version = "1.3.2"
description = "rspec style assertion library for kotlin test"

repositories {
    mavenCentral()
}

dependencies {
    compileOnly(kotlin("stdlib-jdk8"))
    compileOnly(kotlin("reflect"))
    api(libs.slf4j.api)

    // testing
    testRuntimeOnly(kotlin("stdlib-jdk8"))
    testRuntimeOnly(kotlin("reflect"))
    testApi(kotlin("test"))
    testApi(kotlin("test-junit5"))
    testApi(libs.junit5.api)
    testApi(libs.junit5.params)
    testRuntimeOnly(libs.junit5.engine)
    testRuntimeOnly(libs.logback.classic)
}

java {
    withJavadocJar()
    withSourcesJar()
}

tasks {
    compileKotlin {
        kotlinOptions {
            apiVersion = "1.5"
            languageVersion = "1.5"
            jvmTarget = "1.8"
            freeCompilerArgs = listOf("-Xjsr305=strict", "-opt-in=kotlin.RequiresOptIn")
        }
    }
    compileTestKotlin {
        kotlinOptions {
            apiVersion = "1.5"
            languageVersion = "1.5"
            jvmTarget = "1.8"
            freeCompilerArgs = listOf("-Xjsr305=strict", "-opt-in=kotlin.RequiresOptIn")
        }
    }
    configure<JavaPluginExtension> {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    test {
        useJUnitPlatform()
        testLogging {
            events("passed", "skipped", "failed")
        }
    }
    jacoco {
        toolVersion = libs.versions.jacoco.tool.get()
    }
    jacocoTestReport {
        reports {
            xml.required.set(true)
            xml.outputLocation.set(file("$buildDir/jacoco/report.xml"))
        }
    }
    withType<Test> {
        finalizedBy(jacocoTestReport)
    }
}


publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
            groupId = project.group.toString()
            artifactId = project.name
            version = project.version.toString()
            pom {
                name.set(project.name)
                description.set(project.description)
                url.set("https://github.com/odd-poet/kotlin-expect")
                licenses {
                    license {
                        name.set("The Apache Software License, Version 2.0")
                        url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                    }
                }
                developers {
                    developer {
                        id.set("oddpoet")
                        name.set("Yunsang Choi")
                        email.set("oddpoet@gmail.com")
                    }
                }
                scm {
                    url.set("https://github.com/odd-poet/kotlin-expect")
                }
            }
        }
    }
}

nexusPublishing {
    repositories {
        sonatype()
    }
}

signing {
    sign(publishing.publications["maven"])
}

