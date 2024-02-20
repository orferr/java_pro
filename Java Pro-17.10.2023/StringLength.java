import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StringLengths {
    public static void main(String[] args) {
        List<String> strings = Arrays.asList("apple", "banana", "orange", "grape");

        List<Integer> result = getStringLengths(strings);

        System.out.println("Original List: " + strings);
        System.out.println("List of string lengths: " + result);
    }

    public static List<Integer> getStringLengths(List<String> strings) {
        return strings.stream()
                .map(String::length)
                .collect(Collectors.toList());
    }
}
