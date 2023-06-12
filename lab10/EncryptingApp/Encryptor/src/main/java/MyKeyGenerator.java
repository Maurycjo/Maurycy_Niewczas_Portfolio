import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;


public class MyKeyGenerator {

    public void generateAesKey() {


        String defaultFileName = "testKlucz.bin";
        KeyGenerator keyGenerator = null;
        try {
            keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(256);

            SecretKey secretKey = keyGenerator.generateKey();
            byte[] keyBytes = secretKey.getEncoded();

            FileOutputStream fileOutputStream = new FileOutputStream("/home/mniewczas/Desktop/key_java/"+defaultFileName);
            fileOutputStream.write(keyBytes);
            fileOutputStream.close();

            //System.out.println("Wygenerowano klucz AES " + keyBytes);

        } catch (NoSuchAlgorithmException | IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        MyKeyGenerator myKeyGenerator = new MyKeyGenerator();
        myKeyGenerator.generateAesKey();
    }

}
