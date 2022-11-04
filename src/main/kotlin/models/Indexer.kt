package models

import org.apache.lucene.analysis.core.WhitespaceAnalyzer
import org.apache.lucene.analysis.standard.StandardAnalyzer
import org.apache.lucene.index.DirectoryReader
import org.apache.lucene.index.IndexWriter
import org.apache.lucene.index.IndexWriterConfig
import org.apache.lucene.store.Directory
import org.apache.lucene.store.FSDirectory
import java.io.Closeable
import kotlin.io.path.Path

class Indexer(private val id: String) : Closeable {

    private val indexDirectory: Directory
    private var indexer: IndexWriter

    val directory : DirectoryReader
        get() = DirectoryReader.open(indexDirectory)

    init {
        indexDirectory = FSDirectory.open(Path("indexes/$id"))
        val analyzer = WhitespaceAnalyzer()
        val config = IndexWriterConfig(analyzer)
            .apply {
                openMode = IndexWriterConfig.OpenMode.CREATE
            }
        indexer = IndexWriter(indexDirectory, config)
    }

    fun index(directoryPath: String) {
        DocumentDirectory(directoryPath).documents
            .map { it.toDocument() }
            .forEach { doc -> indexer.addDocument(doc) }
        indexer.flush()
        indexer.close()
    }

    override fun close() = indexer.close()
}