plugins {
    kotlin("jvm") version "1.9.20"
    application
}

group = "io.github.spigotcvn"
version = "1.0-SNAPSHOT"

application {
    mainClass = "io.github.spigotcvn.masstester.MassTesterKt"
}

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-client-core:2.3.11")
    implementation("io.ktor:ktor-client-cio:2.3.11")
    implementation("com.sksamuel.hoplite:hoplite-core:2.7.5")
    implementation("com.sksamuel.hoplite:hoplite-yaml:2.7.5")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}