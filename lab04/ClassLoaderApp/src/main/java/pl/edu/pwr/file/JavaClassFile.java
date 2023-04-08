package pl.edu.pwr.file;

import java.nio.file.Path;
import java.util.ArrayList;

public class JavaClassFile extends FileElement{

    private ArrayList<String> methodArrayList = new ArrayList<String>();
    private String className = "Testowa klasa";
    public JavaClassFile(Path filePath) {
        super(filePath);
        methodArrayList.add("jeden"); //testowe elementy w liscie
        methodArrayList.add("dwa");
        methodArrayList.add("trzy");
    }

    public ArrayList<String> getMethodArrayList(){

        return methodArrayList;
    }

    public String getClassName() {
        return className;
    }
}
