import java.util.concurrent.*;

class GasStation {
    private final Semaphore gasPumps;

    public GasStation(int pumpsCount) {
        this.gasPumps = new Semaphore(pumpsCount);
    }

    public void fuelCar(String car) {
        try {
            gasPumps.acquire(); 
            System.out.println("Car " + car + " is refueling at pump " + gasPumps.availablePermits());
            TimeUnit.SECONDS.sleep(2); 
            System.out.println("Car " + car + " finished refueling");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            gasPumps.release(); 
        }
    }
}

public class GasStationExample {
    public static void main(String[] args) throws InterruptedException {
        GasStation gasStation = new GasStation(4);

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.schedule(() -> System.exit(0), 5, TimeUnit.MINUTES); // Завершаем работу через 5 минут

        ExecutorService executor = Executors.newFixedThreadPool(10);

        for (int i = 1; i <= 10; i++) {
            int carNumber = i;
            executor.submit(() -> gasStation.fuelCar("Car" + carNumber));
        }

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.HOURS);
        scheduler.shutdown();
    }
}
