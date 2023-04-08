package pl.edu.pwr.file;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class FileElement extends ElementInFileSystem{


    public FileElement(Path filePath){
        super(filePath);

    }

    public String getFileNameWithInfo(){
        return "Plik | " + getFileName();
    }

    public void readFile(){

    }



}
