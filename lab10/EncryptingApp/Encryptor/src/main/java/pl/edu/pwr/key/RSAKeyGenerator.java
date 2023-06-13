package pl.edu.pwr.key;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.*;

public class RSAKeyGenerator extends MyKeyGenerator{
    @Override
    public void generateKey(int size, String keyPath, String keyFileName) {

        try {
            // Inicjalizacja KeyPairGenerator dla RSA
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(size);

            // Generowanie pary kluczy
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            PrivateKey privateKey = keyPair.getPrivate();
            PublicKey publicKey = keyPair.getPublic();

            // Zapis klucza prywatnego do pliku
            FileOutputStream privateFos = new FileOutputStream(keyPath + keyFileName);
            privateFos.write(privateKey.getEncoded());
            privateFos.close();

            // Zapis klucza publicznego do pliku
            FileOutputStream publicFos = new FileOutputStream(keyPath + keyFileName + ".pub");
            publicFos.write(publicKey.getEncoded());
            publicFos.close();

        } catch (NoSuchAlgorithmException | IOException e) {
            e.printStackTrace();
        }


    }
}
