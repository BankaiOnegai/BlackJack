import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Hand {

    public List<Integer> vaList;
    private final LinkedList<Card> cardList;

    public Hand() {
        this.cardList = new LinkedList<Card>();
    }

    public void add(Card card) {
        cardList.add(card);
    }

    public String toString() {
        return cardList.toString() + " : " + count();
    }

    public List<Integer> count() {
        List<Integer> vaList = new ArrayList<Integer>();
        int val, taille;
        vaList.add(0);
        for (Card c : cardList) {
            taille = vaList.size();
            for (int i = 0; i < taille; i++) {
                val = vaList.get(i);
                vaList.set(i, val + c.getPoints());
                if (c.getPoints() == 1) {
                    vaList.add(val + 11);
                }
            }
        }
        return vaList;
    }

    public int best() {
        List<Integer> pointList = count();
        int bestScore = 0;
        int hightest = Collections.max(pointList);

        for (int p : pointList) {
            if (p <= 21 && p > bestScore) {
                bestScore = p;
            }
        }
        if (bestScore == 0) {
            return hightest;
        }
        return bestScore;
    }

    public void clear() {
        cardList.clear();
    }
}