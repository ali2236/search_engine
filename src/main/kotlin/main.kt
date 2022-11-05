import engines.SE0
import models.*

fun main(args: Array<String>) {

    PythonScript("scripts/hazm_lib.py").call(listOf("NR--", "چه گل های و از که زیبایی.")).let {
        println(it)
    }

    return
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