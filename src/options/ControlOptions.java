package options

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class ControlOptions {

    //constructor
    public ControlOptions(String os, String input, String output, String date){
        this.os = os;
        this.input = input;
        this.output = output;
        this.date = date;

    }


    public void setOS(){


    }

    public void setInput(){


    }

    public void setOutput(){


    }

    public void setDate(){

    }

    //get variables from the textFileController
    public void getOptions(){

    }

    //Sends all variables to the textFileController
    public void reload(){

    }

}