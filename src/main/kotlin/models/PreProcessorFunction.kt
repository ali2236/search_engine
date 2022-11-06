package models

typealias PreProcessor = (String) -> String

fun noPreProcessing(text: String): String {
    return text
}

fun hazm(ops: String): PreProcessor {
    return { text -> PythonScript("scripts/hazm_lib.py").call(listOf(ops, text))!! }
}

fun parsivar(ops: String): PreProcessor {
    return { text -> PythonScript("scripts/parsivar_lib.py").call(listOf(ops, text))!! }
}

