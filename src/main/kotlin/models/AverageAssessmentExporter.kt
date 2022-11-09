package models

class AverageAssessmentExporter {

    fun export(results: Map<String, List<RelevanceResult>>) {
        val resultHeader = listOf("Search Engine", "Precision", "Recall", "F-Measure", "MAP")
        val resultBody = results.map { entry ->
            val avg = entry.value.run {
                RelevanceResult(
                    map { it.precision }.average(),
                    map { it.recall }.average(),
                    map { it.MAP }.average(),
                ).run {
                    listOf(precision, recall, fMeasure, MAP).map { n ->
                        n.toString()
                    }
                }
            }
            listOf(entry.key) + avg
        }

        val resultTable = resultBody.toMutableList().apply {
            add(0, resultHeader)
        }

        CsvExporter("results/avg.csv").apply {
            write(resultTable)
        }
    }

}