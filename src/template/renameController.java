package template;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;

import textfile.textFileController;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;
import template.ControlledScreen;
import javafx.scene.control.Button;

/**
 * Created by kennypotts on 3/23/17.
 */
public class renameController implements Initializable, ControlledScreen {
    ScreensController myController;

    @FXML
    private Button backBtn;
    @FXML
    Parent root;

    private String moveCMD;
    private String copyCMD;
    private String OS;
    private textFileController options; // Not sure what to do about this???????? How are others resolving this?

    public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }
    @FXML
    public void goToSplash(ActionEvent event){
        myController.setScreen(template.ScreensFramework.screen1ID);
    }

    //this function does nothing useful with it's parameters, but it is needed for intellij to stop whining
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

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