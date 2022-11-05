package models

import java.io.File
import java.util.concurrent.TimeUnit

class PythonScript(private val path: String) {

    fun call(args: List<String>): String? {
        return (listOf("py", path) + args).runCommand()
    }

}

private fun List<String>.runCommand(
    workingDir: File = File("."),
    timeoutAmount: Long = 60,
    timeoutUnit: TimeUnit = TimeUnit.SECONDS
): String? = runCatching {
    ProcessBuilder(this)
        .directory(workingDir)
        .redirectOutput(ProcessBuilder.Redirect.PIPE)
        .redirectError(ProcessBuilder.Redirect.PIPE)
        .start().also { it.waitFor(timeoutAmount, timeoutUnit) }
        .inputReader(Charsets.UTF_8).buffered().readText()
}.onFailure { it.printStackTrace() }.getOrNull()