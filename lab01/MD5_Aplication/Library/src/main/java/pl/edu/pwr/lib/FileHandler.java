package pl.edu.pwr.lib;

//import java.io.File;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.stream.Stream;

public class FileHandler {

    
    private static String currentPath = "/home/mniewczas/Desktop";
    private static final String md5Path = "/home/mniewczas/.md5";
    private ArrayList<FileInfo> files = new ArrayList<>();
    private String md5Hash;

    public void parentPath(){
        Path path = Paths.get(currentPath);
        if(path.getParent()!=null){
          currentPath = String.valueOf(path.getParent().toAbsolutePath());
        }
    }

    public void childPath(String fileName){
        Path path = Paths.get(currentPath + "/" + fileName);
        if(Files.isDirectory(path)){
            currentPath+="/"+fileName;
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


    public void fillFilesList(){

        files.clear();
        try (Stream<Path> paths = Files.list(Paths.get(currentPath))) {
            paths.forEach(path -> files.add(new FileInfo(path.getFileName().toString())));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createNewFileInfoFile(){


        Path filePath = Paths.get(md5Path + "/" + files.get(0).getFileName());
        try{
            Files.createFile(filePath);
            Files.write(filePath, calculateMD5(files.get(0).getFileName()).getBytes());


        }catch (IOException e){
            System.err.println("Nie można utworzyć pliku " + e.getMessage());
        }
    }



    private String calculateMD5(String fileName) throws IOException {

        Path filePath = Paths.get(currentPath + "/" + fileName);

        String checksum = null;
        try (InputStream is = Files.newInputStream(filePath)) {
            checksum = DigestUtils.md5Hex(is.toString());
            System.out.println("Wartość skrótu MD5: " + checksum);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return checksum;
    }







}
