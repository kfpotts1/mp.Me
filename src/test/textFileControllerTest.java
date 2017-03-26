package test;

import org.junit.Test;
import sample.*;

import java.io.IOException;

/**
 * Created by Eric on 3/24/17.
 */
public class textFileControllerTest {
    @Test
    public void readFile() {
        //this tests to see if you can successfully read a file
        //note: this file path only works on my computer
        String path = "/Users/Eric/Dropbox/Software Eng/mp.Me/options/";
        //create a textFileController
        textFileController reader = new textFileController(path);
        //make sure the line menu is empty
        if (!reader.options.isEmpty()) {
            System.out.println("Test failed: options list was not empty on open");
            System.exit(1);
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
        System.out.println("Reading files test passed");
    }

    public void writeFile() {
        //note: this file path only works on my computer
        String path = "/Users/Eric/Dropbox/Software Eng/mp.Me/options/";
        //create a textFileController
        textFileController reader = new textFileController(path);
        try {
            reader.readFile();
        } catch (IOException e) {
            System.out.println(e);
            System.out.println("Test failed: file not found");
            System.exit(1);
        }
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