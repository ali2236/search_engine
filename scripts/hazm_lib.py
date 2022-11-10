import sys
import hazm
import stopwords
import os
import datetime

ops = sys.argv[1]
dtype = sys.argv[2]
docs = f"../PersianPoemsData/{dtype}"
output = f"../processed/hazm"

stop_words = stopwords.get_stopwords()
normalizer = hazm.Normalizer()
tokenizer = hazm.WordTokenizer()
lemmatizer = hazm.Lemmatizer()
stemmer = hazm.Stemmer()


def process(src_text):
    text = src_text
    if "N" in ops:
        text = normalizer.normalize(text)

    tokens = tokenizer.tokenize(text)

    if "R" in ops:
        filtered_tokens = [w for w in tokens if w not in stop_words]
        tokens = filtered_tokens

    if "S" in ops:
        stemmed_tokens = [stemmer.stem(w) for w in tokens]
        tokens = stemmed_tokens

    if "L" in ops:
        lemmatized_tokens = [lemmatizer.lemmatize(w) for w in tokens]
        tokens = lemmatized_tokens

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
    t.write(f'hazm-{dtype}-{ops}: {elapsed_time} seconds\n')
