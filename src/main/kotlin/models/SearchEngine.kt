package models

interface SearchEngine {

    fun getName() : String

    fun index()

    // return List of Result DocumentIds
    fun query(query: String): List<String>

}