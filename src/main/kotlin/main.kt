import engines.BaseSearchEngine
import models.*

fun main(args: Array<String>) {
    val totalDocuments = DocumentDirectory("data/Poems").documents.size

    val engines = listOf(
        BaseSearchEngine("SE0") { t -> "data/$t" },
        BaseSearchEngine("SE1", hazm("NT--")),
        BaseSearchEngine("SE1p", parsivar("NT--")),
        BaseSearchEngine("SE2", hazm("NTR-")),
        BaseSearchEngine("SE2p", parsivar("NTR-")),
        BaseSearchEngine("SE3", hazm("NTRL")),
        BaseSearchEngine("SE3p", parsivar("NTRL")),
        BaseSearchEngine("SE4", hazm("NT-S")),
        BaseSearchEngine("SE4p", hazm("NTRS")),
    ).onEach {
        // set to [true] if index exists
        it.index(true)
    }

    // Test
    val ra = RelevanceAssessmentFile("data/RelevanceAssesment/RelevanceAssesment", totalDocuments)
    val assessor = RelevanceAssessor(ra)

    engines.forEach {
        println("assessing ${it.getName()}")
        val queries = QueryDirectory(it.pathBuilder("Queries"))
        assessor.test(it, queries)
        println(assessor.results)
        println(assessor.getMeanResult(it.getName()))
    }



}