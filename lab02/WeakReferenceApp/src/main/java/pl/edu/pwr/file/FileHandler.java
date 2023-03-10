package pl.edu.pwr.file;

import org.apache.velocity.shaded.commons.io.FilenameUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.stream.Stream;

public class FileHandler {

    private static final String pathExpand = "/Desktop";
    private static String currentPath = System.getProperty("user.home") + pathExpand; //path that program start in
    private ArrayList<ElementInFileSystem> files = new ArrayList<>(); //FileInfo objects from path



    public ArrayList<ElementInFileSystem> getFiles() {
        return files;
    }
    public static void setCurrentPath(String currentPath) {
        FileHandler.currentPath = currentPath;
    }

    public static String getCurrentPath(){
        return currentPath;
    }

    public void parentPath(){
        //set paths 1 level up
        Path path = Paths.get(currentPath);

        if(path.getParent()!=null){
            currentPath = String.valueOf(path.getParent().toAbsolutePath());
        }
    }
    public void fillFilesList(){
        //get all files to FileInfo Array from current path
        files.clear();      //clear before filling


        try (Stream<Path> paths = Files.list(Paths.get(currentPath))) {
            paths.peek(path -> {
                        if (path.toString().endsWith(".csv")) {
                            files.add(new CsvFileElement(path.getFileName().toString()));
                        } else if (Files.isDirectory(path)) {
                            files.add(new DirElement(path.getFileName().toString()));
                        } else {
                            files.add(new OtherFileElement(path.getFileName().toString()));
                        }
                    })
                    .count(); // kończy strumień, ale nie wykonuje żadnych operacji
        } catch (Exception e) {
            e.printStackTrace();
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


