package pl.edu.pwr.key;

import java.io.File;

public class KeyGeneratorController {


    private String keyPath = "/home/mniewczas/Desktop/key_java";

    MyKeyGenerator myKeyGenerator;
    AESKeyGenerator aesKeyGenerator = new AESKeyGenerator();
    RSAKeyGenerator rsaKeyGenerator = new RSAKeyGenerator();

    public void generateKey(KeyTypeEnum keyTypeEnum, int size, String keyFileName){

        if(keyTypeEnum == KeyTypeEnum.AES){
            myKeyGenerator = aesKeyGenerator;
        } else if(keyTypeEnum == KeyTypeEnum.RSA){
            myKeyGenerator = rsaKeyGenerator;
        } else {
            return;
        }
        myKeyGenerator.generateKey(size, keyPath, keyFileName);
    }

    public String getKeyPath() {
        return keyPath;
    }

    public void setKeyPath(String keyPath) {
        this.keyPath = keyPath;
    }



}
