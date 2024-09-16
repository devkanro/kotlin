plugins {
    kotlin("jvm")
    id("jps-compatible")
}

dependencies {
    //api(project(":compiler:cli-base"))
    api(project(":compiler:util"))
    api(project(":compiler:frontend"))
    api(project(":compiler:backend"))
    api(project(":compiler:backend-common"))
    api(project(":compiler:ir.tree"))
    api(project(":compiler:ir.backend.common"))
    api(project(":compiler:ir.inline"))
    api(project(":compiler:ir.serialization.common"))
    api(project(":compiler:ir.serialization.clr"))

    compileOnly(intellijCore())
}

optInToUnsafeDuringIrConstructionAPI()

sourceSets {
    "main" { projectDefault() }
    "test" {}
}
