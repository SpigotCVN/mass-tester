package io.github.spigotcvn.masstester

object ProcessManager {
    private val buildToolsProcesses = mutableMapOf<MinecraftVersion, BuildToolsRunner>()

    fun startNewBuildToolsProcess(version: MinecraftVersion) {
        buildToolsProcesses[version] = BuildToolsRunner(version)
        buildToolsProcesses[version]?.run()
    }

    fun processFinished(version: MinecraftVersion) {
        buildToolsProcesses.remove(version)
    }
}