plugins {
    kotlin("jvm")
    id("jps-compatible")
}

dependencies {
    api(project(":core:metadata.jvm"))
    api(project(":core:deserialization.common"))
    api(project(":core:deserialization.common.clr"))
    implementation(project(":core:compiler.common.clr"))
    compileOnly(intellijCore())
    compileOnly(commonDependency("org.jetbrains.intellij.deps:asm-all"))

    implementation(project(":core:descriptors.clr"))
    api(project(":compiler:psi"))

}

sourceSets {
    "main" { projectDefault() }
    "test" {}
}
