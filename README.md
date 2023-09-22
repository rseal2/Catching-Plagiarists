# Catching Plagiarists

Catch the plagiarists! 

**More formally:** given a set of text documents, try to determine whether or not, or the degree to which, any pair of documents contains heavily copied portions of text. If one document shares an inordinate number of phrases in common with another, it's likely the document is plagiarized (or is the result of the all-too-often occurrence of finding out that one student "helped" another by giving them a copy of his work, only to have the other turn it in as their own).

### Details:

##### PROCESSING:

First, you need to ask the user for their `k`, the number of words in sequence that you'd like to search for. A common `k` is 6; so if the user entered 6, we would look for six-word sequences.

Once you have your `k`, you will need to read in the `k`-word sequences from each file. Start by reading the entire file in as a string (make sure to add a space between every word and a newline between every line so that words are not "smashedtogether"). Then, pass the string to the provided `StringTokenizer.getWordsFromString()` function that converts the document to lowercase and splits it into tokens (words). Do not write your own code to split the file into words.

You will then compare **all** of the `k`-word sequences in File A with **all** of the `k`-word sequences in file B, file C, file D, etc. If the comparison comes up "equal", then you should increment the similarity count between those two files. As an example, if the input document is `I like to drink iced coffee` and k=2, `StringTokenizer.getWordsFromString()` would return `["I", "like", "to", "drink", "iced", "coffee"]` and the k-word (2-word) phrases to compare with another document would be `["I like", "like to", "to drink", "drink iced", "iced coffee"]`.

Once all the documents have been compared, you should output a matrix of comparison results. You can think of the results of processing the files as an FxF matrix (where F is the number of total documents) with a number in each cell representing the number of “hits” between any pair of documents.

For example: below is a small table showing the comparisons between 5 documents:

        A     B     C     D     E
    A   -     4    50   700     0
    B   -     -     0     0     5
    C   -     -     -    50     0
    D   -     -     -     -     0
    E   -     -     -     -     -

From this table we can conclude that the writers of documents A, C, and D share a high number of similar 6-word phrases.  We can probably say A and D cheated with a high degree of certainty.

##### OUTPUT:

Printing an NxN matrix may be unmanageable for large sets.  You should instead produce a list of documents ordered by number of hits.  The user should be able to specify a threshold for the minimum number of hits. For example, if the user specified they want to see all pairs of documents that share 25 or more hits, the output might look like this (given the scenario above):

    The following pairs of documents shared 25 or more 6-word phrases:
    700 shared phrases: A, D
    50 shared phrases: A, C
    50 shared phrases: C, D

##### THE DOCUMENTS:

Some sets of documents will be provided.  One set will be small (25 or so documents) for testing purposes. This small set is the benchmark we will be using on your code. Your program must perform the calculations in less than 4 seconds. The other sets will be larger (one has 75 documents, the other over 1300 documents) which you should use to test the scalability of your solution.  (The documents came from www.freeessays.cc, a repository of _really bad_ high school and middle school essays on a variety of topics. There may still be remnants of the websites from which they were scraped.) The set of 1300 documents is REALLY, REALLY BIG. You can expect a reasonable solution to run for at least 12 hours in order to process the largest set. That's a long time! Therefore, you can ignore the larger set, unless, of course, you're curious.

## Example Output

Given the small dataset, your output should look something like this:

    The following pairs of documents shared 25 or more 6-word phrases:
    1658 shared phrases: sra31.txt, jrf1109.txt
    675 shared phrases: edo26.txt, abf0704.txt
    523 shared phrases: abf70402.txt, abf0704.txt
    408 shared phrases: edo14.txt, bef1121.txt
    384 shared phrases: tyc12.txt, catchmeifyoucan.txt
    308 shared phrases: catchmeifyoucan.txt, hal10.txt
    290 shared phrases: catchmeifyoucan.txt, ecu201.txt
    180 shared phrases: abf70402.txt, edo26.txt
    ==================================================
    STARTING MEMORY: _____ bytes
    ENDING MEMORY: _____ bytes
    USAGE: _____ bytes
    TIME: _____ milliseconds
    ==================================================
