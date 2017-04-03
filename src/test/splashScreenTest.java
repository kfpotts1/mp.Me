package test;


import javafx.application.Application;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.junit.BeforeClass;
import org.junit.Test;
import splash.*;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 * Created by jack on 3/27/2017.
 */
public class splashScreenTest {


    //start() and initJFX() are required for GUI testing as JUnit can't run a test on a JavaFX GUI if the GUI doesn't initialize
    public static class splash extends Application {
        @Override
        public void start(Stage primaryStage) throws Exception {
            // noop
        }
    }

    @BeforeClass
    public static void initJFX() {
        Thread t = new Thread("JavaFX Init Thread") {
            public void run() {
                Application.launch(splash.class, new String[0]);
            }
        };
        t.setDaemon(true);
        t.start();
    }


    //these two tests ensures that the radio buttons controlling which operating system is recorded in the options file works for both the Windows ("W") and Mac ("M") buttons
    @Test
    public void operatingSystemTestMac() throws InterruptedException, IOException {
        String[] operatingSystemTester = new String[1];


        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/splash/splash.fxml")); //initialize the splash.splash.fxml file (the GUI itself)
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
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/splash/splash.fxml"));
        fxmlLoader.load();
        Controller fxmlDocumentController = fxmlLoader.getController();
        fxmlDocumentController.setOperatingSystemWindows(); //only this time we're selecting the windowsBtn radio button
        operatingSystemTester[0] = fxmlDocumentController.getOperatingSystem();

        assertEquals("W", operatingSystemTester[0]);
    }

    //todo public void hasVisitedTest(){}
}
