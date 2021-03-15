val ktor_version: String by project
val logback_version: String by project

plugins {
    val kotlinVersion = "1.4.31"
    application
    kotlin("jvm") version kotlinVersion
    kotlin("plugin.serialization") version kotlinVersion
}

group = "nickcipollo.com.test"
version = "0.0.1"
application {
    mainClass.set("io.ktor.server.netty.EngineMain")
}

repositories {
    mavenLocal()
    jcenter()
    maven { url = uri("https://kotlin.bintray.com/ktor") }
}

dependencies {
    implementation("ch.qos.logback:logback-classic:$logback_version")
    implementation("io.ktor:ktor-network-tls-certificates:$ktor_version")
    implementation("io.ktor:ktor-server-core:$ktor_version")
    implementation("io.ktor:ktor-server-netty:$ktor_version")
    implementation("io.ktor:ktor-serialization:$ktor_version")
    testImplementation("io.ktor:ktor-server-tests:$ktor_version")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.1.0-RC")
}

tasks {
    register<JavaExec>("generateJks")
    named<JavaExec>("generateJks") {
        dependsOn("classes")
        classpath(sourceSets.main.get().runtimeClasspath)
        main = "nickcipollo.com.test.CertificateGenerator"
    }
}

getTasksByName("run", false).first().dependsOn("generateJks")