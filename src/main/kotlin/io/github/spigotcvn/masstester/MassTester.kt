package io.github.spigotcvn.masstester

import com.sksamuel.hoplite.ConfigLoaderBuilder
import com.sksamuel.hoplite.PropertySource
import io.github.spigotcvn.masstester.MinecraftVersion.Companion.VERSIONS
import java.io.File

object MassTester {
    val config = ConfigLoaderBuilder.default().addSource(PropertySource.file(File("config.yml"))).build()
        .loadConfigOrThrow<Config>()
}

fun main() {
    println("MassTester starting!")
    println("Versions found: $VERSIONS")
    VERSIONS.forEach {
        ProcessManager.startNewBuildToolsProcess(it)
    }
}