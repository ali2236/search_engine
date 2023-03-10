import os
import sys
import parsivar
import stopwords
import datetime

ops = sys.argv[1]
dtype = sys.argv[2]
docs = f"../PersianPoemsData/{dtype}"
output = f"../processed/parsivar"

stop_words = stopwords.get_stopwords()
normalizer = parsivar.Normalizer()
tokenizer = parsivar.Tokenizer()
stemmer = parsivar.FindStems()


def process(text):
    if "N" in ops:
        text = normalizer.normalize(text)

    tokens = tokenizer.tokenize_words(text)

    if "R" in ops:
        filtered_tokens = [w for w in tokens if w not in stop_words]
        tokens = filtered_tokens

    if "S" in ops or "L" in ops:
        stemmed_tokens = [stemmer.convert_to_stem(w) for w in tokens]
        tokens = stemmed_tokens

    text = " ".join(tokens)
    return text


if not os.path.exists(output):
    os.mkdir(output)
output = os.path.join(output, dtype)
if not os.path.exists(output):
    os.mkdir(output)
output = os.path.join(output, ops)
if not os.path.exists(output):
    os.mkdir(output)
documents = os.listdir(docs)
start = datetime.datetime.now()
if not os.path.exists(output):
    os.mkdir(output)
documents = os.listdir(docs)
i = 0
for doc in documents:
    with open(os.path.join(docs, doc), 'r', encoding='utf8') as d:
        t = d.read()
        pt = process(t)
        with open(os.path.join(output, doc), 'w', encoding='utf8') as pd:
            pd.write(pt)
            if i % 500 == 0:
                print(f'{i} docs processed')
            i += 1
end = datetime.datetime.now()
elapsed_time = end - start
with open("times.txt", 'a', encoding='utf8') as t:
    t.write(f'parsivar-{dtype}-{ops}: {elapsed_time} seconds\n')