package pl.edu.pwr.lib;


import java.io.FileInputStream;
import java.io.IOException;

import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.stream.Stream;

public class FileHandler {

    public FileHandler(){
        createMD5Directory();
    } // creating .md5 dir in home path
    private static final String pathExpand = "/Desktop";
    private static String currentPath = System.getProperty("user.home") + pathExpand; //path that program start in
    private static String md5Path = System.getProperty("user.home") + "/.md5" + currentPath; //.md5 path
    private ArrayList<FileInfo> files = new ArrayList<>(); //FileInfo objects from path
    private ArrayList<FileInfo> filesBackup = new ArrayList<>(); //FileInfo object for checking deletion

    public void createMD5Directory() {
        //create .md5 path in home directory

        Path tempPath = Paths.get(System.getProperty("user.home")+"/.md5");
        if (!Files.exists(tempPath)) {

            try {
                Files.createDirectory(tempPath);
                Files.createDirectory(Path.of(tempPath + "/home"));
                Files.createDirectory(Path.of(tempPath + System.getProperty("user.home")));
                Files.createDirectory(Path.of(tempPath + System.getProperty("user.home") + "/Desktop"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void deleteMD5Directory(){
    //delete .md5 file with all files in

     Path delPath = Paths.get(System.getProperty("user.home") + "/.md5");

        try {
            Files.walkFileTree(delPath, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    Files.delete(file);
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    Files.delete(dir);
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            Files.deleteIfExists(delPath);
            createMD5Directory();
            currentPath = System.getProperty("user.home") + pathExpand; //set current path for Desktop
            md5Path = System.getProperty("user.home") + "/.md5" + currentPath;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }



    }

    public void parentPath(){
        //set paths 1 level up
        Path path = Paths.get(currentPath);

        if(path.getParent()!=null){
          currentPath = String.valueOf(path.getParent().toAbsolutePath());
          md5Path = String.valueOf(Paths.get(md5Path).getParent().toAbsolutePath());
        }
    }

    public void childPath(String fileName){
        //set path 1 level down
        Path path = Paths.get(currentPath + "/" + fileName);
        if(Files.isDirectory(path)){
            currentPath+="/"+fileName;
            md5Path+="/"+fileName;
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
        //get all files to FileInfo Array from current path
        files.clear();      //clear before filling
        filesBackup.clear();

        //get from current path
        try (Stream<Path> paths = Files.list(Paths.get(currentPath))) {
            paths.forEach(path -> files.add(new FileInfo(path.getFileName().toString())));
        } catch (Exception e) {
            e.printStackTrace();
        }

        //get from .md5 path for checking for files that doesn't exist in current path
        try (Stream<Path> paths = Files.list(Paths.get(md5Path))) {
            paths.forEach(path -> filesBackup.add(new FileInfo(path.getFileName().toString())));
        } catch (Exception e) {
            e.printStackTrace();
        }

        //checking if files from .md5 doesn't exist in current path
        for (FileInfo file: filesBackup) {

            Path path = Paths.get(currentPath + "/" + file.getFileName());
            if(!(Files.exists(path))){
                //if it doesn't exist set state deleted
                file.setFileState(FileInfo.FileStateEnum.DELETED);
                files.add(file);
                try {
                    //delete from .md5
                    Files.delete(Paths.get(md5Path+ "/" + file.getFileName()));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            }

    }

    public void createNewFileInfoFile(){
        //checking and writing md5 hashes for text files in .md5 path
        for (FileInfo file:files) {
            Path filePath = Paths.get(md5Path + "/" + file.getFileName());

            try{
                //check if file exist
                if(Files.exists(filePath)){

                    if(file.isDirectory()){
                        //if file exist and file is dir change state to old
                        if(file.getFileState()== FileInfo.FileStateEnum.NEW){
                            file.setFileState(FileInfo.FileStateEnum.OLD);
                        }
                        continue;

                    }
                    //get old and new md5 hash
                    String oldMD5 = Files.readString(filePath);
                    String newMD5 = calculateMD5File(file.getFileName());

                    //check if they are the same
                    if(oldMD5.equals(newMD5)){
                        file.setFileState(FileInfo.FileStateEnum.UNCHANGED);
                    }
                    else{
                        file.setFileState(FileInfo.FileStateEnum.CHANGED);
                        Files.write(filePath, newMD5.getBytes());
                    }

                }
                else { //if file doesn't exist

                    if(file.isDirectory()){
                        //if file is directory create directory in .md5 path for subdirectory structure
                        Files.createDirectory(filePath);
                    }
                    else {
                        if(file.getFileState()== FileInfo.FileStateEnum.DELETED){

                        }
                        else {
                            //if file doesn't delete
                            //create file in .md5 path for hash
                            Files.createFile(filePath);
                            Files.write(filePath, calculateMD5File(file.getFileName()).getBytes());
                        }
                    }
                }

            }catch (IOException e){
                System.err.println("Nie można utworzyć pliku " + e.getMessage());
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private String calculateMD5File(String fileName) throws IOException, NoSuchAlgorithmException {
        //calculate md5 checksum for file
        Path filePath = Paths.get(currentPath + "/" + fileName);
        MessageDigest mdigest = MessageDigest.getInstance("MD5");
        String checksum = checksum(mdigest, filePath);
        return checksum;
    }



    private static String checksum(MessageDigest digest, Path filePath) throws IOException
    {
        //algorithm that realize md5 checksum from file
        FileInputStream fis = new FileInputStream(filePath.toFile());

        byte[] byteArray = new byte[1024];
        int bytesCount = 0;

        // read the data from file and update that data in
        // the message digest
        while ((bytesCount = fis.read(byteArray)) != -1)
        {
            digest.update(byteArray, 0, bytesCount);
        };

        // close the input stream
        fis.close();
        // store the bytes returned by the digest() method
        byte[] bytes = digest.digest();

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < bytes.length; i++) {

            // the following line converts the decimal into
            // hexadecimal format and appends that to the
            // StringBuilder object
            sb.append(Integer
                    .toString((bytes[i] & 0xff) + 0x100, 16)
                    .substring(1));
        }

        // finally we return the complete hash
        return sb.toString();
    }
}


