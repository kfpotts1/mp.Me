package test;


import javafx.application.Application;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.junit.Test;
import splash.*;
import screenSwapTesting.*;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 * Created by jack on 3/27/2017.
 */


//todo: figure out a way to find the current screen we are on and get the name of that screen

public class swapScreensTest {


    //this test ensures that the program can switch between screens and that our screens are
    //in the nodes we expect them to be
    @Test
    public void swapScreensTest() throws InterruptedException, IOException {

        ScreensController myController = new ScreensController();
        Node currentScreen = myController.getScreen("main");

        FXMLLoader main = new FXMLLoader(getClass().getResource("/screensSwapTesting/Screen1.fxml")); //initialize the splash.splash.fxml file (the GUI itself)
        main.load(); //load the object hierarchy from the fxml document read
        Controller screen1Controller = main.getController(); //create the associated controller object to mess with the .fxml's values/buttons

        FXMLLoader screen2 = new FXMLLoader(getClass().getResource("/screensSwapTesting/Screen2.fxml")); //initialize the splash.splash.fxml file (the GUI itself)
        screen2.load(); //load the object hierarchy from the fxml document read
        Controller screen2Controller = screen2.getController(); //create the associated controller object to mess with the .fxml's values/buttons

        screen1Controller.nextBtn.fire();
        currentScreen = myController.getScreen("screen2");


       assertEquals("screen2", currentScreen); //validity assertion
    }


}
