package template;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;

import java.io.File;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import template.ControlledScreen;
import textfile.textFileController;

/**
 * @author Ben
 * This class controls the options screen. It handles creating settings for the operating system, date tolerance,
 * input and output conventions needed for rename and delete functions
 */
public class optionsController implements Initializable, ControlledScreen {

    private static final Logger LOGGER = Logger.getLogger( optionsController.class.getName() );

    ScreensController myController;

    @FXML
    public ToggleGroup operatingSystemButtons;



    @FXML
    private Button renameBtn;
    @FXML
    private ImageView renameBtnImg;

    @FXML
    private Label cwdLabel; //current working directory label
    @FXML
    private Button cwdBtn;


    @FXML
    private TextField nciTextField; //naming convention input label
    @FXML
    private Button nciBtn;

    @FXML
    private TextField ncoTextField; //naming convention output label
    @FXML
    private Button ncoBtn;

    @FXML
    private Label dtLabel; //date tolerance label
    @FXML
    private TextField dtTextField; //date tolerance label
    @FXML
    private Button dtBtn;

    @FXML
    private Button cosBtn; //change operating system button
    @FXML
    private RadioButton windowsBtn; //change operating system to Windows
    @FXML
    private RadioButton macBtn; //change operating system to Mac

    @FXML
    private Button resetBtn; //reset to default button

    @FXML
    private Button helpbtn; //help menu button

    @FXML
    Parent root;

    /**
     * @param screenParent
     * @post the current screen ID is set to be the current screen to be controlled
     */
    public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }


    public String os, input, output, directory, date;
    public textfile.textFileController sender = new textFileController();


    /**
     *
     * @param event
     * @pre the rename button is selected
     * @post screen2ID is set as the current screen
     */
    @FXML
    public void goToRename(ActionEvent event){
        myController.unloadScreen("rename");
        myController.loadScreen(ScreensFramework.screen2ID, ScreensFramework.screen2File);
        myController.setScreen(template.ScreensFramework.screen2ID);
    }

    /**
     *
     * @param event
     * @pre the delete button is selected
     * @post screen4ID is set as the current screen
     */
    public void goToDelete(ActionEvent event){
        myController.unloadScreen("delete");
        myController.loadScreen(ScreensFramework.screen4ID, ScreensFramework.screen4File);
        myController.setScreen(template.ScreensFramework.screen4ID);
    }

    //this function does nothing useful with it's parameters, but it is needed for intellij to stop whining

    /**
     * @param url unused
     * @param rb unused
     * @return void
     * @pre
     * @post
     */
    @Override

    public void initialize(URL url, ResourceBundle rb) {
        String cwd = sender.getPath();
        String dt = sender.getDate();
        String nco = sender.getOutput();
        String nci = sender.getInput();
        os = sender.getOS();
        cwdLabel.setText(cwd);
        ncoTextField.setText(output);
        nciTextField.setText(input);

        if (os.equals("W")){
            windowsBtn.setSelected(true);
        } else if (os.equals("M") ){
            macBtn.setSelected(true);
        }


    }




    /**
     * @pre the "change operating system" button has been selected by user
     * @post the current operating system is saved the textfile
     */
    public void setOperatingSystem(){
        if (windowsBtn.isSelected()){
            os = "W";
            sender.setOS(os);
        } else if (macBtn.isSelected())  {
            os = "M";
        }
        else {
            LOGGER.log(Level.SEVERE, "No operating system selected in options screen!");

        }
        LOGGER.log(Level.INFO,"Operating System Selected: " + os);

    }

    /**
     * @pre the "change input" button is selected
     * @post the input convention is saved in the textfile
     */
    public void setInput() {
        if(nciTextField.getText() != null) {
            input = nciTextField.getText();
            sender.setInput(input);
        }else{
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setTitle("mpMe Alert");
            a.setHeaderText("Invalid Input Convention");
            a.setResizable(true);
            String version = System.getProperty("java.version");
            String content = String.format("You may not leave this field blank", version);
            a.setContentText(content);
            a.showAndWait();
        }
        LOGGER.log(Level.INFO, "Naming convention Input: " + input);
    }

    /**
     * @pre the "change output" button is selected
     * @post the output convention is saved in the textfile
     */
    public void setOutput() {
        if(ncoTextField.getText() != null) {
            output = ncoTextField.getText();
            sender.setOutput(output);
        }else{
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setTitle("mpMe Alert");
            a.setHeaderText("Invalid Output");
            a.setResizable(true);
            String version = System.getProperty("java.version");
            String content = String.format("You may not leave this field blank", version);
            a.setContentText(content);
            a.showAndWait();
        }
        LOGGER.log(Level.INFO, "Naming convention output: " + output);
    }


    /**
     * @pre the "select directory" button is selected
     * @post the directory selected will be used as the range of search for delete and rename functions
     */

    public void setDirectory(){
        DirectoryChooser fc = new DirectoryChooser();
        File selectedFolder = fc.showDialog(null);
        if (selectedFolder != null){
                cwdLabel.setText(selectedFolder.toString());
                sender.setPath(selectedFolder.toString());
            LOGGER.log(Level.INFO,"Current working directory: " + selectedFolder.toString());

        } else {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setTitle("mpMe Alert");
            a.setHeaderText("Invalid Directory Selection");
            a.setResizable(true);
            String version = System.getProperty("java.version");
            String content = String.format("Please select a valid directory path", version);
            a.setContentText(content);
            a.showAndWait();
        }

    }


    /**
     * @pre the "set date tolerance" button is selected and user input is valid
     * @post date is updated to user's string
     */
    public void setDate() {
        String month, day, year;
        String dateField = dtTextField.getText();
        //see if a date was even entered
        if (dateField.equals("")) {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setTitle("mpMe Alert");
            a.setHeaderText("No date given.");
            a.setResizable(true);
            String version = System.getProperty("java.version");
            String content = String.format("Please enter a valid date in DD/MM/YYYY format.", version);
            a.setContentText(content);
            a.showAndWait();

        }else {

            char[] parseCheck = dateField.toCharArray();
             int parseCount = 0;
            for(int i = 0; i < parseCheck.length; i++){
                if(parseCheck[i] == '/'){
                    parseCount++;
                }
            }
if(parseCount == 2) {
    //splits the date entered into month, day, year
    String newString[] = dateField.split("/");
    String monthField = newString[0];
    String dayField = newString[1];
    String yearField = newString[2];
    // splits the date into month, day, year
/**
    //date is the previous date.
    date = sender.getDate();

    String dateString[] = date.split("/");
    month = dateString[0];
    day = dateString[1];
    year = dateString[2];
 **/
    //user sets 3 date indicators from stringfields
    //char checks for valid date
    //2 char between ints 01-12 for month
    if ((monthField.length() == 2) && (Integer.parseInt(monthField) > 0) && (Integer.parseInt(monthField) < 13)) {
        //2 char between ints 01-31
        if ((dayField.length() == 2) && (Integer.parseInt(dayField) > 0) && (Integer.parseInt(dayField) < 32)) {
            //4 char ints above 1970
            if (yearField.length() == 4){
                year = yearField;
                day = dayField;
                month = monthField;
                date = month + "/" + day + "/" + year;
                sender.setDate(date);
            }else{
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setTitle("mpMe Alert");
                a.setHeaderText("Invalid Year Value Given.");
                a.setResizable(true);
                String version = System.getProperty("java.version");
                String content = String.format("Please enter a 4-digit number for the year.", version);
                a.setContentText(content);
                a.showAndWait();
            }
        }else{
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setTitle("mpMe Alert");
            a.setHeaderText("Invalid Day Value Given.");
            a.setResizable(true);
            String version = System.getProperty("java.version");
            String content = String.format("Please enter a 2-digit number between 01 and 31 for the day.", version);
            a.setContentText(content);
            a.showAndWait();
        }
    }else{
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle("mpMe Alert");
        a.setHeaderText("Invalid Month Value Given.");
        a.setResizable(true);
        String version = System.getProperty("java.version");
        String content = String.format("Please enter a 2-digit number between 01 and 12 for month.", version);
        a.setContentText(content);
        a.showAndWait();
    }
}else{
    Alert a = new Alert(Alert.AlertType.INFORMATION);
    a.setTitle("mpMe Alert");
    a.setHeaderText("Could not identify month, day, year");
    a.setResizable(true);
    String version = System.getProperty("java.version");
    String content = String.format("Please enter a valid date in DD/MM/YYYY format. Please include slashes.", version);
    a.setContentText(content);
    a.showAndWait();
}
        }
        LOGGER.log(Level.INFO, "Date is " + date);
    }


    /**
     * @pre the "default settings" button is selected
     * @post settings are reverted to default as if mp.me has never been launched before
     */
    public void clearOptions(){

//        Alert a = new Alert(Alert.AlertType.INFORMATION);
//        a.setTitle("mpMe Alert");
//        a.setHeaderText("No date given.");
//        a.setResizable(true);
//        String version = System.getProperty("java.version");
//        String content = String.format("Please enter a valid date in DD/MM/YYYY format.", version);
//        a.setContentText(content);
//        a.showAndWait();
        input = "";
        output = "";
        date = "01/30/2017";
        if(os == "W") {
            directory = "C:\\";
        }else{
            directory = "~/";
        }
        cwdLabel.setText(directory);
        os = "X"; //for TempleOS
        //pass everything to TFC
        sender.setInput(input);
        sender.setOS(os);
        sender.setDate(date);
        sender.setOutput(output);
        sender.setFirstLaunch("No");

        LOGGER.log(Level.INFO, "Date is " + sender.getDate());
        LOGGER.log(Level.INFO, "OS is " + sender.getOS());
        LOGGER.log(Level.INFO, "first launch is " + sender.getFirstLaunch());

    }


    /**
     * @pre call to collect option settings from textFileController
     * @post os, input, output, and date settings are retrieved by a textfile
     */
    public void collectOptions() {
        textfile.textFileController opt = new textFileController();
        os = opt.getOS();
        input = opt.getInput();
        output = opt.getOutput();
        date = opt.getDate();
    }

    /**
     * @return entered input for rename conventions
     * @pre textfile or options need to retrieve current input settings within its functions
     */
    public String getInput() {
        return input;
    }

    /**
     * @return entered output for rename conventions
     * @pre textfile or options need to retreive current output settings within its functions
     */
    public String getOutput() {
        return output;
    }

    /**
     * @return entered date tolerance for delete conventions
     * @pre textfile or options need to retreive current date settings within its functions
     */
    public String getDate() {
        return date;
    }

    /**
     * @return the selected operating system
     * @pre textfile or options need to retreive current input settings within its functions
     */
    public String getOs() { return os; }

}