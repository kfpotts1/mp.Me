package options;

public class ControlOptions {
    String os, input, output, date;

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
        String month, day, year;
        //set 3 date indicators from stringfields
        this.date = month + "/" + "/" +day + "/" + year;
    }

    //get variables from the textFileController
    public void getOptions(String[] optArr){
        this.os = optArr[0];
        this.input = optArr[1];
        this.output = optArr[2];
        this.date = optArr[3];
    }

    //Sends all variables to the textFileController
    public void reload(){


    }

}