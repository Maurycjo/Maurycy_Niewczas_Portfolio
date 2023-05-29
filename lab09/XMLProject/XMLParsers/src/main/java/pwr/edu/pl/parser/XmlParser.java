package pwr.edu.pl.parser;

import java.io.File;

public interface XmlParser {

    public void load(File file);
    public String getOutput();
    public void serialize();
    public void deserialize();

}
