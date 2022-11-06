package models

interface SearchEngine {

    fun getName(): String

    fun index(skip: Boolean)

    // return List of Result DocumentIds
    fun query(query: String): List<String>

}