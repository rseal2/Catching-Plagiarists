package edu.unl.raikes.catchingplagiarists;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Reader class reads files and splits them into size. 
 *
 */
public class Reader {

    private int k;
    private String[] tokens;
    private File file;

    /**
     * Reads two files. 
     * @param k is the sequence length the user inputs. 
     * @param file is the file that it is reading. 
     */
    public Reader(int k, File file) {
        this.k = k;
        this.file = file;
        this.tokens = StringTokenizer.getWordsFromString(this.fileString());
    }

    /**
     * Gets all the k word sequences. 
     * @param k is the sequence length the user inputs. 
     * @param tokens is the array that needs to be added. 
     * @return all sequences. 
     */
    public String[] getKWordSequences() {
        String[] allSequences = new String[this.tokens.length - this.k + 1];
        for (int i = 0; i < allSequences.length; i++) {
            StringBuilder str = new StringBuilder();
            for (int j = i; j < i + this.k; j++) {
                str.append(this.tokens[j]);
                str.append(" ");
            }
            allSequences[i] = str.toString();
        }
        return allSequences;
    }

    /**
     * Converts the file into a string. 
     * @return a file string. 
     */
    public String fileString() {
        // you have to initialize this to null or else it initializes weirdly
        Scanner input = null;
        try {
            // general file path not specific location
            input = new Scanner((this.file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        StringBuilder fileString = new StringBuilder();
        while (input.hasNext()) {
            fileString.append(input.next());
            fileString.append(" ");
        }
        input.close();
        return fileString.toString();
    }

    /**
     * Gets the file name.
     * @return the file name. 
     */
    public String getFileName() {
        return this.file.getName();
    }

    /**
     * Gets the k value. 
     * @return k.
     */
    public int getK() {
        return this.k;
    }

    /**
     * Sets the k value. 
     * @param k is the integer value.
     */
    public void setK(int k) {
        this.k = k;
    }

    /**
     * Gets the tokens. 
     * @return tokens. 
     */
    public String[] getTokens() {
        return this.tokens;
    }

    /**
     * Sets the tokens. 
     * @param tokens is the array of tokens.
     */
    public void setTokens(String[] tokens) {
        this.tokens = tokens;
    }

    /**
     * Gets the file. 
     * @return file. 
     */
    public File getFile() {
        return this.file;
    }

    /**
     * Sets the file. 
     * @param file is the file.
     */
    public void setFile(File file) {
        this.file = file;
    }

}
