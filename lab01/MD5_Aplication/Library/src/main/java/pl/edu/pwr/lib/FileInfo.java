package pl.edu.pwr.lib;

public class FileInfo {

    private String fileName;
    private FileStateEnum fileState  = FileStateEnum.UNCHANGED;

    public FileInfo(String name){
        fileName = name;
    }

    enum FileStateEnum {
        UNCHANGED,
        CHANGED,
        NEW,
        DELETED
    }

    @Override
    public String toString() {
        switch (fileState) {
            case UNCHANGED:
                return "Niezmieniony | "+ fileName;
            case CHANGED:
                return "Zmieniony    | " + fileName;
            case NEW:
                return "Nowy         | " + fileName;
            case DELETED:
                return "Usunięty     | " + fileName;
            default:
                return "Błąd";
        }
    }
}
