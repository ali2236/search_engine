package models

class ListStatistics<T>(val relevant : List<T>,val retrieved : List<T>, val totalDocuments : Int) {

    val truePositive : Int = relevant.intersect(retrieved.toSet()).size
    val falseNegative : Int = relevant.size - truePositive
    val falsePositive : Int = retrieved.size - truePositive
    val trueNegative : Int = totalDocuments - falsePositive

}