package edu.unl.raikes.catchingplagiarists;

import java.io.File;
import java.util.ArrayList;

/**
 * Class that compares two files.
 */
public class FileComparison implements Comparable<FileComparison> {
    private String[] one;
    private String[] two;
    private File fileOne;
    private File fileTwo;

    /**
     * Compares two files.
     * 
     * @param one is the first file array.
     * @param two is the second file array.
     * @param fileOne is the first file.
     * @param fileTwo is the second file.
     */
    public FileComparison(String[] one, String[] two, File fileOne, File fileTwo) {
        this.one = one;
        this.two = two;
        this.fileOne = fileOne;
        this.fileTwo = fileTwo;
        this.comparison();
    }

    /**
     * Compares two file arrays and returns the similarity between them. 
     * @return similarity score. 
     */
    public int comparison() {
        int similarity = 0;
        for (int i = 0; i < this.one.length; i++) {
            for (int j = 0; j < this.two.length; j++) {
                if (this.one[i].equals(this.two[j])) {
                    similarity++;
                }
            }
        }
        return similarity;

    }

    /**
     * Gets the first file array.
     * 
     * @return the first array.
     */
    public String[] getOne() {
        return this.one;
    }

    /**
     * Sets the first file array.
     * 
     * @param one is the array.
     */
    public void setOne(String[] one) {
        this.one = one;
    }

    /**
     * Gets the second file array.
     * 
     * @return the second array.
     */
    public String[] getTwo() {
        return this.two;
    }

    /**
     * Sets the second file array.
     * 
     * @param two is the array.
     */
    public void setTwo(String[] two) {
        this.two = two;
    }

    /**
     * Gets the first file.
     * 
     * @return file one.
     */
    public File getFileOne() {
        return this.fileOne;
    }

    /**
     * Sets the first file.
     * 
     * @param fileOne takes in the first file.
     */
    public void setFileOne(File fileOne) {
        this.fileOne = fileOne;
    }

    /**
     * Gets the second file.
     * 
     * @return file two.
     */
    public File getFileTwo() {
        return this.fileTwo;
    }

    /**
     * Sets the second file.
     * 
     * @param fileTwo takes in the second file.
     */
    public void setFileTwo(File fileTwo) {
        this.fileTwo = fileTwo;
    }

    @Override
    public int compareTo(FileComparison o) {
        return o.comparison() - this.comparison();
    }

    /**
     * Converts comparison into a string. 
     */
    public String toString() {
        return this.comparison() + " shared phrases: " + this.fileOne.getName() + ", " + this.fileTwo.getName();
    }
}
