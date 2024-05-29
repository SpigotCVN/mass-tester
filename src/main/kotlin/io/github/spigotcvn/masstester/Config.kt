package io.github.spigotcvn.masstester

import java.nio.file.Path
import kotlin.io.path.Path

data class Config(
    private val cvnPluginLocation: String,
    private val testPluginLocation: String,
    private val serversLocation: String,
    private val buildToolsLocation: String,
    val buildToolsJarName: String,
    val javaConfig: JavaConfig
) {
    fun getServerPath(version: MinecraftVersion): Path =
        Path.of(serversLocation.replace("\$version", version.toString()))

    val cvnPluginPath = Path(cvnPluginLocation)
    val testPluginPath = Path(testPluginLocation)
    val buildToolsPath = Path(buildToolsLocation)
    val buildToolsJarPath = Path(buildToolsLocation, buildToolsJarName)
}

data class JavaConfig(
    private val java8Location: String,
    private val java16Location: String,
    private val java17Location: String,
    private val java21Location: String
) {
    val java8Path = Path(java8Location, "/bin/java")
    val java16Path = Path(java16Location, "/bin/java")
    val java17Path = Path(java17Location, "/bin/java")
    val java21Path = Path(java21Location, "/bin/java")
}