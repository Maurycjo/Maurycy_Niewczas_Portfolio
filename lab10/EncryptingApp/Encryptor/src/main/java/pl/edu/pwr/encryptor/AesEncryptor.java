package pl.edu.pwr.encryptor;


import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;


public class AesEncryptor extends Encryptor{

    private static final int BUFFER_SIZE = 256;

    private byte[] generateInitializationVector(){
        return new byte[16];
    }
    @Override
    public void encryptFile(String dirName, String fileName, byte[] fileKeyBytes, byte[] fileDataBytes) {

        try {

            SecretKeySpec secretKeySpec = new SecretKeySpec(fileKeyBytes, "AES");
            byte[] initializeVector = generateInitializationVector();

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec ivParameterSpec = new IvParameterSpec(initializeVector);
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);

            //szyfrowania


            InputStream inputStream = new FileInputStream(dirName + "/" + fileName);
            OutputStream outputStream = new FileOutputStream(dirName +"/"+fileName+"_encryptedAes");
            byte[] buffer = new byte[BUFFER_SIZE];
            int bytesRead;

            while((bytesRead = inputStream.read(buffer)) != -1){
                byte[] encryptedBytes = cipher.update(buffer, 0, bytesRead);
                outputStream.write(encryptedBytes);
            }
            byte[] encryptedBytes = cipher.doFinal();
            outputStream.write(encryptedBytes);

            outputStream.close();
            inputStream.close();

        } catch (IOException | NoSuchPaddingException | NoSuchAlgorithmException | InvalidAlgorithmParameterException |
                 InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void decryptFile(String dirName, String fileName, byte[] fileKeyBytes, byte[] fileDataBytes) {

        try {

            SecretKeySpec secretKeySpec = new SecretKeySpec(fileKeyBytes, "AES");
            byte[] initializeVector = generateInitializationVector();

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec ivParameterSpec = new IvParameterSpec(initializeVector);
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);


            //deszyfrowanie
            InputStream inputStream = new FileInputStream(dirName + "/" + fileName);
            OutputStream outputStream = new FileOutputStream(dirName +"/"+ fileName+"_decrypted");

            byte[] buffer = new byte[BUFFER_SIZE];
            int bytesRead;
            while((bytesRead = inputStream.read(buffer)) != -1){
                byte[] decryptedBytes = cipher.update(buffer, 0, bytesRead);
                outputStream.write(decryptedBytes);
            }
            byte[] decryptedBytes = cipher.doFinal();
            outputStream.write(decryptedBytes);

        } catch (IOException | NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException |
                 InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
            throw new RuntimeException(e);
        }

    }
}
