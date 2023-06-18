package pl.edu.pwr.window;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class FileWalker {

    private Path currentPath = Path.of("/home/mniewczas/Desktop/key_java/");

    private List<Path> currentPathsArrayList = new ArrayList<>();

    public void parentPath(){

        if(currentPath.getParent() != null){
            currentPath = (currentPath.getParent().toAbsolutePath());
        }
    }

    public void fillPathList(){

        currentPathsArrayList.clear();
        try (Stream<Path> paths = Files.list(currentPath)) {
            paths.peek(path -> {
                        currentPathsArrayList.add(path);
                    })
                    .count(); // kończy strumień, ale nie wykonuje żadnych operacji
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getOutputInList(Path path){
        if(Files.isDirectory(path)) {
            return "Dir  |" + path.getFileName();
        } else{
            return "File |" + path.getFileName();
        }
    }

    public void clickOnElement(int idx){

             currentPath = currentPathsArrayList.get(idx);
             fillPathList();
    }

    public boolean checkIfDirectory(int idx){
        return Files.isDirectory(currentPathsArrayList.get(idx));
    }

    public Path getCurrentPath() {
        return currentPath;
    }

    public void setCurrentPath(Path currentPath) {
        this.currentPath = currentPath;
    }

    public List<Path> getCurrentPathsArrayList() {
        return currentPathsArrayList;
    }

    public void setCurrentPathsArrayList(List<Path> currentPathsArrayList) {
        this.currentPathsArrayList = currentPathsArrayList;
    }

    public String getFileDisplay(int idx){

        Path filePath = currentPathsArrayList.get(idx);
        String fileContent = "";

        try {
            byte[] bytes = Files.readAllBytes(filePath);
            fileContent = new String (bytes);
        } catch (IOException e) {
            //handle exception
        }

        return fileContent;
    }


}
