package engines

import models.Indexer
import org.apache.lucene.analysis.core.WhitespaceAnalyzer
import org.apache.lucene.analysis.standard.StandardAnalyzer
import org.apache.lucene.index.DirectoryReader
import org.apache.lucene.queryparser.classic.QueryParser
import org.apache.lucene.queryparser.surround.query.AndQuery
import org.apache.lucene.search.IndexSearcher

class SearchEngine(private val indexName: String, val pathBuilder: (String) -> String) {

    private lateinit var indexDirectory: DirectoryReader

    fun getName(): String = indexName

    fun index(skip: Boolean) {
        println("${getName()}: indexing...")
        indexDirectory = Indexer(getName()).let {
            if (!skip) {
                it.index(pathBuilder("Poems"))
            }
            it.directory
        }
        println("${getName()}: indexing done!")
    }

    fun query(query: String): List<String> {
        val searcher = IndexSearcher(indexDirectory)
        val parser = QueryParser("content", WhitespaceAnalyzer())
        val q = parser.parse(query)

        return searcher.search(q, 10).scoreDocs.map {
            searcher.doc(it.doc).get("id")
        }
    }
}