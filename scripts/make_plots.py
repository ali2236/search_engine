import pandas as pd
import matplotlib.pyplot as plt
import os

results = "../results/queries"
queries = os.listdir(results)
for query in queries:
    q_path = os.path.join(results, query)
    df = pd.read_csv(q_path)
    df.plot.bar(y='Precision')
    nm = query.split('.')[0]
    plt.savefig(f'{nm}.png')
    break
