package models

import org.apache.lucene.document.Document
import org.apache.lucene.document.Field
import org.apache.lucene.document.StringField
import org.apache.lucene.document.TextField
import java.io.File

class DocumentFile(
    private val id: String,
    private val path: String
) {

    val file: File
        get() = File(path)

    fun toDocument(preProcessor: PreProcessor): Document = Document().apply {
        add(StringField("id", id, Field.Store.YES))
        add(StringField("path", path, Field.Store.YES))
        add(TextField("content", preProcessor(file.readText()).reader()))
    }

    override fun toString(): String {
        return "Poem #${id}: $path"
    }
}
