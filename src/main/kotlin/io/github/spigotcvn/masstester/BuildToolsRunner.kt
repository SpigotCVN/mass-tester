package io.github.spigotcvn.masstester

import kotlin.io.path.pathString

class BuildToolsRunner(private val version: MinecraftVersion) {
    private val javaBinary = when (version.javaVersion) {
        8 -> MassTester.config.javaConfig.java8Path
        16 -> MassTester.config.javaConfig.java16Path
        17 -> MassTester.config.javaConfig.java17Path
        21 -> MassTester.config.javaConfig.java21Path
        else -> error("Invalid java version found for $version! Please report this error!")
    }
    private val command = listOf(javaBinary.pathString, "-jar", MassTester.config.buildToolsJarPath.pathString)
    private val buildToolsArgs = listOf("--rev", version.toString(), "--remapped")
    lateinit var process: Process

    fun run() {
        println("Starting BuildTools for Minecraft version $version")
        process = ProcessBuilder(command + buildToolsArgs)
            .directory(MassTester.config.buildToolsPath.toFile())
            .redirectOutput(ProcessBuilder.Redirect.INHERIT)
            .redirectError(ProcessBuilder.Redirect.INHERIT)
            .start()
        val result = process.waitFor()
        if (result != 0) error("BuildTools failed for version $version! Please check the log above!")
        else {
            println("BuildTools finished successfully for Minecraft version $version!")
            ProcessManager.processFinished(version)
        }
    }
}