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



    @Override
    public void encryptFile(String dirName, String fileName, byte[] fileKeyBytes) {

        FileInputStream pubKeyFis = null;
        try {


            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(fileKeyBytes);
            PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);

            Cipher rsaCipher = Cipher.getInstance("RSA");
            rsaCipher.init(Cipher.ENCRYPT_MODE, publicKey);

            loadFile(dirName, fileName);

            byte[] encryptedData = rsaCipher.doFinal(fileDataBytes);

            FileOutputStream dataFos = new FileOutputStream(dirName +"/"+ fileName + "_encryptedRsa" );
            dataFos.write(encryptedData);
            dataFos.close();

        } catch (IOException | NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException |
                 InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void decryptFile(String dirName, String fileName, byte[] fileKeyBytes) {

        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(fileKeyBytes);
            PrivateKey privateKey = keyFactory.generatePrivate(privateKeySpec);

            // Inicjalizacja Cipher z algorytmem RSA
            Cipher rsaCipher = Cipher.getInstance("RSA");
            rsaCipher.init(Cipher.DECRYPT_MODE, privateKey);

            loadFile(dirName, fileName);
            // Odszyfrowanie danych pliku przy użyciu klucza prywatnego RSA
            byte[] decryptedData = rsaCipher.doFinal(fileDataBytes);

            // Zapis odszyfrowanych danych do pliku
            FileOutputStream dataFos = new FileOutputStream(dirName + "/" + fileName+"_decryptedRsa");
            dataFos.write(decryptedData);
            dataFos.close();



        } catch (IOException | NoSuchPaddingException | IllegalBlockSizeException | NoSuchAlgorithmException |
                 InvalidKeySpecException | BadPaddingException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }

}
