import org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile
import java.net.URI

plugins {
    kotlin("jvm")
    id("jps-compatible")
    kotlin("plugin.serialization")
}

dependencies {
    compileOnly(project(":core:util.runtime"))

    implementation(kotlinStdlib())
    implementation(kotlinxCollectionsImmutable())
}