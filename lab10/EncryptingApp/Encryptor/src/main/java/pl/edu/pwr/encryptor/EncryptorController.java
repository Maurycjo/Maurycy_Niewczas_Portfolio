package pl.edu.pwr.encryptor;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class EncryptorController {

    private String dirName ="/home/mniewczas/Desktop/key_java";

    private Encryptor encryptor;
    private AesEncryptor aesEncryptor = new AesEncryptor();
    private RsaEncryptor rsaEncryptor = new RsaEncryptor();

    private byte [] fileKeyBytes;
    public String getDirName() {
        return dirName;
    }

    public void setDirName(String dirName) {
        this.dirName = dirName;
    }

    public void loadKeyFromFile(String fileName){

        FileInputStream keyFis = null;
        try {
            keyFis = new FileInputStream(dirName + "/" + fileName);
            fileKeyBytes = new byte[keyFis.available()];
            keyFis.read(fileKeyBytes);
            keyFis.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void encryptFile(EncryptorTypeEnum encryptorTypeEnum, String fileName){

        if(encryptorTypeEnum == EncryptorTypeEnum.AES){
            encryptor = aesEncryptor;
        } else if(encryptorTypeEnum == EncryptorTypeEnum.RSA){
            encryptor = rsaEncryptor;
        } else{
            return;
        }
        encryptor.encryptFile(dirName, fileName, fileKeyBytes);
    }

    public void decryptFile(EncryptorTypeEnum encryptorTypeEnum, String fileName){

        if(encryptorTypeEnum == EncryptorTypeEnum.AES){
            encryptor = aesEncryptor;
        } else if(encryptorTypeEnum == EncryptorTypeEnum.RSA){
            encryptor = rsaEncryptor;
        } else{
            return;
        }
        encryptor.decryptFile(dirName, fileName, fileKeyBytes);
    }


}
