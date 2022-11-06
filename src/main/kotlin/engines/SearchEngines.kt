package engines

import models.hazm
import models.noPreProcessing
import models.parsivar

val engines = listOf(
    BaseSearchEngine("SE0", ::noPreProcessing),
    BaseSearchEngine("SE1", hazm("NT--")),
    BaseSearchEngine("SE1p", parsivar("NT--")),
    BaseSearchEngine("SE2", hazm("NTR-")),
    BaseSearchEngine("SE2p", parsivar("NTR-")),
    BaseSearchEngine("SE3", hazm("NTRL")),
    BaseSearchEngine("SE3p", parsivar("NTRL")),
    BaseSearchEngine("SE4", hazm("NT-S")),
    BaseSearchEngine("SE4p", hazm("NTRS")),
)