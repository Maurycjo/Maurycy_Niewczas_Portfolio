package pwr.edu.pl.parser;

import java.io.File;

public abstract class XmlParser {

    File file;
    String outputToDisplay;

    public void load(File file){
        this.file = file;
    }
    public String getOutput(){
        return outputToDisplay;
    }
    abstract public void deserialize();


}
