package test;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import org.junit.Test;
import splash.*;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 * Created by jack on 3/27/2017.
 */
public class splashScreenTest {
    @Test
    public void operatingSystemTestMac(){
        System.out.println("*******************************************");
        System.out.println("*****SPLASH - OPERATING SYSTEM MAC TEST****");
        System.out.println("*******************************************");
        splash.Controller splashScreen = new splash.Controller();
        final String[] operatingSystemTester = new String[1];
        splashScreen.nextBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                operatingSystemTester[0] = splashScreen.getOperatingSystem();
            }
        });

        assertEquals("M", operatingSystemTester[0]);
    }

}
