import engines.SE0
import models.DocumentDirectory
import models.QueryDirectory
import models.RelevanceAssessmentFile
import models.RelevanceAssessor

fun main(args: Array<String>) {

    val totalDocuments = DocumentDirectory("data/Poems").documents.size

    val engine = SE0()
    // create indexer
    engine.index()

    // Test
    val ra = RelevanceAssessmentFile("data/RelevanceAssesment/RelevanceAssesment", totalDocuments)
    val queries = QueryDirectory("data/Queries")
    val assessor = RelevanceAssessor(queries, ra)

    assessor.test(engine)
    println(assessor.results)
    println(assessor.getMeanResult(engine.getName()))
}