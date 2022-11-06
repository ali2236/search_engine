import sys
import parsivar
import stopwords

sys.stdout.reconfigure(encoding='utf-8')

ops = sys.argv[1]
text = sys.argv[2]

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
print(text)
