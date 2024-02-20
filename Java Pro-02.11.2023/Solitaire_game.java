import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

enum Suit {
    HEARTS, DIAMONDS, CLUBS, SPADES
}

class Card {
    Suit suit;

    public Card(Suit suit) {
        this.suit = suit;
    }

    @Override
    public String toString() {
        return suit.toString();
    }
}

public class SolitaireGame {
    public static void main(String[] args) {
        List<Card> deck = createShuffledDeck();
        List<List<Card>> tableau = new ArrayList<>();

        while (!isSolitaireCompleted(tableau)) {
            Collections.shuffle(deck);
            tableau.clear();

            for (Card card : deck) {
                if (tableau.isEmpty() || !tableau.get(tableau.size() - 1).get(0).suit.equals(card.suit)) {
                    List<Card> stack = new ArrayList<>();
                    stack.add(card);
                    tableau.add(stack);
                } else {
                    tableau.get(tableau.size() - 1).add(card);
                }
            }
        }

        System.out.println("Solitaire completed! Final tableau:");
        tableau.forEach(System.out::println);
    }

    private static List<Card> createShuffledDeck() {
        List<Card> deck = new ArrayList<>();
        for (Suit suit : Suit.values()) {
            deck.add(new Card(suit));
            deck.add(new Card(suit));
        }
        Collections.shuffle(deck);
        return deck;
    }

    private static boolean isSolitaireCompleted(List<List<Card>> tableau) {
        for (List<Card> stack : tableau) {
            if (stack.size() % 2 != 1) {
                return false;
            }
        }
        return true;
    }
}
