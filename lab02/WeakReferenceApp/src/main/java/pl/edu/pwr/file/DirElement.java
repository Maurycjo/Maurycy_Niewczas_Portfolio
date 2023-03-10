package pl.edu.pwr.file;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DirElement extends ElementInFileSystem{

    public DirElement(String name){
        super(name);
    }

    public String getFileNameWithInfo(){
        return "Dir  | " + getFileNameWithInfo();
    }

    //in dir go into directory 1 level down
    public void doubleClickedAction(){

    }

    public String childPath(String currentPath, String fileName){
        //set path 1 level down
        Path path = Paths.get(currentPath + "/" + fileName);
        if(Files.isDirectory(path)){
            currentPath+="/"+fileName;
        }
        return currentPath;
    }

}
