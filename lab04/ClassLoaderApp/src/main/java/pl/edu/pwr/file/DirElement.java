package pl.edu.pwr.file;

import java.nio.file.Path;

public class DirElement extends ElementInFileSystem{


    public DirElement(Path dirPath){
        super(dirPath);
    }

    public String getFileNameWithInfo(){
        return "Dir   | " + getFileName();
    }

}

