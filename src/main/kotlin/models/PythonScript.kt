package models

class PythonScript(private val path: String) {

    fun call(args: List<String>): String {
        val args = arrayOf("python", path, *args.toTypedArray())
        val process = Runtime.getRuntime().exec(args)
        return process.inputReader().buffered().readText()
    }

}