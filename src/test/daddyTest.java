package test;

//import org.testng.annotations.Test;

import org.junit.Test;
import template.daddy.*;


import static org.junit.Assert.*;

/**
 * Created by Ben on 3/27/2017.
 */
public class daddyTest {

    @Test
    //tests that the screen type passed in is different
    //from the type already in use and changes it if so
    public void testSwitchScreen() {
        String newScreen = "delete";
        template.daddy.switchScreen(newScreen);
        if(template.daddy.getScreen() == "delete"){
            System.out.println("Pass");
        }else{
            System.out.println("Fail: No screen variable change");
        }


    }
}