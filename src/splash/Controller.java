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

    public String operatingSystemSelected = new String();


    public void setOperatingSystemWindows() {
        windowsBtn.setSelected(true);
        operatingSystemSelected = "W";
    }
    public void setOperatingSystemMac() {
        macBtn.setSelected(true);
        operatingSystemSelected = "M";
    }

    public String getOperatingSystem(){
        if (operatingSystemSelected == null){
            System.out.println("No operating system selected");
            //todo throw
        } else {
            System.out.println("Operating System: "+ operatingSystemSelected);
            return operatingSystemSelected;
        }
        return "";
    }

    /*public String getOperatingSystem(ActionEvent actionEvent)
    {

        operatingSystem.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> ov, Toggle t, Toggle t1) {
                RadioButton chk = (RadioButton) t1.getToggleGroup().getSelectedToggle(); // Cast object to radio button
                System.out.println("Selected Radio Button - " + chk.getText());
                operatingSystemSelected[0] = chk.getText();
            }
        });
        System.out.println("Selected Radio Button - "+ operatingSystemSelected[0]);
        return operatingSystemSelected[0];
    }*/

    public void initialize()
    {
        operatingSystemSelected = "M";
        //default OS if none selected
        macBtn.setSelected(true);




    }

}
