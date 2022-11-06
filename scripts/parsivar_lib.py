import os
import sys
import parsivar
import stopwords

ops = sys.argv[1]
docs = f"../data/{sys.argv[2]}"
output = f"../processed/parsivar/{sys.argv[2]}"

stop_words = stopwords.get_stopwords()


def process(text):
    if "N" in ops:
        text = parsivar.Normalizer().normalize(text)

    tokens = parsivar.Tokenizer().tokenize_words(text)

    if "R" in ops:
        stop_words = stopwords.get_stopwords()
        filtered_tokens = [w for w in tokens if w not in stop_words]
        tokens = filtered_tokens

    if "S" in ops or "L" in ops:
        stemmer = parsivar.FindStems()
        stemmed_tokens = [stemmer.convert_to_stem(w) for w in tokens]
        tokens = stemmed_tokens

    text = " ".join(tokens)
    return text


output = os.path.join(output, ops)
if not os.path.exists(output):
    os.mkdir(output)
    documents = os.listdir(docs)
    for doc in documents:
        with open(os.path.join(docs, doc), 'r', encoding='utf8') as d:
            t = d.read()
            pt = process(t)
            with open(os.path.join(output, doc), 'w', encoding='utf8') as pd:
                pd.write(pt)
                print(f'{doc} processed')
