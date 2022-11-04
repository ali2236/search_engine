package models

class RelevanceAssessor(
    private val queries: QueryDirectory,
    private val relevanceAssessment: RelevanceAssessmentFile,
) {

    val results = mutableMapOf<String, List<RelevanceResult>>()

    fun test(searchEngine: SearchEngine) {
        results[searchEngine.getName()] = relevanceAssessment.assessments
            .map { ra ->
                val query = queries.get(ra.queryId).toString()
                val results = searchEngine.query(query)
                return@map RelevanceResult(
                    ra.precision(results),
                    ra.recall(results),
                    ra.meanAveragePrecision(results),
                )
            }
    }

    fun getMeanResult(engine: String): RelevanceResult {
        val result = results[engine]!!
        return RelevanceResult(
            result.map { it.precision }.average(),
            result.map { it.recall }.average(),
            result.map { it.MAP }.average(),
        )
    }
}