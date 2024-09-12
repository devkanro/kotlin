import org.jetbrains.kotlin.ideaExt.idea

plugins {
    kotlin("jvm")
    id("jps-compatible")
}

dependencies {
    compileOnly(project(":core:descriptors"))
    compileOnly(project(":core:descriptors.clr"))
    compileOnly(project(":compiler:fir:cones"))
    compileOnly(project(":compiler:fir:resolve"))
    compileOnly(project(":compiler:fir:providers"))
    compileOnly(project(":compiler:fir:semantics"))
    compileOnly(project(":compiler:fir:tree"))
    compileOnly(project(":clr:il:tree"))
    compileOnly(project(":compiler:fir:fir-serialization"))
    compileOnly(project(":compiler:fir:fir-deserialization"))

    compileOnly(intellijCore())
}

optInToObsoleteDescriptorBasedAPI()

val generationRoot = projectDir.resolve("tests-gen")

sourceSets {
    "main" { projectDefault() }
    "test" {
        projectDefault()
        this.java.srcDir(generationRoot.name)
    }
}

if (kotlinBuildProperties.isInJpsBuildIdeaSync) {
    apply(plugin = "idea")
    idea {
        this.module.generatedSourceDirs.add(generationRoot)
    }
}

fun Test.configure(configureJUnit: JUnitPlatformOptions.() -> Unit = {}) {
    dependsOn(":dist")
    workingDir = rootDir
    useJUnitPlatform {
        configureJUnit()
    }
}

projectTest(
    jUnitMode = JUnitMode.JUnit5,
    defineJDKEnvVariables = listOf(JdkMajorVersion.JDK_1_8, JdkMajorVersion.JDK_11_0, JdkMajorVersion.JDK_17_0)
) {
    configure()
}

projectTest("aggregateTests", jUnitMode = JUnitMode.JUnit5) {
    configure {
        excludeTags("FirPsiCodegenTest")
    }
}

projectTest("nightlyTests", jUnitMode = JUnitMode.JUnit5) {
    configure {
        includeTags("FirPsiCodegenTest")
    }
}

testsJar()
