1. Generate key
keytool -genkey -alias server -keyalg RSA -keypass password -storepass password -keystore keystore.jks
2. Export
keytool -export -alias server -storepass password -file server.cer -keystore keystore.jks
3. Sign jar
jarsigner -keystore keystore.jks -signedjar signedEncryptor.jar Encryptor-1.0-SNAPSHOT.jar server
4. Verify jar
jarsigner -verify signedjar.jar

