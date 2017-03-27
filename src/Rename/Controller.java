package Rename;

import sample.textFileController;

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
}
