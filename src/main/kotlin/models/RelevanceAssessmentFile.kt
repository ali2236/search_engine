package models

import java.io.File

class RelevanceAssessmentFile(private val path: String, private val totalDocuments : Int) {

    val assessments : List<RelevanceAssessment>
        get() = File(path).readText()
            .split("\n")
            .chunked(3)
            .filter { it.size >= 2 }
            .map { RelevanceAssessment(it[0], it[1].split(" "), totalDocuments) }
}