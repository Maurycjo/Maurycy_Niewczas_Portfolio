package pl.edu.pwr.file;

import java.nio.file.Path;
import java.nio.file.Paths;

abstract public class ElementInFileSystem {

    private Path filePath;
    private Path fileName;

    public ElementInFileSystem(String path){
        this.filePath = Paths.get(path);
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

    abstract public String getFileNameWithInfo();



}



