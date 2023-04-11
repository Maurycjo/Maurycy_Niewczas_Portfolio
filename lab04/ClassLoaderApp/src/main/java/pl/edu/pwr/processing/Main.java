package pl.edu.pwr.processing;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        //Path currentPath = Paths.get("/home/mniewczas/Desktop/java_lab/maunie814_javatz_2023/lab04/classToLoad/out/production/classToLoad/implementation");
        //Path currentPath = Paths.get("/home/mniewczas/Desktop/java_lab/maunie814_javatz_2023/lab04/classToLoad/src/implementation");


        Path currentPath = Paths.get("../classToLoad/out/production/classToLoad/");
        MyClassLoader classLoader = new MyClassLoader(currentPath);
        Class<?> loadedClass = classLoader.loadClass("implementation.TestClassToLoad");
        Object object = loadedClass.getDeclaredConstructor().newInstance();
        //Method submitTaskMethod = loadedClass.getMethod("submitTask", String.class, StatusListener.class);
        Method getInfoMethod = loadedClass.getMethod("getInfo");
        Method getResultMethod = loadedClass.getMethod("getResult");

        getInfoMethod.invoke(object);
        getResultMethod.invoke(object);

    }
}
