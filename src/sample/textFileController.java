package sample;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Eric on 3/22/17.
 */
public class textFileController {
    //this bad boy takes care of reading and writing from the text file
    public String fileName = "options.ini";
    public String path;
    //options is formatted as follows, one option per line:
    //OS: W or M
    //input: some string
    //output: some string
    //date: some string

    public List<String> options= new LinkedList<>();




    public void readFile() throws IOException {
        //loads the file into an array of lines
        FileReader fr = new FileReader(path + fileName);
        BufferedReader br = new BufferedReader(fr);
        String line;

        //go through the file, adding lines
        while (br.readLine() != null) {
                options.add(br.readLine());
        }
        br.close();
        fr.close();
    }

    public void writeFile() throws IOException {
        //take the array of options and put it in the file
        FileWriter fw = new FileWriter(path + fileName);
        BufferedWriter bw =new BufferedWriter(fw);
        //put each option from the list on a new line
        for (String line: options) {
            bw.write(line);
        }
        bw.close();
        fw.close();
    }

    //constructor
    public textFileController(String path) {
        boolean b = false;
        File f = new File(fileName);

        //get the path of the options file
        this.path = path;
        //see if the file already exists. if not, create it
        try {
            b = f.createNewFile();
        } catch (IOException e) {
            System.out.println(e);
        }

        if (b == true) {
            System.out.println("File was created");
        }

        //open file for reading
//        try {
//            readFile();
//        } catch (IOException e) {
//            System.out.println(e);
//        }

    }
}
