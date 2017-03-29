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


    @Test
    public void operatingSystemTestMac() throws InterruptedException, IOException {
        final String[] operatingSystemTester = new String[1];
        splash.Controller splashScreenController = new splash.Controller();


       FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("..\\splash\\splash.fxml"));
        fxmlLoader.load();
        Controller fxmlDocumentController = fxmlLoader.getController();
        fxmlDocumentController.setOperatingSystemMac();
        operatingSystemTester[0] = fxmlDocumentController.getOperatingSystem(); // convert the method to non-static



        assertEquals("M", operatingSystemTester[0]);
    }


    @Test
    public void operatingSystemTestWindows() throws InterruptedException, IOException {
        final String[] operatingSystemTester = new String[1];
        splash.Controller splashScreenController = new splash.Controller();


        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("..\\splash\\splash.fxml"));
        fxmlLoader.load();
        Controller fxmlDocumentController = fxmlLoader.getController();
        fxmlDocumentController.setOperatingSystemWindows();
        operatingSystemTester[0] = fxmlDocumentController.getOperatingSystem(); // convert the method to non-static



        assertEquals("W", operatingSystemTester[0]);
    }
}
