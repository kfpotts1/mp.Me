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
        System.out.println("*******************************************");
        System.out.println("*****SPLASH - OPERATING SYSTEM MAC TEST****");
        System.out.println("*******************************************");


       /* FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../splash/Main.java"));
        fxmlLoader.load();
        FXMLDocumentController fxmlDocumentController = fxmlLoader.getController();
            fxmlDocumentController.runScheduledTests(); // convert the method to non-static
        */

        splashScreenController.initialize();
        splashScreenController.setOperatingSystemMac();
        operatingSystemTester[0] = splashScreenController.getOperatingSystem();



        assertEquals("M", operatingSystemTester[0]);
    }

}
