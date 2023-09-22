package edu.unl.raikes.catchingplagiarists;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

/**
 * Main method of the catching plagiarists project.
 * 
 *
 */
public class CatchingPlagiaristsMain {

    /**
     * Reads the file directory and prints out the final comparisons in the array.
     * 
     * @param dir is the directory of files.
     * @param k is the sequence length the user inputs.
     * @param threshhold is the number of words similar between the files.
     */
    public static void directoryReader(File[] dir, int k, int threshhold) {
        ArrayList<FileComparison> comparisons = new ArrayList<FileComparison>();
        for (int i = 0; i < dir.length; i++) {
            for (int j = i + 1; j < dir.length; j++) {
                Reader compA = new Reader(k, dir[i]);
                Reader compB = new Reader(k, dir[j]);
                FileComparison score = new FileComparison(compA.getKWordSequences(), compB.getKWordSequences(),
                        compA.getFile(), compB.getFile());
                if (score.comparison() > threshhold) {
                    order(comparisons, score);
                }
            }
        }
        printFinalArray(comparisons);
    }

    /**
     * Prints the final array of files.
     * 
     * @param comparisons is the array list of comparisons.
     */
    public static void printFinalArray(ArrayList<FileComparison> comparisons) {
        for (int i = 0; i < comparisons.size(); i++) {
            System.out.println(comparisons.get(i));
        }
    }

    /**
     * Orders the scores in the array.
     * 
     * @param comparisons is the comparisons between the files.
     * @param score is the score of the FileComparison class.
     */
    public static void order(ArrayList<FileComparison> comparisons, FileComparison score) {
        // only adds if this boolean is true
        boolean adding = true;
        for (int i = 0; adding && i < comparisons.size(); i++) {
            if (comparisons.get(i).compareTo(score) > 0) {
                comparisons.add(i, score);
                adding = false;
            }
        }
        if (adding) {
            comparisons.add(score);
        }
    }

    /**
     * Main method controls the outcome of the project.
     * 
     */
    public static void main(String[] args) {
        // gets user input
        System.out.print("Enter a k value for your sequence size ");
        Scanner scnr = new Scanner(System.in);
        int k = scnr.nextInt();

        System.out.print("Enter a size for your data set (small, medium, large) ");

        String file = scnr.next();

        File directory = new File(file + "_doc_set");
        File[] dir = directory.listFiles();

        System.out.print("Enter a value for the threshold of similar words ");
        int threshhold = scnr.nextInt();
        scnr.close();
        SimpleProfiler profiler = new SimpleProfiler().start();

        directoryReader(dir, k, threshhold);

        // Profiler stuff.
        profiler.stop();
        System.out.println(
                profiler.getReport(SimpleProfiler.TimeInterval.SECOND, SimpleProfiler.MemoryInterval.MEGABYTE));

    }

}
