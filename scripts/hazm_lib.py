import sys
import hazm
import stopwords

sys.stdout.reconfigure(encoding='utf-8')

ops = sys.argv[1]
text = sys.argv[2]

if "N" in ops:
    text = hazm.Normalizer().normalize(text)

if "R" in ops:
    tokens = hazm.WordTokenizer().tokenize(text)
    stop_words = stopwords.get_stopwords()
    filtered_tokens = [w for w in tokens if w not in stop_words]
    text = " ".join(filtered_tokens)

if "S" in ops:
    text = hazm.Stemmer().stem(text)

if "L" in ops:
    text = hazm.Lemmatizer().lemmatize(text)

print(text)
