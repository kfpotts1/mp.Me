package template;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;

public class optionsController {
    public String os, input, output, date;


    ScreensController myController;

    @FXML
    private Button renameBtn;
    @FXML
    Parent root;
    public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }
    @FXML
    public void goToRename(ActionEvent event){
        myController.setScreen(template.ScreensFramework.screen2ID);
    }
    //constructor
    public optionsController(String os, String input, String output, String date){
        this.os = os;
        this.input = input;
        this.output = output;
        this.date = date;
    }

    //set functions are this.variable = stringfield
    public void setOS(){


    }

    public void setInput(){


    }

    public void setOutput(){


    }

    public void setDate(String monthField, String dayField, String yearField){
        String month, day, year;
        //set 3 date indicators from stringfields
        // *temporary fix*
        month = "";
        day = "";
        year = "";
    //char checks
        //1 or 2 char between ints 1-12
        if((monthField.length() == 1) || (monthField.length() == 2)) {
            if ((Integer.parseInt(monthField) > 0) && (Integer.parseInt(monthField) < 13)) {
                month = monthField;
                //1 or 2 char between ints 1-31
                if ((dayField.length() == 1) || (monthField.length() == 2)) {
                    if ((Integer.parseInt(dayField) > 0) && (Integer.parseInt(dayField) < 31)) {
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
        this.date = month + "/" + "/" + day + "/" + year;
    }

    //get variables from the textFileController
    public void getOptions(String[] optArr){
        this.os = optArr[0];
        this.input = optArr[1];
        this.output = optArr[2];
        this.date = optArr[3];
    }

    //Calls all set functions
    public void reload(){
        //send all stringfields into functions
        setOS();
        setInput();
        setOutput();
        //setDate();

    }

}