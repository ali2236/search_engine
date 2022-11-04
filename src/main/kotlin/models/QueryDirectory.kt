package models

import kotlin.io.path.Path

class QueryDirectory(private val path: String) {

    val queries: List<QueryFile>
        get() = Path(path).toFile().listFiles()
            ?.map { QueryFile(it.name, it.path) }
            ?.toList()
            .orEmpty()

    fun get(queryId: String): QueryFile {
        return QueryFile(queryId, Path(path, queryId).toString())
    }
}