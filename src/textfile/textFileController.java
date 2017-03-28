package textfile;

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
    //date: xx/xx/xxxx

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

    public boolean validate() {
        //this function makes sure the options data is properly formed
        //returns true if all data is good, otherwise false
        //first verify OS
        if ((options.get(0) != "W") || (options.get(0) != "M")) {
            System.out.println("Failed to get OS");
            return false;
        }
        //TODO verify input and output once we decide on a format

        //verify date format
        //date is further validated in the options menu
        String date = options.get(3);
        if ((date.charAt(2) != '/') || (date.charAt(5) != '/')) {
            System.out.println("Failed to get date");
            return false;
        }
        return true;
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
    //getters and setters
    public String getOS() {
        return options.get(0);
    }
    public String getInput() {
        return options.get(1);
    }
    public String getOutput() {
        return options.get(2);
    }
    public String getDate() {
        return options.get(3);
    }

    public void setOS(String os) throws IOException {
        options.set(0, os);
        writeFile();
    }
    public void setInput(String input) throws IOException {
        options.set(0, input);
        writeFile();
    }
    public void setOutput(String output) throws IOException {
        options.set(0, output);
        writeFile();
    }
    public void setDate(String date) throws IOException {
        options.set(0, date);
        writeFile();
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
    }

}
