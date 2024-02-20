import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

enum Answer {
    APPROVED, DENIED, ERROR
}

class UserRequest {
    String address;
    Answer answer;

    public UserRequest(String address, Answer answer) {
        this.address = address;
        this.answer = answer;
    }
}

class Storage {
    Map<String, List<UserRequest>> userRequestsMap = new HashMap<>();

    public void addRequest(String username, String address, Answer answer) {
        UserRequest userRequest = new UserRequest(address, answer);
        userRequestsMap.computeIfAbsent(username, k -> new ArrayList<>()).add(userRequest);
    }

    public List<UserRequest> getUserRequests(String username) {
        return userRequestsMap.getOrDefault(username, new ArrayList<>());
    }
}

public class UserRequestSystem {
    public static void main(String[] args) {
        Storage storage = new Storage();
        processInput(storage);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter username to view statistics:");
        String username = scanner.nextLine();
        List<UserRequest> userRequests = storage.getUserRequests(username);

        System.out.println("User Statistics for " + username + ":");
        for (UserRequest userRequest : userRequests) {
            System.out.println("Address: " + userRequest.address + ", Answer: " + userRequest.answer);
        }
    }

    private static void processInput(Storage storage) {
        String[] inputLines = {
                "User1; www.de.de; APPROVED",
                "User2; www.ebay.de; DENIED",
                "User2; www.amazon.de; DENIED",
                "User1; www.ebay.blabla; ERROR"
        };

        for (String line : inputLines) {
            String[] parts = line.split("; ");
            if (parts.length == 3) {
                String username = parts[0];
                String address = parts[1];
                Answer answer = Answer.valueOf(parts[2]);
                storage.addRequest(username, address, answer);
            }
        }
    }
}
