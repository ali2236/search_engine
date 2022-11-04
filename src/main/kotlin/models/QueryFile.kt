package models

import java.io.File

class QueryFile(private val id: String, private val path: String) {

    private val file : File
        get() = File(path)

    override fun toString(): String {
        return file.readText()
    }

}