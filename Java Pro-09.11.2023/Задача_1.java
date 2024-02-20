import java.util.HashMap;
import java.util.Map;

public class CharacterCount {
    public static void main(String[] args) {
        String text = "Hello, World!";
        Map<Character, Integer> result = countCharacters(text);

        System.out.println("Character\tCount");
        result.forEach((character, count) -> System.out.println(character + "\t\t" + count));
    }

    public static Map<Character, Integer> countCharacters(String text) {
        Map<Character, Integer> charCountMap = new HashMap<>();
        for (char ch : text.toCharArray()) {
            charCountMap.put(ch, charCountMap.getOrDefault(ch, 0) + 1);
        }
        return charCountMap;
    }
}
