package pl.edu.pwr.file;

import java.nio.file.*;
import java.util.ArrayList;
import java.util.stream.Stream;

public class FileHandler {


    private Path currentPath = Paths.get("../classToLoad/out/production/classToLoad");
    private ArrayList<ElementInFileSystem> files = new ArrayList<>(); //FileInfo objects from path
    private ArrayList<Path> filesPath = new ArrayList<>();
    private ArrayList<Path> classPath = new ArrayList<>();

    public ArrayList<ElementInFileSystem> getFiles() {
        return files;
    }

    public ArrayList<Path> getFilesPath() {
        return filesPath;
    }
    public ArrayList<Path> getClassPath() { return classPath; }

    public void clearFiles() {
        files.clear();
    }

    public void setCurrentPath(Path currentPath) {
        this.currentPath = currentPath;

    }

    public Path getCurrentPath() {
        return currentPath;
    }

    public void parentPath() {
        //set paths 1 level up

        if (currentPath.getParent() != null) {
            currentPath = (currentPath.getParent().toAbsolutePath());
        }
    }

    public void fillFilesPathList() {
        filesPath.clear();

        try (Stream<Path> paths = Files.list(currentPath)) {
            paths.peek(path -> {
                        filesPath.add(path);
                    })
                    .count(); // kończy strumień, ale nie wykonuje żadnych operacji
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void fillClassPathList() {
        classPath.clear();

        try (Stream<Path> paths = Files.list(currentPath)) {
            paths.peek(path -> {
                if(path.toString().endsWith(".class"))
                        classPath.add(path);
                    })
                    .count(); // kończy strumień, ale nie wykonuje żadnych operacji
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




}


