package pl.edu.pwr.lib;

public class FileInfo {

    private String fileName;
    private FileStateEnum fileState  = FileStateEnum.UNCHANGED;

    enum FileStateEnum {
        UNCHANGED,
        CHANGED,
        NEW,
        DELETED
    }

    public String textInfo(){
        switch (fileState) {
            case UNCHANGED:
                return fileName + "Niezmieniony";
            case CHANGED:
                return fileName + "Zmieniony";
            case NEW:
                return fileName + "Nowy";
            case DELETED:
                return fileName + "Usunięty";
            default:
                return "Błąd";
        }
    }

}
