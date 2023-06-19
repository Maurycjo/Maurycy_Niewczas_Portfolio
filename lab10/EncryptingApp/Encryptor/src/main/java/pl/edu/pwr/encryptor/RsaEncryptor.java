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



    private int calculatePublicKeySize(byte[] fileKeyBytes){

        int size = fileKeyBytes.length;
        if(size == 294) return 256;
        if(size == 162) return 128;;
        if(size == 94) return 64;

        return 0;
    }

    private int calculatePrivateKeySize(byte[] fileKeyBytes){

        int size = fileKeyBytes.length;

        if(size == 1217) return 245;
        if(size == 634)  return 117;
        if(size == 344) return 53;

        return size;
    }

    @Override
    public void encryptFile(String dirName, String fileName, byte[] fileKeyBytes, byte[] fileDataBytes) {

        FileInputStream pubKeyFis = null;
        try {

            FileOutputStream dataFos = new FileOutputStream(dirName +"/"+ fileName + "_encryptedRsa" );

            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PKCS8EncodedKeySpec privKeySpec = new PKCS8EncodedKeySpec(fileKeyBytes);
            PrivateKey privateKey = keyFactory.generatePrivate(privKeySpec);

            Cipher rsaCipher = Cipher.getInstance("RSA");
            rsaCipher.init(Cipher.ENCRYPT_MODE, privateKey);


            int subArraySize = calculatePrivateKeySize(fileKeyBytes);

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
    public void decryptFile(String dirName, String fileName, byte[] fileKeyBytes, byte[] fileDataBytes) {



        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(fileKeyBytes);
            PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);

            Cipher rsaCipher = Cipher.getInstance("RSA");
            rsaCipher.init(Cipher.DECRYPT_MODE, publicKey);



            int subArraySize = calculatePublicKeySize(fileKeyBytes);

            byte[] subArray = new byte[subArraySize];

            int lastSize = fileDataBytes.length % subArraySize;
            byte[] lastSubArray = new byte[lastSize];
            FileOutputStream dataFos = new FileOutputStream(dirName + "/" + fileName+"_decryptedRsa");

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





        } catch (IOException | NoSuchPaddingException | IllegalBlockSizeException | NoSuchAlgorithmException |
                 InvalidKeySpecException | BadPaddingException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }

}
