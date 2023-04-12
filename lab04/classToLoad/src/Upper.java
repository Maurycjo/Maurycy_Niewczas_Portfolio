import pl.edu.pwr.processing.Processor;
import pl.edu.pwr.processing.Status;
import pl.edu.pwr.processing.StatusListener;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * Klasa procesora symuluj¹ca d³ugotrwa³e przetwarzanie
 * @author tkubik
 *
 */

public class Upper implements Processor {

    private static int taskId=0;
    private String result = null;

    public void setResult(String result){

        this.result=result;
    }
    @Override
    public boolean submitTask(String task, StatusListener sl) {
        taskId++;
        AtomicInteger ai = new AtomicInteger(0);

        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();


        executorService.scheduleAtFixedRate(
                ()->{
                    ai.incrementAndGet();
                    sl.statusChanged(new Status(taskId,ai.get()));
                },
                1, 10, TimeUnit.MILLISECONDS);


        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {
            while (true) {
                //System.out.println(scheduleFuture.isDone()); will always print false
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                if (ai.get() >= 100) {

                    //zmiana literek na duze
                    result = task.toUpperCase();
                    setResult(result);
                    //scheduleFuture.cancel(true);
                    executorService.shutdown();
                    executor.shutdown();
                    return true;

                }
            }
        });

        return false;
    }

    @Override
    public String getInfo() {
        return "Zamiana literek na duze";
    }

    @Override
    public String getResult() {
        return result;
    }

}
