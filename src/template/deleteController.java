package template;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import textfile.textFileController;

import java.io.File;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

/**
 * Created by Jack Banta on 3/23/17.
 * This controller handles all the functionality of the delete screen. This includes getting and displaying the date tolerance and current working directory, as well as actually moving the files recommended
 */
public class deleteController implements Initializable, ControlledScreen {
    ScreensController myController;

    @FXML
    private Button optionsScreenBtn;
    @FXML
    private Image optionsScreenbtnImg;

    @FXML
    private Button deleteScreenBtn;
    @FXML
    private Image deleteScreenbtnImg;

    @FXML
    private javafx.scene.control.TextField cwdLabel;
    @FXML
    private javafx.scene.control.TextField dtLabel;


    @FXML
    private Button deleteFilesBtn;

    private textFileController tfController = new textFileController();

    private String directory;

    private String fileType = "mp3";

    private String dateCutoff;

    @FXML
    Parent root;


    public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }
    @FXML
    public void goToOptions(ActionEvent event){
        myController.setScreen(template.ScreensFramework.screen3ID);
    }

    @FXML
    public void goToRename(ActionEvent event){
        myController.setScreen(template.ScreensFramework.screen2ID);
    }

    @FXML
    public void goToSplash(ActionEvent event){
        myController.setScreen(template.ScreensFramework.screen1ID);
    }

    //this function does nothing useful with it's parameters, but it is needed for intellij to stop whining
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    /**
     * Recursively finds all files of a specified type in a directory and its subdirectories
     * with last access date before a specified date
     *
     * @param directory directory in which to search
     * @param unixTime unix time stamp with which to compare last access times
     * @param fileType type of files to search for (mp3, png, jpeg, etc...)
     * @return ArrayList containing absolute paths for files
     */
    private static ArrayList<String> recursiveFindFiles(String directory, long unixTime, String fileType) {
        ArrayList<String> currentFilesToDelete = new ArrayList<>();
        ArrayList<String> subdirectories = new ArrayList<>();

        File folder = new File(directory);
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                String[] parsedName = listOfFiles[i].getName().split(Pattern.quote(".")); // Pattern.quote for regex use
                String suffix = parsedName[parsedName.length-1];
                if (listOfFiles[i].lastModified() < unixTime && suffix.equals(fileType)) { // compare dates
                    currentFilesToDelete.add(listOfFiles[i].getAbsolutePath());
                }

            } else if (listOfFiles[i].isDirectory()) {
                subdirectories.add(listOfFiles[i].getAbsolutePath());
            }
        }
        ArrayList<String> filesToDelete = new ArrayList<>();
        filesToDelete.addAll(currentFilesToDelete);
        for (int i = 0; i < subdirectories.size(); i++) {
            ArrayList<String> subFiles = recursiveFindFiles(subdirectories.get(i), unixTime, fileType);
            filesToDelete.addAll(subFiles);
        }
        return filesToDelete;
    }

    /**
     * finds all files of a specified type in a directory and its subdirectories
     * with last access date before a specified date
     *
     * @param directory directory in which to search
     * @param dateIn formatted date with which to compare last access times
     * @param fileType type of files to search for (mp3, png, jpeg, etc...)
     * @return ArrayList containing absolute paths for files
     */
    private static ArrayList<String> findFilesBeforeDate(String directory, String dateIn, String fileType) {
        String dateFormat = "MM/dd/yyyy"; // TODO: 4/25/17 resolve date format input
        SimpleDateFormat format = new SimpleDateFormat(dateFormat);
        long unixStamp = 0;
        try {
            Date parsedDate = format.parse(dateIn);
            unixStamp = parsedDate.getTime();
        } catch (ParseException e) {
            e.printStackTrace(); // TODO: 4/25/17 logging in deleteController
        }
        return recursiveFindFiles(directory, unixStamp, fileType);
    }

    /**
     * moves specified files to a new folder called "suggestedDelete" in the specified directory
     *
     * @param directory the directory in which to place the files
     * @param files files to be moved to new directory
     */
    private static void suggestDeleteMoveFiles(String directory, ArrayList<String> files) {
        new File(directory+"/suggestedDelete").mkdirs();

        for (int i = 0; i < files.size(); i++) {
            try {
                File file = new File(files.get(i));
                System.out.println(file.getName());
                System.out.println("Attempting to move to: "+directory + "/suggestedDelete/" + file.getName());
                if (file.renameTo(new File(directory + "/suggestedDelete/" + file.getName()))) {
                    System.out.println("file move successful"); // TODO: 4/25/17 logging in deleteController
                } else {
                    System.out.println("file move failed"); // TODO: 4/25/17 logging in deleteController
                }
            } catch (Exception e) {
                e.printStackTrace(); // TODO: 4/25/17 logging in deleteController
            }
        }
    }

    /**
     * Suggests files to be deleted based on directory, file type, and a last access date cutoff
     *
     * updateParams must be called first
     *
     */
    public void suggestDelete() {
        ArrayList<String> files = findFilesBeforeDate(this.directory, this.dateCutoff, this.fileType);
        suggestDeleteMoveFiles(directory, files);
    }



    /**
     * updates datamembers for use in suggestDelete function
     */
    public void updateParams() {
        this.dateCutoff = this.tfController.getDate();
        this.directory = this.tfController.getPath();
    }


    public void deleteBtnTrigger(){
        updateParams();
        suggestDelete();
    }
}
