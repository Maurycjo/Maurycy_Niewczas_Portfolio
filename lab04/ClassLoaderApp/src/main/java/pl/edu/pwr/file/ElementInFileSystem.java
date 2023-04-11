package pl.edu.pwr.file;

import java.nio.file.Path;
import java.nio.file.Paths;

abstract public class ElementInFileSystem {

    private Path filePath;
    private Path fileName;

    public ElementInFileSystem(Path path){
        this.filePath = path;
        this.fileName = this.filePath.getFileName();
    }

    public Path getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = Paths.get(filePath);
    }

    public String getFileName() {
        return fileName.toString();
    }

    public String getFileNameWithoutExtension(){

        String name = fileName.toString();
        int pos = name.lastIndexOf(".");
        if(pos == -1) return name;

        return name.substring(0, pos);
    }

    abstract public String getFileNameWithInfo();

    public void readFile() {
    }
}



