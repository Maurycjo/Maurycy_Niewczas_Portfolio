package pl.edu.pwr.file;

public class CsvFileElement extends ElementInFileSystem{


    private String content;
    public CsvFileElement(String name){
        super(name);
    }

    public String getFileNameWithInfo(){
        return "Csv  | " + getFileName();
    }

    public String getContent() {
        return content;
    }

    public void contentAfterClicked() {

        content = "This is content of csv file";

    }
}
