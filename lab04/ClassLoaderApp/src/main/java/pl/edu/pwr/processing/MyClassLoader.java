package pl.edu.pwr.processing;

import java.io.*;
import java.nio.file.*;

public class MyClassLoader extends ClassLoader {

    private Path classPath;

    public MyClassLoader(Path classPath) {
        this.classPath = classPath;
    }


    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {


        try {
            byte[] data = getClassData(name);
            return defineClass(name, data, 0, data.length);
        } catch (IOException ex) {
            throw new ClassNotFoundException("Class " + name + " not found", ex);
        }
    }

    private byte[] getClassData(String className) throws IOException {
        Path path = classPath.resolve(className.replace('.', '/') + ".class");
        try (InputStream inputStream = Files.newInputStream(path)) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            int bufferSize = 4096;
            byte[] buffer = new byte[bufferSize];
            int bytesNumRead;
            while ((bytesNumRead = inputStream.read(buffer)) != -1) {
                byteArrayOutputStream.write(buffer, 0, bytesNumRead);
            }
            return byteArrayOutputStream.toByteArray();
        }
    }


}


