package pl.edu.pwr.file;

public class CsvFileElement extends FileElement{



    public CsvFileElement(String filePath){
        super(filePath);
    }

    public String getFileNameWithInfo(){
        return "Csv  | " + getFileName();
    }




}
