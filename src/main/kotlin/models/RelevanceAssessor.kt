package models

import engines.SearchEngine

class RelevanceAssessor(
    private val relevanceAssessment: RelevanceAssessmentFile,
) {

    val results = mutableMapOf<String, List<RelevanceResult>>()

    fun test(searchEngine: SearchEngine, queries: QueryDirectory) : List<RelevanceResult> {
        val r = relevanceAssessment.assessments
            .map { ra ->
                val query = queries.get(ra.queryId).toString()
                val results = searchEngine.query(query)
                val rr = RelevanceResult(
                    ra.precision(results),
                    ra.recall(results),
                    ra.meanAveragePrecision(results),
                )
                rr
            }
        results[searchEngine.getName()] = r
        return r
    }
}