package pl.edu.pwr.encryptor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

abstract public class Encryptor {




    protected FileInputStream fileInputStream;
    protected File fileEncryptDecrypt;
    protected byte[] fileDataBytes;
    abstract public void encryptFile(String dirName, String fileName, byte[] fileKeyBytes);
    abstract public void decryptFile(String dirName, String fileName, byte[] fileKeyBytes);


    public void loadFile(String dirName, String fileName) {

        try {
            fileInputStream = new FileInputStream(dirName + "/" + fileName);
            fileEncryptDecrypt = new File(dirName + "/" + fileName);
            long fileSize = fileEncryptDecrypt.length();
            fileDataBytes = new byte[(int) fileSize];
            fileInputStream.read(fileDataBytes);
            fileInputStream.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }






}
