package models

import java.io.File

class CsvExporter(val path: String, val separator: String = ",") {

    private val file: File
        get() = File(path)

    fun write(data: List<List<String>>) {
        val csvText = StringBuffer().apply {
            data.forEach { row ->
                val csvRow = row.joinToString(separator)
                append(csvRow)
                append('\n')
            }
        }

        file.apply {
            createNewFile()
            file.writeText(csvText.toString())
        }
    }

}