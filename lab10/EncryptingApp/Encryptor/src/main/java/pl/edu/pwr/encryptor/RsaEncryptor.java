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
import java.util.Arrays;

public class RsaEncryptor extends Encryptor {



    private int calculateSize(byte[] fileKeyBytes){

        int size = fileKeyBytes.length;
        return size - 50;
    }

    @Override
    public void encryptFile(String dirName, String fileName, byte[] fileKeyBytes) {

        FileInputStream pubKeyFis = null;
        try {

            FileOutputStream dataFos = new FileOutputStream(dirName +"/"+ fileName + "_encryptedRsa" );

            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(fileKeyBytes);
            PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);

            Cipher rsaCipher = Cipher.getInstance("RSA");
            rsaCipher.init(Cipher.ENCRYPT_MODE, publicKey);

            loadFile(dirName, fileName);

            int subArraySize = calculateSize(fileKeyBytes);

            byte[] subArray = new byte[subArraySize];

            int lastSize = fileDataBytes.length % subArraySize;
            byte[] lastSubArray = new byte[lastSize];

            for(int i=0; i<fileDataBytes.length; i+=subArraySize){


                if(i+subArraySize > fileDataBytes.length){

                    for(int k=0; k<lastSize; k++){
                        lastSubArray[k] = fileDataBytes[i+k];
                    }
                    byte[] encryptedData = rsaCipher.doFinal(lastSubArray);
                    dataFos.write(encryptedData);
                } else{

                    for(int j=i; j<i+subArraySize; j++){

                        subArray[j-i] = fileDataBytes[j];
                    }
                    byte[] encryptedData =  rsaCipher.doFinal(subArray);
                    dataFos.write(encryptedData);
                }
            }



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
