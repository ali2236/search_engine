package models

class QueryResultExporter {

    fun export(results: Map<String, List<RelevanceResult>>) {
        for (q in results.values.indices){
            val resultHeader = listOf(" ") + results.keys
            val precision = listOf("Precision") + results.values.map { it[q].precision.toString() }
            CsvExporter("results/query$q.csv").apply {
                write(listOf(resultHeader, precision))
            }
        }

        /*val resultHeader = listOf("Precision", "Recall", "F-Measure", "MAP")*/
    }

}