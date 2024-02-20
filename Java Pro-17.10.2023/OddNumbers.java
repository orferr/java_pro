import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class OddNumbers {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        List<Integer> result = getOddNumbers(numbers);

        System.out.println("Original List: " + numbers);
        System.out.println("List of odd numbers: " + result);
    }

    public static List<Integer> getOddNumbers(List<Integer> numbers) {
        return numbers.stream()
                .filter(n -> n % 2 != 0)
                .collect(Collectors.toList());
    }
}
