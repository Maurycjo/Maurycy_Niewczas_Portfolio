package pl.edu.pwr.file;

public class DirElement extends ElementInFileSystem{


    public DirElement(String dirPath){
        super(dirPath);
    }

    public String getFileNameWithInfo(){
        return "Dir   | " + getFileName();
    }

}

