package pl.edu.pwr.encryptor;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

abstract public class Encryptor {


    protected String fileToEncryptDecryptDir ="/home/mniewczas/Desktop/key_java/";
    protected File fileToEncryptDecrypt = new File(fileToEncryptDecryptDir + "test.txt");
    protected FileInputStream fileInputStream;
    protected byte[] inputData;
    abstract public void encryptFile();
    abstract public void decryptFile();

    public void loadFileToEncrypt() {
        try {
            fileInputStream = new FileInputStream(fileToEncryptDecrypt);
            inputData = new byte[(int) fileToEncryptDecrypt.length()];
            fileInputStream.read(inputData);
            fileInputStream.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }





}
