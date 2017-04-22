package test;

import org.junit.Test;
import textfile.*;

import java.io.IOException;

/**
 * Created by Eric on 3/24/17.
 */
 /*
These aren't relevant anymore cause we're using a better methodology for options files

public class textFileControllerTest {
    @Test
    public void readFile() {
        //this tests to see if you can successfully read a file
        //create a textFileController
        textFileController reader = new textFileController();
        //make sure the line menu is empty
        if (!reader.options.isEmpty()) {
            System.out.println("Test failed: options list was not empty on open");
            System.exit(1);
        }
        //add text to the options file
        reader.options.add("W");
        try {
            reader.writeFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //read the options file
        try {
            reader.readFile();
        } catch (IOException e) {
            System.out.println(e);
            System.out.println("Test failed: file not found");
            System.exit(1);
        }
        //now see if lines have been read
        if (reader.options.isEmpty()) {
            System.out.println("Test failed: no input was read");
            System.exit(1);
        }
    }
    @Test
    public void writeFile() {

        //create a textFileController
        textFileController reader = new textFileController();
        try {
            reader.readFile();
        } catch (IOException e) {
            System.out.println(e);
            System.out.println("Test failed: file not found");
            System.exit(1);
        }
        //put something in the list to write
        reader.options.add("M");
        //take out a null item that sometimes appears
        reader.options.remove(null);
        try {
            reader.writeFile();
        } catch (IOException e)  {
            System.out.println(e);
            System.out.println("Test failed: cannot write to file");
            System.exit(1);
        }

    }

    public void main() {
        //this actually calls the test
        readFile();
    }
    }

*/
