import engines.BaseSearchEngine
import models.*

fun main(args: Array<String>) {
    val totalDocuments = DocumentDirectory("data/Poems").documents.size

    val engines = listOf(
        BaseSearchEngine("SE0", "data/Poems"),
        BaseSearchEngine("SE1", hazm("NT--")),
        BaseSearchEngine("SE1p", parsivar("NT--")),
        BaseSearchEngine("SE2", hazm("NTR-")),
        BaseSearchEngine("SE2p", parsivar("NTR-")),
        BaseSearchEngine("SE3", hazm("NTRL")),
        BaseSearchEngine("SE3p", parsivar("NTRL")),
        BaseSearchEngine("SE4", hazm("NT-S")),
        BaseSearchEngine("SE4p", hazm("NTRS")),
    ).onEach {
        // comment this line if index exists
        it.index()
    }

    println("done")

/*    // Test
    val ra = RelevanceAssessmentFile("data/RelevanceAssesment/RelevanceAssesment", totalDocuments)
    val queries = QueryDirectory("data/Queries")
    val assessor = RelevanceAssessor(queries, ra)

    assessor.test(engine)
    println(assessor.results)
    println(assessor.getMeanResult(engine.getName()))*/
}