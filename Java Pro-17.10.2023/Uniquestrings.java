import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class UniqueStrings {
    public static void main(String[] args) {
        List<String> strings = Arrays.asList("apple", "banana", "orange", "apple", "grape");

        List<String> result = getUniqueStrings(strings);

        System.out.println("Original List: " + strings);
        System.out.println("List of unique strings: " + result);
    }

    public static List<String> getUniqueStrings(List<String> strings) {
        return strings.stream()
                .distinct()
                .collect(Collectors.toList());
    }
}
