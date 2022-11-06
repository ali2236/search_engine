import sys
import hazm
import stopwords

sys.stdout.reconfigure(encoding='utf-8')

ops = sys.argv[1]
text = sys.argv[2]

if "N" in ops:
    text = hazm.Normalizer().normalize(text)

tokens = hazm.WordTokenizer().tokenize(text)

if "R" in ops:
    stop_words = stopwords.get_stopwords()
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
print(text)
