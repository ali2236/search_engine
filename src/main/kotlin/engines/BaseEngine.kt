package engines

import models.Indexer
import models.PreProcessor
import models.SearchEngine
import models.hazm
import org.apache.lucene.analysis.standard.StandardAnalyzer
import org.apache.lucene.index.DirectoryReader
import org.apache.lucene.queryparser.classic.QueryParser
import org.apache.lucene.search.IndexSearcher

class BaseSearchEngine(private val indexName: String, private val preProcessor: PreProcessor) : SearchEngine {

    private lateinit var indexDirectory: DirectoryReader

    override fun getName(): String = indexName

    override fun index() {
        indexDirectory = Indexer(getName(), preProcessor).let {
            it.index("data/Poems")
            it.directory
        }
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