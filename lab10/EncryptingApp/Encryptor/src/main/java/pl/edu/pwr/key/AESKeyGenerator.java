package pl.edu.pwr.key;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;


public class AESKeyGenerator extends MyKeyGenerator{



    public void generateKey(int size, String keyPath, String keyFileName) {

        KeyGenerator keyGenerator = null;
        try {
            keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(size);

            SecretKey secretKey = keyGenerator.generateKey();
            byte[] keyBytes = secretKey.getEncoded();

            FileOutputStream fileOutputStream = new FileOutputStream(keyPath +"/"+ keyFileName);
            fileOutputStream.write(keyBytes);
            fileOutputStream.close();


        } catch (NoSuchAlgorithmException | IOException e){
            e.printStackTrace();
        }
    }
}
