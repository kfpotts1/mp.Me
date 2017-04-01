package screenSwapTesting;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;

import java.net.URL;
import java.util.ResourceBundle;


public class Screen1Controller implements Initializable, ControlledScreen{
    ScreensController myController;

    @FXML
    public ToggleGroup operatingSystemButtons;
    @FXML
    public Button nextBtn;
    @FXML
    public RadioButton windowsBtn;
    @FXML
    public RadioButton macBtn;
    @FXML
    Parent root;

    String screenName = "screen1";

    //todo figure out path for textfilecontroller
    //public textFileController setOptions = new textFileController();

    public String operatingSystemSelected = new String();

    public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }


    @FXML
    public void goToScreen2(ActionEvent event){
        myController.setScreen(ScreensFramework.screen2ID);
    }




    //this function does nothing useful with it's parameters, but it is needed for intellij to stop whining
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //default OS if none selected
        operatingSystemSelected = "M";
        macBtn.setSelected(true);

    }



    public void setOperatingSystemWindows() {
        windowsBtn.setSelected(true);
        operatingSystemSelected = "W";
        System.out.println(operatingSystemSelected);

    }
    public void setOperatingSystemMac() {
        macBtn.setSelected(true);
        operatingSystemSelected = "M";
        System.out.println(operatingSystemSelected);
    }

    public String getOperatingSystem(){
        if (operatingSystemSelected == null){
            System.out.println("No operating system selected");
            //todo throw
        } else {
            return operatingSystemSelected;
        }
        return "";
    }


}
