package Rename;

import textfile.textFileController;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by kennypotts on 3/23/17.
 */
public class Controller {

    private String moveCMD;
    private String copyCMD;
    private String OS;
    private textFileController options; // Not sure what to do about this???????? How are others resolving this?

    public void setOS(){
        this.OS = options.getOS();
    }

    public void setOptions(textFileController options){
        // I honestly have no clue how this should happen,
        // I need a way to access the OS type somehow,
        // which means I need access to the textFileController
        this.options = options;
    }

    public String getOS() {
        return this.OS;
    }

    public void setMoveCMD(String command) {
        this.moveCMD = command;
    }

    public void setCopyCMD(String command) {
        this.copyCMD = command;
    }

    public String getCopyCMD() {
        return copyCMD;
    }

    public String getMoveCMD() {
        return moveCMD;
    }


    public String[] searchFilesByType(String type, String directory) {
        String[] commands = {"../osx_scripts/searchByFileType.sh", type, directory};
        List<String> output = new LinkedList<>();

        try {
            //run the shell script
            Process process = new ProcessBuilder(commands).start();
            //read the output
            InputStream stdout = process.getInputStream ();
            BufferedReader reader = new BufferedReader (new InputStreamReader(stdout));
            //if reader.readLine() != null doesn't work,
            //while process.waitFor() != 0 or 1 might work, that function gives error codes
            while (reader.readLine() != null) {
                output.add(reader.readLine());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void renameFile(String fileName, String newFileName, String directory) throws IOException {

        File tempScript = createRenameFileBashScript(fileName, newFileName, directory);

        String s;

        try {
            ProcessBuilder pb = new ProcessBuilder("bash", tempScript.toString());
            pb.inheritIO();
            Process process = pb.start();

            process.waitFor();

            BufferedReader stdInput = new BufferedReader(new
                    InputStreamReader(process.getInputStream()));

            BufferedReader stdError = new BufferedReader(new
                    InputStreamReader(process.getErrorStream()));

            // read the output from the command
            System.out.println("Here is the standard output of the command:\n");
            while ((s = stdInput.readLine()) != null) {
                System.out.println(s);
            }

            // read any errors from the attempted command
            System.out.println("Here is the standard error of the command (if any):\n");
            while ((s = stdError.readLine()) != null) {
                System.out.println(s);
            }

            System.exit(0);

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            tempScript.delete();
        }
    }

    private File createRenameFileBashScript(String fileName, String newFileName, String directory) throws IOException {
        File tempScript = File.createTempFile("script", null);

        Writer streamWriter = new OutputStreamWriter(new FileOutputStream(
                tempScript));
        PrintWriter printWriter = new PrintWriter(streamWriter);

        printWriter.println("#!/bin/bash");
        printWriter.println("cd "+directory);
        printWriter.println("mv "+fileName+" "+newFileName);

        printWriter.close();

        return tempScript;
    }
}
