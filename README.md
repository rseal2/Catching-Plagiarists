# Catching Plagiarists

Catch the plagiarists! Your goal is to try to (quickly) determine the similarities between documents in a set to see if you can find out if plagiarism is going on within the group.

**More formally:** given a set of text documents, try to determine whether or not, or the degree to which, any pair of documents contains heavily copied portions of text. If one document shares an inordinate number of phrases in common with another, it's likely the document is plagiarized (or is the result of the all-too-often occurrence of finding out that one student "helped" another by giving them a copy of his work, only to have the other turn it in as their own).

### Assignment:

Your task is very similar to the one described and shown above: find the common word sequences among documents in a closed set.  Simply put, your input will be a set of plain-text documents, and a number n; your output will be some representation showing the number of `k`-word sequences each document has in common with every other document in the set.

Finally, you should identify "suspicious" pairs of documents that share many `k`-word-sequences.

**As a reminder, you are not allowed to use anything we haven’t talked about in class or read in the ZyBooks up to this point in the class, *especially* "hashmaps."** Any solution involving a hashmap will **not** recieve full credit on this assignment.

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

Additionally, you should report the time/memory it took for your code to execute (you can use the SimpleProfiler from last week's lab). For full points, your program needs to run in less than 4 seconds on the small size dataset.

##### THE DOCUMENTS:

Some sets of documents will be provided.  One set will be small (25 or so documents) for testing purposes. This small set is the benchmark we will be using on your code. Your program must perform the calculations in less than 4 seconds. The other sets will be larger (one has 75 documents, the other over 1300 documents) which you should use to test the scalability of your solution.  (The documents came from www.freeessays.cc, a repository of _really bad_ high school and middle school essays on a variety of topics. There may still be remnants of the websites from which they were scraped.) The set of 1300 documents is REALLY, REALLY BIG. You can expect a reasonable solution to run for at least 12 hours in order to process the largest set. That's a long time! Therefore, you can ignore the larger set, unless, of course, you're curious.

Your program should be able to process all of the documents in a given folder/directory.  

##### STRATEGY:

How are you going to do this?  We’ll it’s up to you.  Here is an example strategy.

1.  Write a class that accepts a file and can return the next `k`-word sequence on demand. Write unit tests for this class to ensure it operates properly.

2.  Write a class to hold a single result of the file comparison (the similarity between two files). You'll probably want to include compareTo and toString functions. Write unit tests for this class to ensure it operates properly.

3.  Write a class with a function that can compare two sequences to determine if they are the same. Write unit tests for this function to ensure it operates properly.

4.  Write a function that performs the comparisons. You may need to rewrite this function many times. Should the function compare two files in full? Should the function compare one `k`-word sequence from one file with all the other files? Is there another arrangement that would be more time efficient?

5.  Write a function that processes the results (however you choose to store them) by filtering them according to a similarity threshold and then sorting the filtered set. Beware of the "and" in that last sentence. This probably means you need two functions.

6.  Write a class that contains a `main` method that controls the running of the program. Ask the user for the dataset they want to use. Ask the user for `k`. Ask users for the similarity threshold. Call the function that processes the files and returns the results. Print the results. You may need extra functions to handle some of these tasks.

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

## Tips and Hints

-   Opening and reading files is going to be the most costly operation in terms of time. You want to minimize the number of times you need to open a file.
-   Storing the contents of a file is going to be the most costly operation in terms of memory. You might not be able to hold all of this data in memory at the same time. You need to be really conscious of "nulling out" very large blocks of data when you're finished with them. ("Nulling out" means setting their references to null, so the garbage collector can reclaim them.)
-   You will need to balance the two above items in order to get your code to run with the default amount of memory as well as in the time required.
-   Because you will constantly be reworking things to balance your memory usage with the time complexity, ensure that your code minimizes coupling. Ensure that your functions and classes operate the same, regardless of how they are implemented on the inside.

## Notes on Grading the Time of your Program

As previously mentioned, your code needs to run the **small** document folder in 4 seconds or less. Here are some notes to clarify how grading will work:

-  Every run of your code should be 4 seconds or less—if your code is sometimes faster than 4 seconds and sometimes slower, look at ways to speed it up.
- The TAs will grade times on the fastest laptop we have. That being said, if your code **consistently** runs in under 4 seconds on your personal computer and points are taken off for the program run time, please send a message in your help channel, and we will respect how quickly it runs on your computer.
- You will be penalized less if you are only a few seconds over the 4 second limit.

## Homework 12 Objectives

- **OOP Principles**: Demonstrate understanding of object-oriented design, including object instantiation, composition, encapsulation, and static vs. non-static.
- **Input/Output**: Demonstrates knowledge of file input and output, working with buffers, and understands potential efficiency concerns.
- **Computational Systems**: Understand the building blocks of computational systems, including binary number formats, memory management, and file optimization.
- **Meets Spec**: Write software that meets provided requirements and specifications.
- **Code Mechanics**: Choose appropriate data types and structures, use those types and structures effectively, and continue to make good choices over time.
- **Code Style**: Write code in an industry-standard coding style.
- **Software Engineering Skills**: Produce software by following industry-standard practices, including code reading, use of remote repositories, and software testing.

<sub>\* This assignment is based on "Catching Plagiarists", an assignment by Baker Franke, Computer Science Dept., The University of Chicago Laboratory Schools.</sub>
