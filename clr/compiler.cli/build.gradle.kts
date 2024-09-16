plugins {
    kotlin("jvm")
    id("jps-compatible")
}

dependencies {
    api(project(":compiler:util"))
    api(project(":compiler:cli-common"))
    api(project(":compiler:cli"))
    api(project(":compiler:frontend"))
    api(project(":compiler:backend-common"))
    api(project(":compiler:fir:fir-serialization"))
    api(project(":compiler:ir.backend.common"))
    api(project(":compiler:ir.serialization.clr"))
    api(project(":compiler:ir.tree"))
    // api(project(":compiler:backend.clr"))

    compileOnly(intellijCore())

    testImplementation(libs.junit4)
    testImplementation(platform(libs.junit.bom))
    testImplementation(libs.junit.jupiter.api)
    testImplementation(libs.junit.platform.launcher)

    testRuntimeOnly(libs.junit.jupiter.engine)
    testRuntimeOnly(libs.junit.vintage.engine)

    testApi(projectTests(":compiler:tests-common"))
    testApi(projectTests(":compiler:tests-common-new"))
}

task<Test> {
    useJUnitPlatform()
}
