package models

class QueryResultExporter {

    fun export(results: Map<String, List<RelevanceResult>>) {
        for (q in results.values.first().indices) {
            CsvExporter("results/queries/query$q.csv").apply {
                write(
                    listOf(
                        listOf(" ") + results.keys.map { it.replace('p', '`') },
                        listOf("Precision") + results.values.map { it[q].precision.toString() },
                        listOf("Recall") + results.values.map { it[q].recall.toString() },
                        listOf("F-Measure") + results.values.map { it[q].fMeasure.toString() },
                        listOf("MAP") + results.values.map { it[q].MAP.toString() },
                    ),
                )
            }
        }

        // rotated
        for (q in results.values.first().indices) {
            CsvExporter("results/plot_queries/query$q.csv").apply {
                val enginesResult = results.keys.map {
                    val values = results[it]!![q].run {
                        listOf(precision, recall, fMeasure, MAP).map { it.toString() }
                    }
                    listOf(it.replace('p', '`')) + values
                }.toMutableList()
                enginesResult.add(0, listOf("Search Engine", "Precision", "Recall", "F-Measure", "MAP"))
                write(enginesResult)
            }
        }

        /*val resultHeader = listOf("Precision", "Recall", "F-Measure", "MAP")*/
    }

}