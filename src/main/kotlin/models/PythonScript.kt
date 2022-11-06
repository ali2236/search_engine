package models

class PythonScript(path: String, args: List<String>) {

    private val process = ProcessBuilder(listOf("python", path) + args)
        .redirectOutput(ProcessBuilder.Redirect.PIPE)
        .redirectError(ProcessBuilder.Redirect.PIPE)
        .start()

    fun call(text: String): String {
        process.outputWriter().buffered().write(text)
        return process.inputReader(Charsets.UTF_8).buffered().readText()
    }

}