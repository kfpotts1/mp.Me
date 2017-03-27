package Rename;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by kennypotts on 3/26/17.
 */
public class ControllerTest {
    private  Controller testController = new Controller();

    @Test
    public void testSetMoveCMD() throws Exception {
        testController.setMoveCMD("mv");
        assertEquals("vp", testController.getMoveCMD());
    }

    @Test
    public void testSetCopyCMD() throws Exception {
        testController.setCopyCMD("cp");
        assertEquals("cp", testController.getCopyCMD());
    }
}