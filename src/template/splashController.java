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

/**
 * @author Jack
 * This class controls the splash screen and handles all user interaction within. The user can select an operating system,
 * then can continue on to use the program. This screen should only launch if the user has never selected an operating system before.
 */

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

    /**
     * This function injects the screen and controller into a screenParent node
     * @param screenParent
     * @return void
     * @pre none
     * @post loads screen1ID (the splash screen)
     */
    public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }


    /**
     * This function handles the screen switching from splash screen to rename screen
     * @param event (user clicks the "Next>>" button)
     * @return void
     * @pre operatingSystem Selected
     * @post loads screen2ID (the rename screen)
     */
    @FXML
    public void goToRename(ActionEvent event){
        getOperatingSystem();
        myController.setScreen(ScreensFramework.screen2ID);
    }


    /**
     * This function double checks the selected radio button, then loads the operatingSystemSelected string into the options text file
     * @param
     * @return String operatingSystemSelected
     * @pre none (though preferred action is that the user has selected a radio button)
     * @post sets the operating system element in the options array through textFileController
     */
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


    /**
     * This function replaces a default constructor as we are unsure if the screen we are loading exists until we actually load it
     * @param url unused
     * @param rb unused
     * @return void
     * @pre
     * @post
     */
    public void initialize(URL url, ResourceBundle rb) {

    }


    /**
     * This function checks to see if the user selected operating system is Windows
     * @param
     * @return void
     * @pre user has selected the windowsBtn radio button
     * @post
     */
    public void setOperatingSystemWindows() {
        windowsBtn.setSelected(true);
    }

    /**
     * This function checks to see if the user selected operating system is Mac
     * @param
     * @return void
     * @pre user has selected the macBtn radio button
     * @post
     */
    public void setOperatingSystemMac() {
        macBtn.setSelected(true);
    }


}
