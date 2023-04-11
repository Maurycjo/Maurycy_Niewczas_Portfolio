package pl.edu.pwr.file;

import pl.edu.pwr.processing.MyClassLoader;
import pl.edu.pwr.processing.MyStatusListener;
import pl.edu.pwr.processing.StatusListener;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Path;
import java.util.ArrayList;

public class JavaClassFile extends FileElement{

    private final String unloadedStr = "Niezaładowana";
    private final String loadedStr = "Załadowana";

    private String lastTask ="";

    public StatusListener mst = new MyStatusListener();

    public JavaClassFile(Path filePath) {
        super(filePath);
    }

    MyClassLoader classLoader = new MyClassLoader(getFilePath().getParent());
    Class <?> loadedClass;
    Object object;


    private String classState = unloadedStr; //Niezaładowana lub Załadowana
    private String methodState; //Nie znaleziono, 1-99, wykonano

    public void changeMethodState(int progress){
        if(progress==100){
            methodState="Wykonano";
        }else {
            methodState = String.valueOf(progress);
        }

    }

    public String getLastTask() {
        return lastTask;
    }

    public String getMethodState() {
        return methodState;
    }

    public String getClassState(){
        return classState;
    }

    public void loadClass(){



        try {
            loadedClass = classLoader.loadClass(getFileNameWithoutExtension());
            object = loadedClass.getDeclaredConstructor().newInstance();
        } catch (ClassNotFoundException | InvocationTargetException | InstantiationException | IllegalAccessException |
                 NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

        classState = loadedStr;
    }

    public String getInfoMethod(){

        Method method = null;
        try {
            method = loadedClass.getMethod("getInfo");
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

        try {
            return (String) method.invoke(object);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }

    }

    public String getResultMethod(){

        Method method = null;
        try {
            method = loadedClass.getMethod("getResult");
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        try {
            return (String) method.invoke(object);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    public void submitTask(String task){
        lastTask = task;
        Method submitTaskMethod = null;

        try {

            submitTaskMethod = loadedClass.getMethod("submitTask", String.class, StatusListener.class);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

        try {
            submitTaskMethod.invoke(object, task, mst);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

}
