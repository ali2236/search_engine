package models

data class RelevanceAssessment(val queryId: String, val relevantDocsId: List<String>, val totalDocuments: Int) {
    fun precision(results: List<String>): Double {
        val stats = ListStatistics(relevantDocsId, results, totalDocuments)
        return stats.truePositive / stats.retrieved.size.toDouble()
    }

    fun recall(results: List<String>): Double {
        val stats = ListStatistics(relevantDocsId, results, totalDocuments)
        return stats.truePositive / stats.relevant.size.toDouble()
    }

    // MAP = (p@1 + p@2 + ... + p@k) / k
    fun meanAveragePrecision(results: List<String>): Double {
        val relevant = relevantDocsId.toSet()
        val k = results.toSet().intersect(relevant).size
        val precisionAt: (Int) -> Double = { i ->
            val related = results.subList(0, i).toSet().intersect(relevant).size
            if(relevant.contains(results[i-1])) related.toDouble() / i else 0.0
        }
        val result = (results.indices.map { precisionAt(it+1) }.sum() / k)
        return if(result.isNaN()) 0.0 else result
    }
}