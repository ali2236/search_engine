package models

import kotlin.io.path.Path

class DocumentDirectory(private val path: String) {

    val documents: List<DocumentFile>
        get() = Path(path).toFile().listFiles()
            ?.map { DocumentFile(it.name, it.path) }
            ?.toList()
            .orEmpty()
}