import java.util.ArrayDeque;

public class CustomQueue<T> {
    private final ArrayDeque<T> deque = new ArrayDeque<>();

    public void addFirst(T element) {
        deque.addFirst(element);
    }

    public void addLast(T element) {
        deque.addLast(element);
    }

    public void removeLast(int count) {
        for (int i = 0; i < count; i++) {
            deque.removeLast();
        }
    }

    public void removeFirst(int count) {
        for (int i = 0; i < count; i++) {
            deque.removeFirst();
        }
    }

    public void printQueue() {
        System.out.println(deque);
    }

    public static void main(String[] args) {
        CustomQueue<Integer> customQueue = new CustomQueue<>();
        ArrayDeque<Integer> originalDeque = new ArrayDeque<>();

        for (int i = 0; i < 10; i++) {
            customQueue.addFirst(i);
            originalDeque.addFirst(i);
        }

        for (int i = 0; i < 12; i++) {
            customQueue.addLast(i);
            originalDeque.addLast(i);
        }

        customQueue.removeLast(4);
        originalDeque.removeLast(4);

        customQueue.removeFirst(2);
        originalDeque.removeFirst(2);

        System.out.println("Custom Queue: ");
        customQueue.printQueue();
        System.out.println("Original ArrayDeque: ");
        System.out.println(originalDeque);
    }
}
