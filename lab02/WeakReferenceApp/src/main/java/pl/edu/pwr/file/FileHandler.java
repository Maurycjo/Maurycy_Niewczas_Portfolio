package pl.edu.pwr.file;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.stream.Stream;

public class FileHandler {

    private static final String pathExpand = "/Desktop";
    private Path currentPath = Paths.get(System.getProperty("user.home") + pathExpand); //path that program start in
    private ArrayList<ElementInFileSystem> files = new ArrayList<>(); //FileInfo objects from path



    public ArrayList<ElementInFileSystem> getFiles() {
        return files;
    }
    public void setCurrentPath(Path currentPath) {
    this.currentPath = currentPath;

    }

    public Path getCurrentPath(){
        return currentPath;
    }

    public void parentPath(){
        //set paths 1 level up

        if(currentPath.getParent()!=null){
            currentPath = (currentPath.getParent().toAbsolutePath());
        }
    }

    public void fillFilesList(){
        //get all files to FileInfo Array from current path
        files.clear();      //clear before filling

        try (Stream<Path> paths = Files.list(currentPath)) {
            paths.peek(path -> {
                        if (path.toString().endsWith(".csv")) {
                            files.add(new CsvFileElement(path.toAbsolutePath().toString()));
                        } else if (Files.isDirectory(path)) {
                            files.add(new DirElement(path.toAbsolutePath().toString()));
                        } else {
                            files.add(new FileElement(path.toAbsolutePath().toString()));
                        }
                    })
                    .count(); // kończy strumień, ale nie wykonuje żadnych operacji
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




}


