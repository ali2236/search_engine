package models

fun hazm(ops: String): (String) -> String {
    return { type -> "processed/hazm/$type/$ops" }
}

fun parsivar(ops: String): (String) -> String {
    return { type -> "processed/parsivar/$type/$ops" }
}

