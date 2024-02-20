import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> elements = Arrays.asList("a1", "b5", "a2", "b4");

        String minElement = elements.stream()
                .min(String::compareTo)
                .orElse(null);

        System.out.println("Smallest element: " + minElement);
    }
}
