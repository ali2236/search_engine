import engines.SearchEngine
import models.*

fun main(args: Array<String>) {
    val totalDocuments = DocumentDirectory("data/Poems").documents.size

    val engines = listOf(
        SearchEngine("SE0") { t -> "data/$t" },
        SearchEngine("SE1", hazm("NT--")),
        SearchEngine("SE1p", parsivar("NT--")),
        SearchEngine("SE2", hazm("NTR-")),
        SearchEngine("SE2p", parsivar("NTR-")),
        SearchEngine("SE3", hazm("NTRL")),
        SearchEngine("SE3p", parsivar("NTRL")),
        SearchEngine("SE4", hazm("NT-S")),
        SearchEngine("SE4p", hazm("NTRS")),
    ).onEach {
        // set to [true] if index exists
        it.index(skip=true)
    }

    // Test
    val ra = RelevanceAssessmentFile("data/RelevanceAssesment/RelevanceAssesment", totalDocuments)
    val assessor = RelevanceAssessor(ra)
    val seExporter = AssessmentExporter()
    val qExporter = QueryResultExporter()

    engines.forEach {
        val name = it.getName()
        println("assessing $name")
        val queries = QueryDirectory(it.pathBuilder("Queries"))
        assessor.test(it, queries).let { results ->
            seExporter.export(name, results)
        }
    }

    qExporter.export(assessor.results)

}