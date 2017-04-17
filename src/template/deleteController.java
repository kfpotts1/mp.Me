package template;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;

import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.image.*;
import javafx.scene.image.Image;
import textfile.textFileController;

import java.awt.*;
import java.awt.TextField;
import java.net.URL;
import java.util.ResourceBundle;
import template.ControlledScreen;

/**
 * Created by Jack Banta on 3/23/17.
 */
public class deleteController implements Initializable, ControlledScreen {
    ScreensController myController;

    @FXML
    private Button optionsScreenBtn;
    @FXML
    private Image optionsScreenbtnImg;

    @FXML
    private Button deleteScreenBtn;
    @FXML
    private Image deleteScreenbtnImg;

    @FXML
    private javafx.scene.control.TextField cwdLabel;
    @FXML
    private javafx.scene.control.TextField dtLabel;


    @FXML
    private Button deleteFilesBtn;

    @FXML
    Parent root;


    public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }
    @FXML
    public void goToOptions(ActionEvent event){
        myController.setScreen(template.ScreensFramework.screen3ID);
    }

    @FXML
    public void goToRename(ActionEvent event){
        myController.setScreen(template.ScreensFramework.screen2ID);
    }

    @FXML
    public void goToSplash(ActionEvent event){
        myController.setScreen(template.ScreensFramework.screen1ID);
    }

    //this function does nothing useful with it's parameters, but it is needed for intellij to stop whining
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }


}
