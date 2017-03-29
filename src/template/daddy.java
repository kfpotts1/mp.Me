package template;

/**
 * Created by Jack Banta on 3/25/17.
 */
public class daddy {
    String currScreen;

    public daddy(){
        this.currScreen = "rename";
    }

    public void switchScreen(String newScreen){

        if(newScreen != this.currScreen){
            //set the selected tab to be the current screen
            this.currScreen = newScreen;
            //call constructor for the correct screen
            /**
            if (currScreen == 'rename'){

            }else if(currScreen == 'delete'){

            }else if(currScreen == 'options'){

            }else if (currScreen == 'help'){

            }else{

            }
             */
        }
    }

    public String getScreen(){
        return this.currScreen;
    }


}
