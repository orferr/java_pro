import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MultiplyByTwo {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        List<Integer> result = multiplyByTwo(numbers);

        System.out.println("Original List: " + numbers);
        System.out.println("List after multiplication by 2: " + result);
    }

    public static List<Integer> multiplyByTwo(List<Integer> numbers) {
        return numbers.stream()
                .map(n -> n * 2)
                .collect(Collectors.toList());
    }
}
