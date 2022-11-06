def get_stopwords():
    with open("data/Stopwords/Stopwords", 'r', encoding='utf8') as f:
        return [sw for sw in f.readlines()]
