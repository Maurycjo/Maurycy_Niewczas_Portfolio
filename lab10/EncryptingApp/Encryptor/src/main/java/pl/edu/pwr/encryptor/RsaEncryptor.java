package pl.edu.pwr.encryptor;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.*;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class RsaEncryptor extends Encryptor {

    private File pubRsaKey = new File("/home/mniewczas/Desktop/key_java/rsaTest.pub");
    private File privRsaKey = new File("/home/mniewczas/Desktop/key_java/rsaTest");

    @Override
    public void encryptFile() {

        FileInputStream pubKeyFis = null;
        try {
            //wczytanie klucza rsa
            pubKeyFis = new FileInputStream(pubRsaKey);
            byte[] pubKeyBytes = new byte[pubKeyFis.available()];
            pubKeyFis.read(pubKeyBytes);
            pubKeyFis.close();


            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(pubKeyBytes);
            PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);

            Cipher rsaCipher = Cipher.getInstance("RSA");
            rsaCipher.init(Cipher.ENCRYPT_MODE, publicKey);

            byte[] encryptedData = rsaCipher.doFinal(inputData);

            FileOutputStream dataFos = new FileOutputStream("/home/mniewczas/Desktop/key_java/encryptedRsa");
            dataFos.write(encryptedData);
            dataFos.close();

        } catch (IOException | NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException |
                 InvalidKeyException e) {
            throw new RuntimeException(e);
        } catch (IllegalBlockSizeException e) {
            throw new RuntimeException(e);
        } catch (BadPaddingException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public void decryptFile() {

        File fileToDecrypt = new File("/home/mniewczas/Desktop/key_java/encryptedRsa");
        try {
            FileInputStream fis = new FileInputStream(fileToDecrypt);
            byte[] encryptedData = new byte[(int) fileToDecrypt.length()];
            fis.read(encryptedData);
            fis.close();

            FileInputStream privKeyFis = new FileInputStream("/home/mniewczas/Desktop/key_java/rsaTest");
            byte[] privKeyBytes = new byte[privKeyFis.available()];
            privKeyFis.read(privKeyBytes);
            privKeyFis.close();

            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(privKeyBytes);
            PrivateKey privateKey = keyFactory.generatePrivate(privateKeySpec);

            // Inicjalizacja Cipher z algorytmem RSA
            Cipher rsaCipher = Cipher.getInstance("RSA");
            rsaCipher.init(Cipher.DECRYPT_MODE, privateKey);

            // Odszyfrowanie danych pliku przy użyciu klucza prywatnego RSA
            byte[] decryptedData = rsaCipher.doFinal(encryptedData);

            // Zapis odszyfrowanych danych do pliku
            FileOutputStream dataFos = new FileOutputStream("/home/mniewczas/Desktop/key_java/decryptedRsa");
            dataFos.write(decryptedData);
            dataFos.close();



        } catch (IOException | NoSuchPaddingException | IllegalBlockSizeException | NoSuchAlgorithmException |
                 InvalidKeySpecException | BadPaddingException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {

        RsaEncryptor rsaEncryptor = new RsaEncryptor();
        rsaEncryptor.loadFileToEncrypt();
        rsaEncryptor.encryptFile();
        rsaEncryptor.decryptFile();

    }

}
