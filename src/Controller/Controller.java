package Controller;

import Model.Model;
import View.View;

public class Controller implements ControllerInterface {

    private View view;
    private Model model;

    public Controller(View view,Model model){
        this.view=view;
        this.model=model;
    }

    @Override
    public void buttonPressed(String buttonName, String url) {
        if (buttonName.equals("delete"))
            model.delete(url);
        else if(buttonName.equals("add in db"))
            model.addInDB(url);
    }

    @Override
    public void buttonPressed(String buttonName, String url, String path) {
        if(buttonName.equals("search"))
            model.search(url,path);
        else if(buttonName.equals("add in file"))
            model.addInFile(url,path);
    }
}
