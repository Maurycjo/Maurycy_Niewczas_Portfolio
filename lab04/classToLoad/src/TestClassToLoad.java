import processing.Processor;
import processing.StatusListener;

public class TestClassToLoad implements Processor {
    @Override
    public boolean submitTask(String task, StatusListener sl) {
        System.out.println("submit task");
        return false;
    }

    @Override
    public String getInfo() {
        System.out.println("get info about class");
        return null;
    }

    @Override
    public String getResult() {
        System.out.println("get result");
        return null;
    }
}
