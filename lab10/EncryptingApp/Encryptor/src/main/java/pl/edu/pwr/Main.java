package pl.edu.pwr;

import pl.edu.pwr.encryptor.EncryptorController;
import pl.edu.pwr.encryptor.EncryptorTypeEnum;
import pl.edu.pwr.key.KeyGeneratorController;
import pl.edu.pwr.key.KeyTypeEnum;

public class Main {


    public static void main(String[] args) {

        EncryptorController encryptorController = new EncryptorController();
        KeyGeneratorController keyGeneratorController = new KeyGeneratorController();
//
        keyGeneratorController.generateKey(KeyTypeEnum.RSA, 2048, "rsa_key");
        keyGeneratorController.generateKey(KeyTypeEnum.AES, 256, "aes_key");

        encryptorController.loadKeyFromFile("aes_key");
        encryptorController.encryptFile(EncryptorTypeEnum.AES, "test.txt");
        encryptorController.loadKeyFromFile("aes_key");
        encryptorController.decryptFile(EncryptorTypeEnum.AES, "test.txt_encryptedAes");

//        encryptorController.loadKeyFromFile("rsa_key.pub");
//        encryptorController.encryptFile(EncryptorTypeEnum.RSA, "test.txt");
//        encryptorController.loadKeyFromFile("rsa_key");
//        encryptorController.decryptFile(EncryptorTypeEnum.RSA, "test.txt_encryptedRsa");

    }
}
