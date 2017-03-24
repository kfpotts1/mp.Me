package test;

import org.junit.Test;
import sample.*;

import static org.junit.Assert.*;

/**
 * Created by Eric on 3/24/17.
 */
public class textFileControllerTest {
    @Test
    public void openFile() {
        //this tests to see if you can successfully read a file
        //note: this file path only works on my computer
        String path = "/Users/Eric/Dropbox/Software Eng/mp.Me/options";
        //create a textFileController
        textFileController reader = new textFileController(path);
    }
}