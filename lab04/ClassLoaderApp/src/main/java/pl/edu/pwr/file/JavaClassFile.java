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
    private MyStatusListener mst;

    public JavaClassFile(Path filePath) {
        super(filePath);
    }

    MyClassLoader classLoader = new MyClassLoader(getFilePath().getParent());
    Class <?> loadedClass;
    Object object;

    Method getResultMethod=null;
    Method submitTaskMethod=null;

    private String lastTask, lastResult, lastProgress, lastMethodInfo;

    public String getLastResult() {
        return lastResult;
    }

    public String getLastProgress() {
        return lastProgress;
    }

    public String getLastMethodInfo() {
        return lastMethodInfo;
    }

    public void setMst(MyStatusListener mst){
        this.mst=mst;
    }
    private String classState = unloadedStr; //Niezaładowana lub Załadowana
    private String methodState; //Nie znaleziono, 1-99, wykonano


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
            lastMethodInfo = (String) method.invoke(object);
            return lastMethodInfo;
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }

    }

    public String getResultMethod(){

        try {
            getResultMethod = loadedClass.getMethod("getResult");
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        try {
            lastResult = (String) getResultMethod.invoke(object);
            return lastResult;
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    public void submitTask(String task){
        lastTask = task;

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
