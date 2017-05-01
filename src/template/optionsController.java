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
     *
     * @param screenParent
     * @return void
     * @pre
     * @post
     */
    public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }


    public String os, input, output, directory, date;
    public textfile.textFileController sender = new textFileController();


    /**
     *
     * @param event
     * @return void
     * @pre the rename button is selected
     * @post screen2ID is set as the current screen
     */
    @FXML
    public void goToRename(ActionEvent event){
        myController.setScreen(template.ScreensFramework.screen2ID);
    }

    public void goToDelete(ActionEvent event){
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
        //collectOptions();
    }


    /**
     * @return os
     * @pre the "change operating system" button has been selected by user
     */
    public void setOperatingSystem(){
        if (windowsBtn.isSelected()){
            os = "W";
            //textfile.textFileController sender = new textFileController();
            sender.setOS(os);
        } else if (macBtn.isSelected())  {
            os = "M";
        }
        else {
            System.out.println("No operating system selected");
            //todo throw
        }
        System.out.println("operatingSystem: " + os);
    }

    /**
     * @return void
     * @pre the "change input" button is selected
     * @post
     */
    public void setInput() {
        input = nciTextField.getText();
        sender.setInput(input);
    }

    /**
     * @return void
     * @pre the "change output" button is selected
     * @post
     */
    public void setOutput() {
        output = ncoTextField.getText();
        sender.setOutput(output);
    }

    public void setDirectory(){
        DirectoryChooser fc = new DirectoryChooser();
        File selectedFolder = fc.showDialog(null);
        if (selectedFolder != null){
                cwdLabel.setText(selectedFolder.toString());
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
     * @return void
     * @pre the "set date tolerance" button is selected and user input is valid
     * @post date is updated to user's string
     */



    public void setDate() {
        String month, day, year;
        String dateField = dtTextField.getText();
        //see if a date was even entered
        if (dateField == "") {
            System.out.println("No text entered");
        }



        //splits the date entered into month, day, year
        String newString[] = dateField.split("/");
        String monthField = newString[0];
        String dayField = newString[1];
        String yearField = newString[2];
        // splits the date into month, day, year

        //date is the previous date.
        date = sender.getDate();

        String dateString[] = date.split("/");
        month = dateString[0];
        day = dateString[1];
        year = dateString[2];
        //user sets 3 date indicators from stringfields
        //char checks for valid date
        //2 char between ints 01-12
        if ((monthField.length() == 2)) {
            if ((Integer.parseInt(monthField) > 0) && (Integer.parseInt(monthField) < 13)) {
                month = monthField;
                //2 char between ints 01-31
                if ((dayField.length() == 2)) {
                    if ((Integer.parseInt(dayField) > 0) && (Integer.parseInt(dayField) < 32)) {
                        day = dayField;
                        //4 char ints above 1970
                        if ((yearField.length() == 4)) {
                            if ((Integer.parseInt(yearField)) > 1969) {
                                year = yearField;
                                day = yearField;
                                month = monthField;
                            }
                        }
                    }
                }
            }
        }
        date = month + "/" + day + "/" + year;
        sender.setDate(date);
    }

    public void clearOptions(){
        input = "";
        output = "";
        date = "";
        if(os == "W") {
            directory = "C:\\";
        }else{
            directory = "~/";
        }
    }


    /**
     * @return void
     * @pre
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
     * @param
     * @return input
     * @pre
     * @post
     */
    public String getInput() {
        return input;
    }

    /**
     * @param
     * @return output
     * @pre
     * @post
     */
    public String getOutput() {
        return output;
    }

    /**
     * @param
     * @return date
     * @pre
     * @post
     */
    public String getDate() {
        return date;
    }

    /**
     *
     * @return the preferred operating system is taken from options
     * @@pre
     */
    public String getOs() { return os; }

}