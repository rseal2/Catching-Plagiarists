package edu.unl.raikes.catchingplagiarists;

import static org.junit.Assert.*;
import java.io.File;
import org.junit.Test;

/**
 * Testing the functions within the project.
 *
 */
public class CatchingPlagiaristsTest {

    @Test
    public void testGetKWordSequences() {
        // setup
        int k = 2;
        File file = new File("./src./textFile.txt");
        Reader reader = new Reader(k, file);
        String[] expected = { "hello hi ", "hi howdy ", "howdy bye ", "bye goodbye ", "goodbye cheese ",
                "cheese sarah ", "sarah taco " };

        // execute
        String[] actual = reader.getKWordSequences();

        // test
        for (int i = 0; i < expected.length; i++) {
            assertTrue("Print final array function is not working", expected[i].equals(actual[i]));
        }
    }

    @Test
    public void testFileString() {
        // setup
        int k = 1;
        File file = new File("./src./textFile.txt");
        Reader reader = new Reader(k, file);
        String expected = "hello hi howdy bye goodbye cheese sarah taco ";

        // execute
        String actual = reader.fileString();

        // test
        assertEquals("File reader is not working correctly", expected, actual);

    }

    @Test
    public void testComparison() {
        // setup

        File fileOne = new File("./src./textFile.txt");
        File fileTwo = new File("./src./textFileTwo.txt");
        String[] one = { "hello hi ", "hi howdy ", "howdy bye ", "bye goodbye ", "goodbye cheese ", "cheese sarah ",
                "sarah taco " };
        String[] two = { "phone apple ", "apple wallet ", "wallet vitamin ", "vitamin charger ", "charger soda ",
                "soda frog ", "frog peace" };
        FileComparison fComp = new FileComparison(one, two, fileOne, fileTwo);

        int expected = 0;

        // execute
        int actual = fComp.comparison();

        // test
        assertEquals("Comparison function is not working as expected", expected, actual);
    }

    @Test
    public void testComparisonIfFilesAreTheSame() {
        // setup

        File fileOne = new File("./src./textFileTwoCopy.txt");
        File fileTwo = new File("./src./textFileTwo.txt");
        String[] one = { "phone apple ", "apple wallet ", "wallet vitamin ", "vitamin charger ", "charger soda ",
                "soda frog ", "frog peace" };
        String[] two = { "phone apple ", "apple wallet ", "wallet vitamin ", "vitamin charger ", "charger soda ",
                "soda frog ", "frog peace" };
        FileComparison fComp = new FileComparison(one, two, fileOne, fileTwo);

        int expected = 7;

        // execute
        int actual = fComp.comparison();

        // test
        assertEquals("Comparison function is not working as expected", expected, actual);
    }
}
