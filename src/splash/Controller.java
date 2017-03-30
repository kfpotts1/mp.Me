package splash;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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



public class Controller {
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


    public void setOperatingSystemWindows() {
        windowsBtn.setSelected(true);
        operatingSystemSelected = "W";
        //operatingSystemSelected.setOperatingSystem(operatingSystemSelected);
    }
    public void setOperatingSystemMac() {
        macBtn.setSelected(true);
        operatingSystemSelected = "M";
        //operatingSystemSelected.setOperatingSystem(operatingSystemSelected);
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

    public void initialize()
    {
        //default OS if none selected
        operatingSystemSelected = "M";
        macBtn.setSelected(true);




    }

}
