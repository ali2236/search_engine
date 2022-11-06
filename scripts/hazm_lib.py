import sys
import hazm
import stopwords
import os

ops = sys.argv[1]
docs = f"../data/{sys.argv[2]}"
output = f"../processed/hazm/{sys.argv[2]}"

stop_words = stopwords.get_stopwords()

def process(text):
    if "N" in ops:
        text = hazm.Normalizer().normalize(text)

    tokens = hazm.WordTokenizer().tokenize(text)

    if "R" in ops:
        filtered_tokens = [w for w in tokens if w not in stop_words]
        tokens = filtered_tokens

    if "S" in ops:
        stemmer = hazm.Stemmer()
        stemmed_tokens = [stemmer.stem(w) for w in tokens]
        tokens = stemmed_tokens

    if "L" in ops:
        lemmatizer = hazm.Lemmatizer()
        lemmatized_tokens = [lemmatizer.lemmatize(w) for w in tokens]
        tokens = lemmatized_tokens

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
