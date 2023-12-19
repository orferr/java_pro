import java.io.*;
import java.util.*;

public class TextEncoderDecoder {
    public static void main(String[] args) {
        String inputFileName = "input.txt";
        String encodedFileName = "encoded.txt";

        Map<Character, List<Integer>> charIndexMap = readAndEncodeFile(inputFileName);
        saveEncodedToFile(charIndexMap, encodedFileName);

        String decodedText = decodeFile(encodedFileName, charIndexMap);
        String decodedFileName = "decoded.txt";
        saveDecodedToFile(decodedText, decodedFileName);

        compareFiles(inputFileName, decodedFileName);
    }

    private static Map<Character, List<Integer>> readAndEncodeFile(String fileName) {
        Map<Character, List<Integer>> charIndexMap = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            int index = 0;

            while ((line = reader.readLine()) != null) {
                for (char c : line.toCharArray()) {
                    charIndexMap.computeIfAbsent(c, k -> new ArrayList<>()).add(index++);
                }
                charIndexMap.computeIfAbsent('\n', k -> new ArrayList<>()).add(index++);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return charIndexMap;
    }

    private static void saveEncodedToFile(Map<Character, List<Integer>> charIndexMap, String fileName) {
        try (PrintWriter writer = new PrintWriter(fileName)) {
            for (Map.Entry<Character, List<Integer>> entry : charIndexMap.entrySet()) {
                writer.println(entry.getKey() + ":" + entry.getValue());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static String decodeFile(String encodedFileName, Map<Character, List<Integer>> charIndexMap) {
        StringBuilder decodedText = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(encodedFileName))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                char c = parts[0].charAt(0);
                List<Integer> indexes = Arrays.asList(parts[1].split(",")).stream()
                        .map(Integer::parseInt)
                        .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);

                for (int index : indexes) {
                    decodedText.insert(index, c);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return decodedText.toString();
    }

    private static void saveDecodedToFile(String decodedText, String fileName) {
        try (PrintWriter writer = new PrintWriter(fileName)) {
            writer.print(decodedText);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void compareFiles(String fileName1, String fileName2) {
        try (BufferedReader reader1 = new BufferedReader(new FileReader(fileName1));
             BufferedReader reader2 = new BufferedReader(new FileReader(fileName2))) {

            int lineIndex = 0;
            String line1, line2;

            while ((line1 = reader1.readLine()) != null && (line2 = reader2.readLine()) != null) {
                for (int i = 0; i < line1.length(); i++) {
                    char char1 = line1.charAt(i);
                    char char2 = line2.charAt(i);

                    if (char1 != char2) {
                        System.out.println("Files are not identical at line " + lineIndex + ", index " + i);
                        return;
                    }
                }
                lineIndex++;
            }

            System.out.println("Files are identical.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


//задача 1


import java.io.*;
        import java.util.*;

class Storage<T> {
    private final Map<T, Integer> elements = new HashMap<>();

    public void addElement(T element) {
        elements.put(element, elements.getOrDefault(element, 0) + 1);
    }

    public Map<T, Integer> getElements() {
        return new HashMap<>(elements);
    }
}

public class CustomHashGenerator {
    public static void main(String[] args) {
        Storage<Character> charStorage = new Storage<>();
        Storage<Integer> numStorage = new Storage<>();

        String inputFileName = "input.txt";
        readAndCountOccurrences(inputFileName, charStorage, numStorage);

        String hashFileName = "customHash.txt";
        String numFileName = "customNumbers.txt";

        writeHashToFile(charStorage, hashFileName);
        writeNumbersToFile(numStorage, numFileName);
    }

    private static void readAndCountOccurrences(String fileName, Storage<Character> charStorage, Storage<Integer> numStorage) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;

            while ((line = reader.readLine()) != null) {
                for (char c : line.toCharArray()) {
                    if (Character.isLetter(c)) {
                        charStorage.addElement(c);
                    } else if (Character.isDigit(c)) {
                        numStorage.addElement(Integer.parseInt(String.valueOf(c)));
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeHashToFile(Storage<Character> charStorage, String fileName) {
        try (PrintWriter writer = new PrintWriter(fileName)) {
            Map<Character, Integer> charElements = charStorage.getElements();
            for (Map.Entry<Character, Integer> entry : charElements.entrySet()) {
                writer.print(entry.getKey() + entry.getValue());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void writeNumbersToFile(Storage<Integer> numStorage, String fileName) {
        try (PrintWriter writer = new PrintWriter(fileName)) {
            Map<Integer, Integer> numElements = numStorage.getElements();
            for (Map.Entry<Integer, Integer> entry : numElements.entrySet()) {
                writer.println(entry.getKey() + "_" + entry.getValue());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}