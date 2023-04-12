import pl.edu.pwr.processing.Processor;
import pl.edu.pwr.processing.Status;
import pl.edu.pwr.processing.StatusListener;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;


public class Adder implements Processor {

    private static int taskId=0;
    private String result = null;

    public void setResult(String result){

        this.result=result;
    }

    private String add(String task){

        String[] numbers = task.split("\\+"); // dzielimy równanie na tablicę liczb
        int sum = 0;

        for (String number : numbers) {
            try {
                int val = Integer.parseInt(number.trim());
                sum += val; // konwertujemy każdą liczbę na int i sumujemy
            } catch (NumberFormatException e){
                return "Błąd";
            }
        }
        return String.valueOf(sum);
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

                    result = add(task);
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
        return "Dodawanie";
    }

    @Override
    public String getResult() {
        return result;
    }

}
