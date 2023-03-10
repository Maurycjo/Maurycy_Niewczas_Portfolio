package pl.edu.pwr.file;

public class OtherFileElement extends ElementInFileSystem{

    public OtherFileElement(String name){
        super(name);
    }

    public String getFileNameWithInfo(){
        return "Inny | " + getFileName();
    }

    @Override
    public void contentAfterClicked() {

    }
}
