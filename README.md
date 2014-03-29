common-lines-finder
===================

A multi-threaded program that analyses of pairs of txt files in a directory and prints the lines common to both of them to the report file. The original idea was to analyse Russian folk songs in this manner, and at the moment the program accepts a rather specific kind of input: preprocessed text files where each word was converted to a word|lemma pair with the use of Python 3 pymorphy2 library. The ’texts’ folder provides several specimens. It would not be hard to add an additional plaint-text mode in which the program will compare simple English texts cleaning them up on-line if necessary.

The usage is straightforward:

```
~ $ java RecurringLinesFinder [path to the texts directory here]
```

The results are printed to the report.txt file, which is updated online. The same algorithm (except the concurrency element) implemented in Python 3 took several days — and not 66 seconds — to process a directory with 600-odd files so I took a precaution to be able to obtain some results ASAP. It turned out that this was unnecessary.