mkdir "../processed"

python hazm_lib.py NT-- Poems
python hazm_lib.py NT-- Queries
python parsivar_lib.py NT-- Poems
python parsivar_lib.py NT-- Queries

python hazm_lib.py NTR- Poems
python hazm_lib.py NTR- Queries
python parsivar_lib.py NTR- Poems
python parsivar_lib.py NTR- Queries

python hazm_lib.py NTRL Poems
python hazm_lib.py NTRL Queries
python parsivar_lib.py NTRL Poems
python parsivar_lib.py NTRL Queries

python hazm_lib.py NT-S Poems
python hazm_lib.py NT-S Queries
python hazm_lib.py NTRS Poems
python hazm_lib.py NTRS Queries