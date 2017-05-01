package template;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import textfile.textFileController;
import template.ControlledScreen;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Jack
 * This class controls the splash screen and handles all user interaction within. The user can select an operating system,
 * then can continue on to use the program. This screen should only launch if the user has never selected an operating system before.
 */

public class splashController implements Initializable, ControlledScreen{

    private static final Logger LOGGER = Logger.getLogger( splashController.class.getName() );


    private textFileController optionsFile = new textFileController();

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
    public void goToRename(ActionEvent event) {

        setOperatingSystem();

        if (operatingSystemSelected == "W" || operatingSystemSelected == "M") {
            myController.setScreen(ScreensFramework.screen2ID);
        } else {
            System.out.println("operatingSystemSelected: " + operatingSystemSelected);
            LOGGER.log(Level.SEVERE, "No operating system selected in splash screen!");
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setTitle("mpMe Alert");
            a.setHeaderText("No operating system selected");
            a.setResizable(true);
            String version = System.getProperty("java.version");
            String content = String.format("Please select an operating system before proceeding.", version);
            a.setContentText(content);
            a.showAndWait();
        }
    }


    /**
     * This function double checks the selected radio button, then loads the operatingSystemSelected string into the options text file
     * @param
     * @return void
     * @pre none (though preferred action is that the user has selected a radio button)
     * @post sets the operating system element in the options array through textFileController
     */
    public void setOperatingSystem() {
        if (windowsBtn.isSelected()){
                optionsFile.setOS(operatingSystemSelected);
        } else if (macBtn.isSelected())  {
                optionsFile.setOS(operatingSystemSelected);
        }
        else {
            LOGGER.log(Level.SEVERE, "No operating system selected in splash screen!");
        }
        LOGGER.log(Level.INFO,"Operating system selected: " + operatingSystemSelected);

    }

    public void setOSWindows(){
        operatingSystemSelected = "W";
    }

    public void setOSMac(){
        operatingSystemSelected = "M";
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




}
