plugins {
    kotlin("jvm")
    id("jps-compatible")
}

dependencies {
    api(project(":core:compiler.common.clr"))
    api(project(":compiler:config.clr"))
    api(commonDependency("org.jetbrains.intellij.deps:asm-all"))
    api(libs.guava)
    compileOnly(intellijCore())
}

sourceSets {
    "main" { projectDefault() }
    "test" {}
}
