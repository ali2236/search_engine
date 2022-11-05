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
    filtered_tokens = stopwords.remove_stopwords(tokens)
    text = " ".join(filtered_tokens)

if "S" in ops:
    text = hazm.Stemmer().stem(text)

if "L" in ops:
    text = hazm.Lemmatizer().lemmatize(text)

print(text)