package pl.edu.pwr.lib;

//import java.io.File;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.SequenceInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.stream.Stream;

public class FileHandler {

    public FileHandler(){
        createMD5Directory();
    }

    private static String currentPath = "/home/mniewczas/Desktop";
    private static String md5Path = "/home/mniewczas/.md5";
    private ArrayList<FileInfo> files = new ArrayList<>();
    private String md5Hash;


    public void createMD5Directory() {

        Path tempPath = Paths.get(md5Path);
        if (!Files.exists(tempPath)) {

            try {
                Files.createDirectory(tempPath);
                Files.createDirectory(Path.of(tempPath + "/home"));
                Files.createDirectory(Path.of(tempPath+"/home/mniewczas"));
                Files.createDirectory(Path.of(tempPath+"/home/mniewczas/Desktop"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void deleteMD5Directory(){

        try {
            Files.deleteIfExists(Paths.get(md5Path));
            createMD5Directory();
            currentPath = "/home/mniewczas/Desktop/md5";
            md5Path = "/home/mniewczas/.md5";


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    public void parentPath(){
        Path path = Paths.get(currentPath);

        if(path.getParent()!=null){
          currentPath = String.valueOf(path.getParent().toAbsolutePath());
          md5Path = String.valueOf(Paths.get(md5Path).getParent().toAbsolutePath());
        }
    }

    public void childPath(String fileName){
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

        files.clear();
        try (Stream<Path> paths = Files.list(Paths.get(currentPath))) {
            paths.forEach(path -> files.add(new FileInfo(path.getFileName().toString())));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createNewFileInfoFile(){

        for (FileInfo file:files) {
            Path filePath = Paths.get(md5Path + "/" + file.getFileName());

            try{
                if(Files.exists(filePath)){

                    if(file.isDirectory()){
                        continue;
                    }
                    String oldMD5 = Files.readString(filePath);
                    String newMD5 = calculateMD5File(file.getFileName());

                    if(oldMD5.equals(newMD5)){
                        file.setFileState(FileInfo.FileStateEnum.UNCHANGED);
                        System.out.println("unchanged");
                    }
                    else{
                        file.setFileState(FileInfo.FileStateEnum.CHANGED);
                        Files.write(filePath, newMD5.getBytes());
                        System.out.println("changed");
                    }

                }
                else {

                    if(file.isDirectory()){
                        Files.createDirectory(filePath);
                    }
                    else {
                        Files.createFile(filePath);
                        Files.write(filePath, calculateMD5File(file.getFileName()).getBytes());
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

        Path filePath = Paths.get(currentPath + "/" + fileName);

        MessageDigest mdigest = MessageDigest.getInstance("MD5");

        String checksum = checksum(mdigest, filePath);
        return checksum;
    }



    private static String checksum(MessageDigest digest, Path filePath) throws IOException
    {
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


