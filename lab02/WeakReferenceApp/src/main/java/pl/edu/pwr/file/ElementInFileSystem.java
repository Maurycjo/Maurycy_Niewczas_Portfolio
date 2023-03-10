package pl.edu.pwr.file;

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
    abstract public void contentAfterClicked();


}



