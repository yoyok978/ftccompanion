plugins {
    id("java")
    id("org.jetbrains.kotlin.jvm") version "1.9.24"
    id("org.jetbrains.intellij") version "1.17.3"
    id("io.freefair.lombok") version "8.6"
}

group = "com.aradiors.ftccompanion"
version = "1.4.0"

repositories {
    mavenCentral()
}

// Configure Gradle IntelliJ Plugin
// Read more: https://plugins.jetbrains.com/docs/intellij/tools-gradle-intellij-plugin.html
intellij {
    // Switched to IC (IntelliJ Community) to avoid a known bug in the v1.x Gradle plugin
    // when parsing recent Android Studio (AI) product-info.json files.
    // The compiled plugin will still work perfectly in Android Studio.
    version.set("2024.2.1")
    type.set("IC") // Target IDE Platform

    plugins.set(listOf("com.intellij.java"))
}

tasks {
    // Set the JVM compatibility versions
    withType<JavaCompile> {
        sourceCompatibility = "17"
        targetCompatibility = "17"
    }
    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.jvmTarget = "17"
    }

    patchPluginXml {
        sinceBuild.set("242")
        untilBuild.set("253.*")
    }

    signPlugin {
        val certChainEnv = providers.environmentVariable("CERTIFICATE_CHAIN_PATH")
        val privateKeyEnv = providers.environmentVariable("PRIVATE_KEY_PATH")
        val keyPassEnv = providers.environmentVariable("PRIVATE_KEY_PASSWORD")

        if (certChainEnv.isPresent && privateKeyEnv.isPresent && keyPassEnv.isPresent) {
            certificateChainFile.set(file(certChainEnv))
            privateKeyFile.set(file(privateKeyEnv))
            password.set(keyPassEnv)
        }
    }

    publishPlugin {
        token.set(System.getenv("PUBLISH_TOKEN"))
    }

    buildSearchableOptions {
        enabled = false
    }
}