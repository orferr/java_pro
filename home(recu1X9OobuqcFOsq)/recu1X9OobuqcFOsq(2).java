import java.util.concurrent.*;

class StringExchange {
    private final Exchanger<String> exchanger = new Exchanger<>();

    public void performExchange(String message) {
        try {
            String beforeExchange = "Thread " + Thread.currentThread().getId() + " before exchange: " + message;
            System.out.println(beforeExchange);

            String exchangedMessage = exchanger.exchange(message);

            String afterExchange = "Thread " + Thread.currentThread().getId() + " after exchange: " + exchangedMessage;
            System.out.println(afterExchange);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class StringExchangeExample {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        StringExchange stringExchange = new StringExchange();

        for (int i = 1; i <= 10; i++) {
            final int threadNumber = i;
            executor.submit(() -> stringExchange.performExchange("Message from Thread " + threadNumber));
        }

        executor.shutdown();
    }
}
