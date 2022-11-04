package models

data class RelevanceResult(val precision : Double, val recall: Double, val MAP : Double){
    val fMeasure : Double
        get() = (2*precision*recall)/(precision+recall)
}
