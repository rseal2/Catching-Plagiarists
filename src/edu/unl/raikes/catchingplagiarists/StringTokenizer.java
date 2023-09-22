package edu.unl.raikes.catchingplagiarists;

/**
 * Contains static functions helpful in tokenizing a String. Feel free to modify
 * if you think necessary.
 * 
 * @author Stephanie Valentine
 *
 */
public class StringTokenizer {
	/**
	 * Accepts a string and returns an array of the words in that string. The words
	 * have been lower-cased and stripped of all non-alphabetic characters.
	 * 
	 * @param input The string from which you want the array of words
	 * @return An array of all the alphabetic words in the input string
	 */
	public static String[] getWordsFromString(String input) {
		return input.trim().replaceAll("[^a-zA-Z\\s]", "").toLowerCase().split("\\s+");
	}

}
