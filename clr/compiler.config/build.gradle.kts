plugins {
    kotlin("jvm")
    id("jps-compatible")
    id("gradle-plugin-compiler-dependency-configuration")
}

dependencies {
    api(project(":compiler:config"))
    api(project(":core:compiler.common.clr"))
}

sourceSets {
    "main" { projectDefault() }
    "test" { }
}
