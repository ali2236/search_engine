package engines

import models.Indexer
import models.SearchEngine
import org.apache.lucene.analysis.standard.StandardAnalyzer
import org.apache.lucene.index.DirectoryReader
import org.apache.lucene.queryparser.classic.QueryParser
import org.apache.lucene.search.IndexSearcher

class BaseSearchEngine(private val indexName: String, private val path: String) : SearchEngine {

    private lateinit var indexDirectory: DirectoryReader

    override fun getName(): String = indexName

    override fun index() {
        println("${getName()}: indexing...")
        indexDirectory = Indexer(getName()).let {
            it.index(path)
            it.directory
        }
        println("${getName()}: indexing done!")
    }

    override fun query(query: String): List<String> {
        val searcher = IndexSearcher(indexDirectory)
        val parser = QueryParser("content", StandardAnalyzer())
        val q = parser.parse(query)

        return searcher.search(q, 25).scoreDocs.map {
            searcher.doc(it.doc).get("id")
        }
    }
}