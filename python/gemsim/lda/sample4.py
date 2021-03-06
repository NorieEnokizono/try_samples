
import sys

from statistics import mean
from toolz import concat, frequencies

from gensim.corpora import Dictionary
from gensim.models.ldamodel import LdaModel
from gensim.models import word2vec

data_file = sys.argv[1]
topic_num = int(sys.argv[2])
alpha = float(sys.argv[3])

sentences = [s for s in word2vec.LineSentence(data_file) if len(s) >= 2]

dic = Dictionary(sentences)

corpus = [dic.doc2bow(s) for s in sentences]

lda = LdaModel(corpus = corpus, id2word = dic, num_topics = topic_num, alpha = alpha, random_state = 1)

doc_topics = [lda[c] for c in corpus]

avg_doc_topics = mean([len(t) for t in doc_topics])

print(f"topics num of doc = {avg_doc_topics}")

topic_freq = frequencies(concat([[x[0] for x in t] for t in doc_topics]))

print('----------')

print('topic,freq,item,prob')

for i in range(topic_num):
  for t in lda.get_topic_terms(i):
    item = dic[t[0]]

    print(f"{i},{topic_freq[i]},{item},{t[1]}")

print('----------')

print('doc,item,topic')

for i in range(len(corpus)):
  dts = lda.get_document_topics(corpus[i], per_word_topics = True)

  for dt in dts[1]:
    item = dic[dt[0]]
    print(f"{i},{item},{dt[1][0]}")
