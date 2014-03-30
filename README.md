common-lines-finder
===================

A multi-threaded program that analyses all pairs of txt files in a directory and prints pairs, triples, etc., of lines common to both of the files to the report file (single common are also collected, but then discarded; they can be reported as well). The original idea was to analyse Russian folk songs in this manner, and at the moment the program accepts a rather specific kind of input: preprocessed text files where each word was converted to a word|lemma pair with the use of Python 3 pymorphy2 library. The ’texts’ folder provides several specimens. It would not be hard to add an additional plaint-text mode in which the program will compare simple English texts cleaning them up on-line if necessary.

The usage is straightforward:

```
~ $ java RecurringLinesFinder [path to the texts directory here]
```

The results are printed to the report.txt file, which is updated on-line. The same algorithm (save the concurrency) implemented in Python 3 took several days — and not 66 seconds — to process a directory with 600-odd files so I took a precaution to be able to obtain some results ASAP. It turned out that this was unnecessary.
