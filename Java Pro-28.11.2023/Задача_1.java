import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("a1", "b5", "c1", "a2", "b4", "c1", "a1");

        List<String> sortedList = list.stream()
                .distinct()
                .sorted()
                .collect(Collectors.toList());

        List<String> reversedList = list.stream()
                .distinct()
                .sorted((s1, s2) -> s2.compareTo(s1))
                .collect(Collectors.toList());

        System.out.println("Sorted List: " + sortedList);
        System.out.println("Reversed List: " + reversedList);
    }
}
