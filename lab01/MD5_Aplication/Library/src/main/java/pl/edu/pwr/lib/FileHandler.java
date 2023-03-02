package pl.edu.pwr.lib;

import java.util.ArrayList;

public class FileHandler {

    private String currentPath ="/home/mniewczas/Desktop/md5";

    public ArrayList<FileInfo> getFiles() {
        return files;
    }

    private ArrayList<FileInfo> files = new ArrayList<>();

    public void fillFilesList(){

        // fill list for tests
        for(int i=0;i<3;i++){
            files.add(new FileInfo("file " + Integer.toString(i)));
        }

    }
    public void calculateMD5(){

    }

}
