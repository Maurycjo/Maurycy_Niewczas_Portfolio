package pl.edu.pwr.lib;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

public class FileHandler {

    
    private static String currentPath = "/home/mniewczas/Desktop/";


    public void parentPath(){
        File file = new File(currentPath);
        if(file.getParentFile()!=null){
          currentPath = file.getAbsoluteFile().getParent();
        }
    }

    public ArrayList<FileInfo> getFiles() {
        return files;
    }

    public static void setCurrentPath(String currentPath) {
        FileHandler.currentPath = currentPath;
    }

    public static String getCurrentPath(){
        return currentPath;
    }

    private ArrayList<FileInfo> files = new ArrayList<>();


    public void fillFilesList(){

        files.clear();
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
