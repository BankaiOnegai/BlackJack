import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Deck {
    private LinkedList<Card> cardList = new LinkedList<Card>(); 

    public Deck(int nbBox) {
        Value[] valuesArray = Value.values();
        Color[] colorsArray = Color.values();

        for (int i = 0; i < nbBox; i++) {
            for (Value v : valuesArray) {
                for (Color c : colorsArray) {
                    Card f = new Card(v, c);
                    cardList.add(f);
                }
            }
        }
        Collections.shuffle(cardList);
    }

    public String toString() {
        return cardList.toString();
    }

    public Card draw() throws EmptyDeckException 
    {
        if (cardList == null) {
            throw new EmptyDeckException("Erreur");
        } else {
            return cardList.pollFirst();
        }
    }
}
