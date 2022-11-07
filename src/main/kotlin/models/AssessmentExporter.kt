package models

class AssessmentExporter {

    fun export(assessmentId: String, results: List<RelevanceResult>) {
        val resultHeader = listOf("Precision", "Recall", "F-Measure", "MAP")
        val resultBody = results.map {
            listOf(it.precision, it.recall, it.fMeasure, it.MAP).map { n ->
                n.toString()
            }
        }

        val resultTable = resultBody.toMutableList().apply {
            add(0, resultHeader)
        }

        CsvExporter("results/se/$assessmentId.csv").apply {
            write(resultTable)
        }
    }

}