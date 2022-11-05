from collections.abc import Iterable

def get_stopwords() -> Iterable[str]:
    with open("../data/Stopwords/Stopwords") as f:
        return f.readlines()

def remove_stopwords(tokens: Iterable[str]) -> Iterable[str]:
    stop_words = get_stopwords()
    filtered_tokens = [w for w in tokens if not w in stop_words]
    return filtered_tokens
