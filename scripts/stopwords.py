def get_stopwords():
    try:
        with open("data/Stopwords/Stopwords", 'r', encoding='utf8') as f:
            return [sw.strip() for sw in f.readlines()]
    except:
        with open("../PersianPoemsData/Stopwords/Stopwords", 'r', encoding='utf8') as f:
            return [sw.strip() for sw in f.readlines()]
