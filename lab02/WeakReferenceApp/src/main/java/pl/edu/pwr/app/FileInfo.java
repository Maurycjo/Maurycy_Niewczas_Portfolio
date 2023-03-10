package pl.edu.pwr.app;

import java.io.File;
//import org.apache.commons.io.FilenameUtils;
import org.apache.velocity.shaded.commons.io.FilenameUtils;

public class FileInfo {

    private String fileName;
    private String path;

    public FileStateEnum getFileState() {
        return fileState;
    }

    private FileStateEnum fileState;

    public boolean isDirectory() {
        return isDirectory;
    }

    private boolean isDirectory;

    public FileInfo(String name){
        fileName = name;
        path = FileHandler.getCurrentPath() + "/" + fileName;

        if(checkIfDirectory()){
            fileState = FileStateEnum.DIRECTORY;
        } else if(FilenameUtils.getExtension(fileName).contains("csv")){
            fileState = FileStateEnum.CSV;
        } else{
            fileState = FileStateEnum.OTHER;
        }


    }
    public String getFileName(){
        return fileName;
    }

    private boolean checkIfDirectory(){
        File file = new File(path);
        if(file.isDirectory()){
            return true;
        }
        else{
            return false;
        }
    }



    public void setFileState(FileStateEnum fileState) {
        this.fileState = fileState;
    }

    enum FileStateEnum {
        DIRECTORY,
        CSV,
        OTHER

    }

    @Override
    public String toString() {
        String text;
        switch (fileState) {
            case DIRECTORY:
                return  "Dir  | " + fileName;
            case CSV:
                return  "Csv  | " + fileName;
            default:
                return  "Inny | " + fileName;
        }

    }

}
