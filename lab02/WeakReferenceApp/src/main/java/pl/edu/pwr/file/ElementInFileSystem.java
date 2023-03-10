package pl.edu.pwr.file;

import java.io.File;
import org.apache.velocity.shaded.commons.io.FilenameUtils;

abstract public class ElementInFileSystem {

    private String fileName;


    public ElementInFileSystem(String name){
        fileName=name;
    }
    public String getFileName(){
        return fileName;
    }
    public void setFileName(String name){
        fileName=name;
    }

    abstract public String getFileNameWithInfo();
    abstract public void doubleClickedAction();


}



