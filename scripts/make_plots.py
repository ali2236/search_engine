import pandas as pd
import matplotlib.pyplot as plt
import os

results = "../results/plot_queries"
output = "../results/plots"

queries = os.listdir(results)
for query in queries:
    q_path = os.path.join(results, query)
    nm = query.split('.')[0]
    df = pd.read_csv(q_path)

    f, ax = plt.subplots(4, 1, figsize=(10, 12))
    plt.subplots_adjust(left=0.1,
                        bottom=0.1,
                        right=0.9,
                        top=0.9,
                        wspace=0.4,
                        hspace=0.4)

    ax = ax.flatten()
    df.plot.bar(x='Search Engine', y='Precision', title='Precision', xlabel="", ax=ax[0])
    # plt.title('Precision')
    # plt.savefig(os.path.join(output, f'{nm}-precision.png'))

    df.plot.bar(x='Search Engine', y='Recall', title='Recall', xlabel="", ax=ax[1])
    # plt.title('Recall')
    # plt.savefig(os.path.join(output, f'{nm}-recall.png'))

    df.plot.bar(x='Search Engine', y='F-Measure', title='F-Measure', xlabel="", ax=ax[2])
    # plt.title('F-Measure')
    # plt.savefig(os.path.join(output, f'{nm}-f-measure.png'))

    df.plot.bar(x='Search Engine', y='MAP', title='Mean Average Precision', xlabel="", ax=ax[3])
    #plt.title('Mean Average Precision')

    plt.savefig(os.path.join(output, f'{nm}.png'))
    plt.close()

