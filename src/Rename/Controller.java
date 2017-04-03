package Rename;

import textfile.textFileController;

import java.io.*;

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

//    public void renameFile2(String fileName, String newFileName, String directory) {
//        String command = this.moveCMD + " " + directory+"/"+fileName + " " + directory+"/"+newFileName;
//        try {
//            Runtime rt = Runtime.getRuntime();
//            //Process pr = rt.exec("cmd /c dir");
//            Process pr = rt.exec(command);
//
//            BufferedReader input = new BufferedReader(new InputStreamReader(pr.getInputStream()));
//
//            String line=null;
//
//            while((line=input.readLine()) != null) {
//                System.out.println(line);
//            }
//
//            int exitVal = pr.waitFor();
//            System.out.println("Exited with error code "+exitVal);
//
//        } catch(Exception e) {
//            System.out.println(e.toString());
//            e.printStackTrace();
//        }
//    }

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
