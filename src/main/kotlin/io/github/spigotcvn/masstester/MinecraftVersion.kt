package io.github.spigotcvn.masstester

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.runBlocking
import java.util.regex.Pattern

data class MinecraftVersion(val major: Int, val minor: Int, val rev: Int) {
    override fun toString() = buildString {
        if (major != 0) append("$major")
        if (minor != 0) append(".$minor")
        if (rev != 0) append(".$rev")
    }

    val javaVersion: Int get() {
        if(minor < 17) return 8
        if(minor == 17) return 16
        if(minor < 20 && rev < 5) return 17
        return 21
    }

    companion object {
        fun fromString(input: String): MinecraftVersion {
            val numbers = input.split(".").map(String::toInt)
            return MinecraftVersion(numbers.getOrNull(0) ?: 0, numbers.getOrNull(1) ?: 0, numbers.getOrNull(2) ?: 0)
        }

        private fun getVersions(): List<MinecraftVersion> {
            val url = "https://hub.spigotmc.org/versions/"
            val versionPattern = Pattern.compile("([0-9]+\\.[0-9]+\\.[0-9]+|[0-9]+\\.[0-9]+)")

            lateinit var response: String

            runBlocking {
                response = HttpClient(CIO).use { client ->
                    client.get(url).bodyAsText()
                }
            }

            val matcher = versionPattern.matcher(response)

            return buildList {
                while (matcher.find()) {
                    add(fromString(matcher.group()))
                }
            }
        }

        val VERSIONS = getVersions().distinct()
    }
}
