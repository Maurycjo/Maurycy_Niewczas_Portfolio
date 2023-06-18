package pl.edu.pwr.encryptor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

abstract public class Encryptor {




    protected FileInputStream fileInputStream;
    protected File fileEncryptDecrypt;
    protected byte[] fileDataBytes;
    abstract public void encryptFile(String dirName, String fileName, byte[] fileKeyBytes, byte[] fileDataBytes);
    abstract public void decryptFile(String dirName, String fileName, byte[] fileKeyBytes, byte[] fileDataBytes);









}
