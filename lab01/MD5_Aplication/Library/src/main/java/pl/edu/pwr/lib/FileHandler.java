package pl.edu.pwr.lib;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

public class FileHandler {

    private static String currentPath = "/home/mniewczas/Desktop/";


    public ArrayList<FileInfo> getFiles() {
        return files;
    }

    public String pathName(){
        return currentPath.toString();
    }

    private ArrayList<FileInfo> files = new ArrayList<>();


    public void fillFilesList(){

        try (Stream<Path> paths = Files.list(Paths.get(currentPath))) {
            paths.forEach(path -> files.add(new FileInfo(path.getFileName().toString())));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void calculateMD5(){

    }

    public void loadFilesFromCurrentPath(){

    }





}
