package models

class PythonScript(private val path: String) {

    fun call(args: List<String>): String {
        val pythonArgs = arrayOf("py", path, *args.toTypedArray())
        val process = Runtime.getRuntime().exec(pythonArgs)
        return process.inputReader().buffered().readText()
    }

}