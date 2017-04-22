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
    public textFileController() {
        //where prefs will be saved
        prefs = Preferences.userRoot().node(this.getClass().getName());
        //see if each setting already exists. if not, set it to a default
         if (prefs.get("OS",null) == null) {
             prefs.put("OS","M");
         }
        if (prefs.get("Input",null) == null) {
            prefs.put("Input","placeholder");
        }
        if (prefs.get("Output",null) == null) {
            prefs.put("Output","placeholder");
        }
        if (prefs.get("Date",null) == null) {
            prefs.put("Date","01/30/2017");
        }
        if (prefs.get("FirstLaunch",null) == null) {
             prefs.put("FirstLaunch", "Yes");
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

}