package java;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class FileElement extends ElementInFileSystem {


    private String md5CheckSum;

    public FileElement(Path filePath){
        super(filePath);
        try {
            md5CheckSum=calculateMD5File();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public String getMd5CheckSum() {
        return md5CheckSum;
    }

    public String getFileNameWithInfo(){
        return "Plik | " + getFileName();
    }

    public void readFile(){

    }

    private String calculateMD5File() throws IOException, NoSuchAlgorithmException {
        //calculate md5 checksum for file
        MessageDigest mdigest = MessageDigest.getInstance("MD5");
        String checksum = checksum(mdigest, getFilePath());
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
