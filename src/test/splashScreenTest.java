package test;


import javafx.application.Application;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
    public void operatingSystemTestMac() throws InterruptedException{
        final String[] operatingSystemTester = new String[1];
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                new JFXPanel(); // Initializes the JavaFx Platform
                Platform.runLater(new Runnable() {


                    @Override
                    public void run() {
                        final splash.Main splashScreen = new splash.Main();
                        Application.launch(splashScreen);
                        System.out.println("*******************************************");
                        System.out.println("*****SPLASH - OPERATING SYSTEM MAC TEST****");
                        System.out.println("*******************************************");


                        operatingSystemTester[0] = splashScreen.getOperatingSystem();

                    }
                });
            }
        });
        thread.start();// Initialize the thread
        Thread.sleep(10000); // Time to use the app, with out this, the thread
        // will be killed before you can tell.
        assertEquals("M", operatingSystemTester[0]);
    }

}
