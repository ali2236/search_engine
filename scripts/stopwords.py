def get_stopwords() -> list[str]:
    with open("../data/Stopwords/Stopwords", 'r', encoding='utf8') as f:
        return [sw.strip() for sw in f.readlines()]
