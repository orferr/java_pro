import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.*;

enum EventState {
    SUCCESS, REQUESTERROR, ACCESSDENIED
}

class Event {
    private final int eventId;
    private final String userName;
    private final String userIp;
    private final LocalDate eventDate;
    private final EventState eventState;

    public Event(int eventId, String userName, String userIp, LocalDate eventDate, EventState eventState) {
        this.eventId = eventId;
        this.userName = userName;
        this.userIp = userIp;
        this.eventDate = eventDate;
        this.eventState = eventState;
    }

    public int getEventId() {
        return eventId;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserIp() {
        return userIp;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public EventState getEventState() {
        return eventState;
    }
}

class EventCreator {
    public static Event createEvent(int eventId, String userName, String userIp, LocalDate eventDate, EventState eventState) {
        return new Event(eventId, userName, userIp, eventDate, eventState);
    }
}

class FirewallEventListener {
    private final String name;
    private final List<CompletableFuture<String>> logs;

    public FirewallEventListener(String name, List<CompletableFuture<String>> logs) {
        this.name = name;
        this.logs = logs;
    }

    public CompletableFuture<Void> processEventAsync(Event event) {
        return CompletableFuture.runAsync(() -> {
            // Логика обработки события
            String log1 = "Обработчик " + name + event.getEventId() + event.getUserName();
            String log2 = "Обработчик " + name + event.getEventId() + event.getUserIp();
            String log3 = "Обработчик " + name + event.getEventId() + event.getEventDate();
            String log4 = "Обработчик " + name + event.getEventId() + event.getEventState();
            
            // Добавление логов в CompletableFuture
            logs.add(CompletableFuture.completedFuture(log1));
            logs.add(CompletableFuture.completedFuture(log2));
            logs.add(CompletableFuture.completedFuture(log3));
            logs.add(CompletableFuture.completedFuture(log4));
        });
    }
}

public class JavaPro {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        int numberOfUsers = 10;
        List<CompletableFuture<String>> logs = new CopyOnWriteArrayList<>();
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        List<FirewallEventListener> listeners = List.of(
                new FirewallEventListener("1", logs),
                new FirewallEventListener("2", logs),
                new FirewallEventListener("3", logs),
                new FirewallEventListener("4", logs)
        );

        CompletableFuture<Void> allEventsProcessed = CompletableFuture.allOf(
                listeners.stream()
                        .map(listener -> CompletableFuture.runAsync(() -> {
                            for (int i = 1; i <= numberOfUsers; i++) {
                                Event event = EventCreator.createEvent(i, "User" + i, "192.168.0." + i, LocalDate.now(), EventState.SUCCESS);
                                listener.processEventAsync(event).join();
                            }
                        }, executorService))
                        .toArray(CompletableFuture[]::new)
        );

        allEventsProcessed.thenRun(() -> {
            logs.forEach(log -> System.out.println(log.join()));
            executorService.shutdown();
        }).get();
    }
}
