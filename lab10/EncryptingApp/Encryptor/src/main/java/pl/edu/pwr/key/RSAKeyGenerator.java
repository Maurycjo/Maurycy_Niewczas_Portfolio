package pl.edu.pwr.key;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.*;

public class RSAKeyGenerator extends MyKeyGenerator{
    @Override
    public void generateKey(int size, String keyPath, String keyFileName) {

        try {

            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(size);

            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            PrivateKey privateKey = keyPair.getPrivate();
            PublicKey publicKey = keyPair.getPublic();


            FileOutputStream privateFos = new FileOutputStream(keyPath +"/"+ keyFileName);
            privateFos.write(privateKey.getEncoded());
            privateFos.close();


            FileOutputStream publicFos = new FileOutputStream(keyPath +"/"+ keyFileName + ".pub");
            publicFos.write(publicKey.getEncoded());
            publicFos.close();

        } catch (NoSuchAlgorithmException | IOException e) {
            e.printStackTrace();
        }


    }
}
