I used jsigner and keytool to sign my application jar an library jar. There are executable jars in application directory.

There are commands I using:

1. Generate key
keytool -genkey -alias server -keyalg RSA -keypass password -storepass password -keystore keystore.jks
2. Export
keytool -export -alias server -storepass password -file server.cer -keystore keystore.jks
3. Sign jar
jarsigner -keystore keystore.jks -signedjar signedEncryptor.jar Encryptor-1.0-SNAPSHOT.jar server
4. Verify jar
jarsigner -verify signedjar.jar

The application has the ability to generate rsa public key, rsa private key. There are created at once togheter. Application can also create AES key. With these keys the application can encrytp and decrypt files. The encrypted will have names following specific pattern: the base filename + "_encrypted" + algorithm used. For example, if the original file is file.txt and RSA encryption is used, the encrypted file will be named file.txt_encryptedRsa. A similar pattern applies to decrypting the files.
View of generate key Tab
![genKey](https://github.com/Maurycjo/Maurycy_Niewczas_Portfolio/assets/59066809/798fc3bb-9927-46ed-bb6e-05407c49607a)

Chose name, algorithm and size then click generate button

View of encrypting Tab with encrypted file

![encrypted](https://github.com/Maurycjo/Maurycy_Niewczas_Portfolio/assets/59066809/483c35fb-665b-442e-b6d4-76b9173185dd)
In this window, you can double-click to display the content of a file. You have the option to choose an algorithm for encryption or decryption. To load a file and key, click on the file first and then click the specified button.
