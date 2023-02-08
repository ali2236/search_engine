# Persian Poem Search Engine

IR Project #1

### Requirements
- python 3.9
- JDK 19
- Kotlin 1.6

### How to Run
1. extract `PersianPoemsData.rar` in this folder
1. install required python libraries from `requirments.txt`
1. run `preprocessing.bat` from `/scripts` folder
1. use intellij for kotlin compiler and resolving maven dependencies(like lucene for example)
1. go to `main.kt` set `skip=false`
1. go to `models/SearchEngine.kt` and set query `n` top results 
1. run `main.kt`
1. results will be outputted to `/results`
1. run `/scripts/make_plots/py` to generate plots