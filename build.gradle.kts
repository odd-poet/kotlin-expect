import java.util.Date

plugins {
    java
    kotlin("jvm")
    id("maven-publish")
    id("com.jfrog.bintray")
    jacoco
}

group = "net.oddpoet"
version = "1.3.0-SNAPSHOT"
description = "rspec style assertion library for kotlin test"

/**
 * Dependency versions
 */
val kotlinVersion: String by project // from gradle.properties
val junitVersion = "4.12"
val slf4jVersion = "1.7.25"
val logbackVersion = "1.2.3"
val jacocoToolVersion = "0.8.5"

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    api("org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion")
    api("org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion")
    api("org.slf4j:slf4j-api:$slf4jVersion")

    testImplementation("org.jetbrains.kotlin:kotlin-test:$kotlinVersion")
    testImplementation("junit:junit:$junitVersion")
    testImplementation("ch.qos.logback:logback-classic:$logbackVersion")
}
tasks {
    compileKotlin {
        kotlinOptions.apiVersion = "1.4"
        kotlinOptions.languageVersion = "1.4"
        kotlinOptions.jvmTarget = "1.6"
    }
    compileTestKotlin {
        kotlinOptions.apiVersion = "1.4"
        kotlinOptions.languageVersion = "1.4"
        kotlinOptions.jvmTarget = "1.6"
    }
    val sourcesJar by registering(Jar::class) {
        dependsOn("classes")
        archiveClassifier.set("sources")
        from(sourceSets["main"].allSource)
    }

    val javadocJar by registering(Jar::class) {
        dependsOn(JavaPlugin.JAVADOC_TASK_NAME)
        archiveClassifier.set("javadoc")
        from(javadoc)
    }

    artifacts {
        add("archives", sourcesJar)
        add("archives", javadocJar)
    }

    test {
        testLogging {
            events("passed", "skipped", "failed")
        }
    }
    jacoco {
        toolVersion = jacocoToolVersion
    }
    jacocoTestReport {
        reports {
            xml.isEnabled = true
            xml.destination = file("$buildDir/jacoco/report.xml")
        }
    }
}


fun prop(name: String, defaultValue: String): String {
    return if (project.hasProperty(name)) {
        project.property(name) as String
    } else {
        defaultValue
    }
}

bintray {
    user = prop("bintray.user", "WHORU")
    key = prop("bintray.key", "NO-BINTRAY-KEY")
    pkg.apply {
        repo = "maven"
        name = project.name
        setLicenses("Apache-2.0")
        setLabels("kotlin", "expect", "rspec")

        websiteUrl = "https://github.com/odd-poet/kotlin-expect"
        issueTrackerUrl = "https://github.com/odd-poet/kotlin-expect/issues"
        vcsUrl = "https://github.com/odd-poet/kotlin-expect.git"

        publicDownloadNumbers = true
        version.apply {
            name = project.version.toString()
            desc = project.description
            released = Date().toString()
            vcsTag = "v${project.version}"
            gpg.apply {
                sign = true
                passphrase = prop("gpg.passphrase", "you know nothing, jon snow")
            }
            mavenCentralSync.apply {
                sync = true //[Default: true] Determines whether to sync the version to Maven Central.
                user = prop("oss.user.token", "WHO-ARE-YOU")
                password = prop("oss.user.password", "NO-PASSWORD")
                //Optional property. By default the staging repository is closed and artifacts are released to Maven Central. You can optionally turn this behaviour off (by puting 0 as value) and release the version manually.
                close = "1"
            }
        }
    }
    setPublications("maven")
    dryRun = false
    publish = true
    override = true
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
            groupId = project.group.toString()
            artifactId = project.name
            version = project.version.toString()
            artifact(tasks["sourcesJar"])
            artifact(tasks["javadocJar"])
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
