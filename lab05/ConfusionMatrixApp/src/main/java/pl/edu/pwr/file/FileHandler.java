package pl.edu.pwr.file;

import java.nio.file.*;
import java.util.ArrayList;
import java.util.stream.Stream;

public class FileHandler {


    private Path currentPath = Paths.get("../classToLoad/out/production/classToLoad");

    private ArrayList<Path> filesPath = new ArrayList<>();
    private ArrayList<Path> classPath = new ArrayList<>();


    public ArrayList<Path> getFilesPath() {
        return filesPath;
    }
    public ArrayList<Path> getClassPath() { return classPath; }


    public void setCurrentPath(Path currentPath) {
        this.currentPath = currentPath;

    }

    public Path getCurrentPath() {
        return currentPath;
    }


    public void fillFilesPathList() {
        filesPath.clear();


    }

    public void fillClassPathList() {
        classPath.clear();

    }

}


