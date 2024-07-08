plugins {
    kotlin("jvm")
    id("jps-compatible")
}

project.configureJvmToolchain(JdkMajorVersion.JDK_1_8)

dependencies {
    api(project(":kotlin-annotations-clr"))
    api(project(":core:descriptors"))
    api(project(":core:deserialization"))
    api(project(":core:compiler.common.clr"))
    api(project(":core:deserialization.common.clr"))
    api(project(":core:util.runtime"))
    api(commonDependency("javax.inject"))
}

sourceSets {
    "main" { projectDefault() }
    "test" {}
}
