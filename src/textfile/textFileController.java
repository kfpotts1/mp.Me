package textfile;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Eric
 */
public class textFileController {
    //this bad boy takes care of reading and writing from the text file
    public String fileName = "options.ini";
    //options is formatted as follows, one option per line:
    //OS: W or M
    //input: some string
    //output: some string
    //date: xx/xx/xxxx
    //first launch: 0 or 1
    public List<String> options= new ArrayList<>(100);


    /**
     * This function copies the contents of the options.ini file into a list of strings.
     * Each line in the original file becomes an item in the list.
     *
     * @return nothing
     * @pre the class has been constructed and the options.ini file exists
     * @post the options array is full of actual options data
     * @throws IOException
     */
    public void readFile() throws IOException {
        //loads the file into an array of lines
        FileReader fr = new FileReader(fileName);
        BufferedReader br = new BufferedReader(fr);
        String line;

        //go through the file, adding lines
        while (br.readLine() != null) {
                options.add(br.readLine());
        }
        br.close();
        fr.close();
    }

    /**
     * @author eric
     * @pre the class has already read from the options file
     * @post the operating system and date formatting have been checked
     * @return a boolean that indicates if the options file is formed correctly
     */
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

    /**
     * @return nothing!
     * @pre the options list contains data
     * @post the options.ini text file has been updated to contain the content of the options list
     * @throws IOException
     */
    public void writeFile() throws IOException {
        //take the array of options and put it in the file
        FileWriter fw = new FileWriter(fileName);
        BufferedWriter bw =new BufferedWriter(fw);
        //System.out.println("Writing "  + options.toString() + " to file");
        //put each option from the list on a new line
        for (String line: options) {
            //System.out.println("this better work");
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
    public String getFirstLaunch() {
        return options.get(4);
    }

    public void setOS(String os) throws IOException {
        options.set(0, os);
        writeFile();
    }
    public void setInput(String input) throws IOException {
        options.set(1, input);
        writeFile();
    }
    public void setOutput(String output) throws IOException {
        options.set(2, output);
        writeFile();
    }
    public void setDate(String date) throws IOException {
        options.set(3, date);
        writeFile();
    }
    public void setFirstLaunch(String first) throws IOException {
        options.set(4, first);
        writeFile();
    }


    //constructor
    public textFileController() {
        boolean b = false;
        File f = new File(fileName);
        //see if the file already exists. if not, create it
        try {
            b = f.createNewFile();
        } catch (IOException e) {
            System.out.println(e);
        }
        //log if a new file had to be made
        if (b == true) {
            System.out.println("File was created");
            //put placeholders into options
            for (int i=0; i < 4; i++) {

                options.add("placeholder");
            }
            try {
                writeFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            //if the file already exists
            try {
                readFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
