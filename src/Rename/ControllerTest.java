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
        assertEquals("mv", testController.getMoveCMD());
    }

    @Test
    public void testSetCopyCMD() throws Exception {
        testController.setCopyCMD("cp");
        assertEquals("cp", testController.getCopyCMD());
    }

    @Test
    public void testRenameFile() throws Exception {
        testController.setMoveCMD("mv");
        // testController.renameFile("test_music_1.mp3", "TEST_MUSIC_1.mp3", "/Users/kennypotts/Desktop/mp.Me/test_files");
        testController.renameFile("TEST_MUSIC_1.mp3", "test_music_1.mp3", "/Users/kennypotts/Desktop/mp.Me/test_files");
    }
}