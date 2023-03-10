package pl.edu.pwr.file;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DirElement extends ElementInFileSystem{




    public DirElement(String name){
        super(name);

    }

    public String getFileNameWithInfo(){
        return "Dir   | " + getFileName();
    }

    //in dir go into directory 1 level down
    public void contentAfterClicked(){


    }




}
