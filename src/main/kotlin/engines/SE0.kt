package engines

import models.Indexer
import models.SearchEngine
import org.apache.lucene.analysis.core.WhitespaceAnalyzer
import org.apache.lucene.analysis.standard.StandardAnalyzer
import org.apache.lucene.index.DirectoryReader
import org.apache.lucene.queryparser.classic.QueryParser
import org.apache.lucene.search.IndexSearcher

class SE0 : SearchEngine {
    override fun getName(): String = "SE0"

    private lateinit var indexDirectory: DirectoryReader

    override fun index() {
        indexDirectory = Indexer("SE0").let {
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