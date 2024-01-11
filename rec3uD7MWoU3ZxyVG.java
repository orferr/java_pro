import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SearchEngine {
    private Map<String, String> index;

    public SearchEngine() {
        this.index = new HashMap<>();
    }

    public void indexDocument(String documentId, String content) {
        index.put(documentId, content);
    }

    public void search(String query) {
        for (Map.Entry<String, String> entry : index.entrySet()) {
            String documentId = entry.getKey();
            String content = entry.getValue();
            if (content.contains(query)) {
                System.out.println("Найден документ с ID " + documentId);
            }
        }
    }

    public static void main(String[] args) {
        SearchEngine searchEngine = new SearchEngine();
        Scanner scanner = new Scanner(System.in);

        searchEngine.indexDocument("doc1", "Java программирование основы");
        searchEngine.indexDocument("doc2", "Поиск в Java");
        searchEngine.indexDocument("doc3", "Реализация поисковой машины на Java");

        System.out.print("Введите запрос: ");
        String query = scanner.nextLine();

        searchEngine.search(query);
        scanner.close();
    }
}
