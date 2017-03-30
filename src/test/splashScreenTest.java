package test;


import javafx.application.Application;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.junit.Test;
import splash.*;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 * Created by jack on 3/27/2017.
 */
public class splashScreenTest {


    //these two tests ensures that the radio buttons controlling which operating system is recorded in the options file works for both the Windows ("W") and Mac ("M") buttons
    @Test
    public void operatingSystemTestMac() throws InterruptedException, IOException {
         String[] operatingSystemTester = new String[1];


       FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("..\\splash\\splash.fxml")); //initialize the splash.splash.fxml file (the GUI itself)
        fxmlLoader.load(); //load the object hierarchy from the fxml document read
        Controller fxmlDocumentController = fxmlLoader.getController(); //create the associated controller object to mess with the .fxml's values/buttons
        fxmlDocumentController.setOperatingSystemMac(); //selecting the macBtn radio button
        operatingSystemTester[0] = fxmlDocumentController.getOperatingSystem(); //checking which operating system has been selected

        assertEquals("M", operatingSystemTester[0]); //validity assertion
    }


    @Test
    public void operatingSystemTestWindows() throws InterruptedException, IOException {
        //pretend all the above comments are also here
        String[] operatingSystemTester = new String[1];
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("..\\splash\\splash.fxml"));
        fxmlLoader.load();
        Controller fxmlDocumentController = fxmlLoader.getController();
        fxmlDocumentController.setOperatingSystemWindows(); //only this time we're selecting the windowsBtn radio button
        operatingSystemTester[0] = fxmlDocumentController.getOperatingSystem();

        assertEquals("W", operatingSystemTester[0]);
    }
}
