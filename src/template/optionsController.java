package template;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import template.ControlledScreen;
import textfile.textFileController;

public class optionsController implements Initializable, ControlledScreen {
    ScreensController myController;

    @FXML
    private Button renameBtn;
    @FXML
    private ImageView renameBtnImg;
    @FXML
    Parent root;


    public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }


    public String os, input, output, date;


    @FXML
    public void goToRename(ActionEvent event){
        myController.setScreen(template.ScreensFramework.screen2ID);
    }

    //this function does nothing useful with it's parameters, but it is needed for intellij to stop whining
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        collectOptions();
    }

    //set functions are this.variable = stringfield
    public void setOS(){

    }

    public void setInput(String inputField) {


    }

    public void setOutput(String outputField) {


    }

    public void setDate(String monthField, String dayField, String yearField) {
        String month, day, year;
        // splits the date into month, day, year
        String dateString[] = date.split("/");
        month = dateString[0];
        day = dateString[1];
        year = dateString[2];
        //user sets 3 date indicators from stringfields
        //char checks for valid date
        //1 or 2 char between ints 1-12
        if ((monthField.length() == 1) || (monthField.length() == 2)) {
            if ((Integer.parseInt(monthField) > 0) && (Integer.parseInt(monthField) < 13)) {
                month = monthField;
                //1 or 2 char between ints 1-31
                if ((dayField.length() == 1) || (monthField.length() == 2)) {
                    if ((Integer.parseInt(dayField) > 0) && (Integer.parseInt(dayField) < 32)) {
                        day = dayField;
                        //4 char ints above 1970
                        if ((yearField.length() == 4)) {
                            if ((Integer.parseInt(yearField)) > 1969) {
                                year = yearField;
                                day = yearField;
                                month = monthField;
                            }
                        }
                    }
                }
            }
        }
        date = month + "/" + "/" + day + "/" + year;
    }

    //get variables from the textFileController
    public void collectOptions() {
        textfile.textFileController opt = new textFileController();
        os = opt.getOS();
        input = opt.getInput();
        output = opt.getOutput();
        date = opt.getDate();
    }

    public String getInput() {
        return input;
    }

    public String getOutput() {
        return output;
    }

    public String getDate() {
        return date;
    }

    //Calls all set functions
    public void reload() {
        //send all stringfields into functions
        setOS();
        //setInput();
        //setOutput();
        //setDate();

    }
}