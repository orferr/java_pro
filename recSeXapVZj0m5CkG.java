import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

class Warehouse {
    private final List<Integer> goods;
    private final int capacity;
    private final AtomicInteger currentIndex;

    public Warehouse(int capacity) {
        this.capacity = capacity;
        this.goods = new ArrayList<>(capacity);
        this.currentIndex = new AtomicInteger(0);
        loadGoods();
    }

    private void loadGoods() {
        for (int i = 1; i <= capacity; i++) {
            goods.add(i);
        }
    }

    public synchronized int getNextIndex() {
        return currentIndex.getAndIncrement();
    }

    public synchronized int getGoods(int index) {
        if (index < capacity) {
            return goods.get(index);
        } else {
            return -1; 
        }
    }

    public synchronized void clearGoods() {
        goods.clear();
    }
}

class Loader implements Runnable {
    private final Warehouse warehouse;

    public Loader(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    @Override
    public void run() {
        int index;
        while ((index = warehouse.getNextIndex()) < warehouse.capacity) {
            int goodsIndex = warehouse.getGoods(index);
            if (goodsIndex != -1) {
                System.out.println("Грузчик " + Thread.currentThread().getId() + " взял товар " + goodsIndex);
            }
        }

        warehouse.clearGoods();

        if (index == warehouse.capacity - 1) {
            System.out.println("Последний грузчик дает отмашку конвейеру");
            synchronized (warehouse) {
                warehouse.notify();
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите количество грузчиков: ");
        int numOfLoaders = scanner.nextInt();
        
        Warehouse warehouse = new Warehouse(10);

        for (int i = 0; i < numOfLoaders; i++) {
            new Thread(new Loader(warehouse)).start();
        }

        synchronized (warehouse) {
            try {
                warehouse.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Конвейер начинает загружать склад снова");
    }
}
