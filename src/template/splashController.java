package template;

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
import textfile.textFileController;
import template.ControlledScreen;



import java.net.URL;
import java.util.ResourceBundle;


public class splashController implements Initializable, ControlledScreen{
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

    //todo figure out path for textfilecontroller
    //public textFileController setOptions = new textFileController();

    public String operatingSystemSelected = new String();


    public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }


    @FXML
    public void goToRename(ActionEvent event){
        getOperatingSystem();
        myController.setScreen(ScreensFramework.screen2ID);
    }

    public String getOperatingSystem(){
        if (windowsBtn.isSelected()){
            operatingSystemSelected = "W";
        } else if (macBtn.isSelected())  {
            operatingSystemSelected = "M";
        }
        else {
            System.out.println("No operating system selected");
            //todo throw
        }
        System.out.println("operatingSystem: " + operatingSystemSelected);

        return operatingSystemSelected;
    }





    //this function does nothing useful with it's parameters, but it is needed for intellij to stop whining
    public void initialize(URL url, ResourceBundle rb) {

    }


    public void setOperatingSystemWindows() {
        windowsBtn.setSelected(true);
    }
    public void setOperatingSystemMac() {
        macBtn.setSelected(true);
    }


}
