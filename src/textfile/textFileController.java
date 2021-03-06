package textfile;
import java.util.prefs.Preferences;


public class textFileController {
    //Implementation of user preferences via the more robust Preferences API
    public Preferences prefs;

    /**
     * Constructor
     * @return a new instance of optionsFile
     * @pre nothing, the class will check to see if preferences already exist
     * @post preferences are saved on the users computer. where is an implementation-specific detail
     */
    public textFileController(){
        //where prefs will be saved
        try {
            prefs = Preferences.userRoot().node(this.getClass().getName());
            //see if each setting already exists. if not, set it to a default
            if (prefs.get("OS", null) == null) {
                prefs.put("OS", "X");
            }
            if (prefs.get("Input", null) == null) {
                prefs.put("Input", "No input given");
            }
            if (prefs.get("Output", null) == null) {
                prefs.put("Output", "No output given");
            }
            if (prefs.get("Path", null) == null) {
                prefs.put("Path", "No directory selected");
            }
            if (prefs.get("Date", null) == null) {
                prefs.put("Date", "01/30/2017");
            }
            if (prefs.get("FirstLaunch", null) == null) {
                prefs.put("FirstLaunch", "No");
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }
    //Getters: all return null if the query fails
    public String getOS() {
        return prefs.get("OS", null);
    }
    public String getInput() {
        return prefs.get("Input", null);
    }
    public String getOutput() {
        return prefs.get("Output", null);
    }
    public String getDate() {
        return prefs.get("Date",null);
    }
    public String getFirstLaunch() {
        return prefs.get("FirstLaunch",null);
    }
    public String getPath() {
        return prefs.get("Path", null);
    }

    //Setters
    public void setOS(String os) {
        prefs.put("OS", os);
    }
    public void setInput(String input) {
        prefs.put("Input", input);
    }
    public void setOutput(String output) {
        prefs.put("Output", output);
    }
    public void setDate(String date) {
        prefs.put("Date", date);
    }
    public void setFirstLaunch(String firstLaunch) {
        prefs.put("FirstLaunch", firstLaunch);
    }
    public void setPath(String path) {
        prefs.put("Path", path);
    }

}