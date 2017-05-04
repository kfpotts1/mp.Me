package template;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import textfile.textFileController;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

/**
 * Created by kennypotts on 3/23/17.
 */
public class renameController implements Initializable, ControlledScreen {
    ScreensController myController;


    @FXML
    private Button optionsScreenBtn;
    @FXML
    private Image optionsScreenbtnImg;

    @FXML
    private Label cwdLabel;
    @FXML
    private Label nciLabel;
    @FXML
    private Label ncoLabel;

    @FXML
    private Button renameFilesBtn;

    @FXML
    Parent root;

    private textFileController optionsFile = new textFileController();
    private String directory;

    private String fileType = "mp3";
    private String input = "trackID_artist_title";
    private String output = "title__trackID__artist";
    private boolean recursiveRename = true;



    public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }
    @FXML
    public void goToOptions(ActionEvent event){
        myController.setScreen(template.ScreensFramework.screen3ID);
    }


    public void goToDelete(ActionEvent event){
        myController.unloadScreen("delete");
        myController.loadScreen(ScreensFramework.screen4ID, ScreensFramework.screen4File);
        myController.setScreen(template.ScreensFramework.screen4ID);
    }

    //this function does nothing useful with it's parameters, but it is needed for intellij to stop whining
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        String cwd = optionsFile.getPath();
        String dt = optionsFile.getDate();
        String nco = optionsFile.getOutput();
        String nci = optionsFile.getInput();

        ncoLabel.setText(nco);
        nciLabel.setText(nci);
        cwdLabel.setText(cwd);

    }

    private ArrayList<String> recursiveFindFilesToRename(String directory, String inputSplitter,
                                                        int numTerms, String fileType) {
        ArrayList<String> currentFilesToRename = new ArrayList<>();
        ArrayList<String> subdirectories = new ArrayList<>();

        File folder = new File(directory);
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                String[] parsedName = listOfFiles[i].getName().split(Pattern.quote(".")); // Pattern.quote for regex use
                String suffix = parsedName[parsedName.length - 1];
                if (listOfFiles[i].getName().split(inputSplitter).length == numTerms && suffix.equals(fileType)) {
                    currentFilesToRename.add(listOfFiles[i].getAbsolutePath());
                }

            } else if (listOfFiles[i].isDirectory()) {
                subdirectories.add(listOfFiles[i].getAbsolutePath());
            }
        }
        if (this.recursiveRename) {
            ArrayList<String> filesToRename = new ArrayList<>();
            filesToRename.addAll(currentFilesToRename);
            for (int i = 0; i < subdirectories.size(); i++) {
                ArrayList<String> subFiles = recursiveFindFilesToRename(subdirectories.get(i), inputSplitter,
                        numTerms, fileType);
                filesToRename.addAll(subFiles);
            }
            return filesToRename;
        } else {
            return currentFilesToRename;
        }
    }

    /**
     * uses the NCI and NCO to return an order with which to modify file names.
     * @return order of modification
     * @throws Exception
     */
    private String[] genNewPaths(String[] oldPaths, String inputSplitter, String outputSplitter) {

        String[] splitInput = this.input.split(inputSplitter);
        String[] splitOutput = this.output.split(outputSplitter);



        int[] order = new int[splitOutput.length];

        for (int i = 0; i < splitInput.length; i++) {
            for (int j = 0; j < splitOutput.length; j++) {
                if (splitInput[i].equals(splitOutput[j])) {
                    order[j] = i;
                }
            }
        }


        String[] newPaths = new String[oldPaths.length];

        for (int i = 0; i < oldPaths.length; i++) {

            File file = new File(oldPaths[i]);
            String fileNameFull = file.getName();

            String oldFileName = fileNameFull.split(Pattern.quote("."))[0];

            String fileSuffix = fileNameFull.split(Pattern.quote("."))[1];



            String[] oldNameParts = oldFileName.split(inputSplitter);
            String[] newNameParts = new String[splitOutput.length];

            for (int j = 0; j < order.length; j++) {
                newNameParts[j] = oldNameParts[order[j]];
            }
            String newFileName = String.join(outputSplitter,newNameParts) + "." + fileSuffix;

            StringBuilder b = new StringBuilder(oldPaths[i]);

            b.replace(oldPaths[i].length() - fileNameFull.length(), oldPaths[i].length(), newFileName);
            String newPath = b.toString();

            newPaths[i] = newPath;
        }

        return newPaths;
    }

    private static void renameFromPaths(String[] oldPaths, String[] newPaths) {

        for (int i = 0; i < oldPaths.length; i++) {
            try {
                File file = new File(oldPaths[i]);
                File newFile = new File(newPaths[i]);

                if (file.renameTo(newFile)) {
                    System.out.println("file rename successful"); //TODO: logging
                    System.out.println("Renamed "+file.getName()+" to "+newFile.getName());
                } else {
                    System.out.println("file rename failed: "+file.getName()); //TODO: logging
                }
            } catch (Exception e) {
                e.printStackTrace(); //TODO: logging
            }

        }

    }


    private void doRenameFiles() throws Exception{
        String inputSplitter, outputSplitter = null;
        if (this.input.contains("__")) {
            inputSplitter = "__";
        } else if (this.input.contains("_")) {
            inputSplitter = "_";
        } else if (this.input.contains("__")) {
            inputSplitter = "__";
        } else if (this.input.contains(" - ")) {
            inputSplitter = " - ";
        } else if (this.input.contains("-")) {
            inputSplitter = "-";
        } else if (this.input.contains(" ")) {
            inputSplitter = " ";
        } else {
            throw new Exception("No valid delimiter found in input string."); //TODO: we may want to revisit this
        }
        if (this.output.contains("__")) {
            outputSplitter = "__";
        } else if (this.output.contains("_")) {
            outputSplitter = "_";
        } else if (this.output.contains(" - ")) {
            outputSplitter = " - ";
        } else if (this.output.contains("--")) {
            outputSplitter = "--";
        } else if (this.output.contains("-")) {
            outputSplitter = "-";
        } else if (this.output.contains(" ")) {
            outputSplitter = " ";
        } else {
            throw new Exception("No valid delimiter found in output string."); //TODO: we may want to revisit this
        }
        int numTerms = this.input.split(inputSplitter).length;

        ArrayList<String> toRenameList = recursiveFindFilesToRename(this.directory, inputSplitter, numTerms, this.fileType);
        String[] toRename = new String[toRenameList.size()];
        toRename = toRenameList.toArray(toRename); // convert to standard string array, ArrayList no longer needed

        String[] newPaths = this.genNewPaths(toRename, inputSplitter, outputSplitter);

        renameFromPaths(toRename, newPaths);
    }

    /**
     * updates datamembers for use in suggestDelete function
     */
    public void updateParams() {
        this.input = this.optionsFile.getInput();
        this.output = this.optionsFile.getOutput();
        this.directory = this.optionsFile.getPath();
        //TODO: potentially filetype and recursive option in tf-controller
    }

    public void renameBtnTrigger() {
        this.updateParams();
        try {
            this.doRenameFiles();
        } catch (Exception e) {
            // TODO: log, no files to rename
        }
    }


}
