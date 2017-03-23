package sample;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Eric on 3/22/17.
 */
public class textFileController {
    //this bad boy takes care of reading and writing from the text file
    public String fileName = "Options.ini";
    public String path;
    public List<String> options= new LinkedList<>();




    public void readFile() throws IOException {
        //loads the file into an array of lines
        FileReader fr = new FileReader(path);
        BufferedReader br = new BufferedReader(fr);
        String line;

        //go through the file, adding lines
        while (br.readLine() != null) {
                options.add(br.readLine());
        }
        br.close();
        fr.close();
    }

    //constructor
    textFileController(String path) {
        //get the path of the options file
        this.path = path;
        //open file for reading
        try {
            readFile();
        } catch (IOException e) {
            System.out.println(e);
        }

    }
}
