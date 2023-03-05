package pl.edu.pwr.lib;

import java.io.File;

public class FileInfo {

    private String fileName;
    private String path;
    private FileStateEnum fileState  = FileStateEnum.UNCHANGED;
    private boolean isDirectory;

    public FileInfo(String name){
        fileName = name;
        path = FileHandler.getCurrentPath() + "/" + fileName;
        checkIfDirectory();
    }
    public String getFileName(){
        return fileName;
    }

    private void checkIfDirectory(){
        File file = new File(path);
        if(file.isDirectory()){
            isDirectory=true;
        }
        else{
            isDirectory=false;
        }

    }

    enum FileStateEnum {
        UNCHANGED,
        CHANGED,
        NEW,
        DELETED
    }

    @Override
    public String toString() {
        String text;
        switch (fileState) {
            case UNCHANGED:
                text = "Niezmieniony | "+ fileName;
                break;
            case CHANGED:
                text = "Zmieniony    | " + fileName;
                break;
            case NEW:
                text = "Nowy         | " + fileName;
                break;
            case DELETED:
                text = "Usunięty     | " + fileName;
                break;
            default:
                return "Błąd";
        }
        if(isDirectory){
            text = "Dir  | " + text;
        }
        else{
            text = "File | " + text;
        }
        return text;
    }
}
