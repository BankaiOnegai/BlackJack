import java.io.*;
import java.util.HashMap;

public class BlackJack {
    private final Deck deck;
    private final Hand playerHand;
    private final Hand bankHand;
    private final String SCORE_FILENAME;
    public boolean gameFinished;
    private int playerScore;
    private int bankScore;

    public BlackJack() {
        SCORE_FILENAME = "Score.txt";
        this.deck = new Deck(4);
        this.playerHand = new Hand();
        this.bankHand = new Hand();
        this.gameFinished = false;
        try {
            reset();
        } catch (EmptyDeckException ex) {
            System.out.println(ex);
        }
    }

    public void reset() throws EmptyDeckException {
        gameFinished = false;
        playerHand.clear();
        bankHand.clear();
        playerHand.add(deck.draw());
        playerHand.add(deck.draw());
        bankHand.add(deck.draw());
    }

    public String getPlayerHandString() {
        return this.playerHand.toString();
    }

    public String getBankHandString() {
        return this.bankHand.toString();
    }

    public int getPlayerBest() {
        return this.playerHand.best();
    }

    public int getBankBest() {
        return this.bankHand.best();
    }

    public boolean isPlayerWinner() {
        if(getPlayerBest() <= 21 && gameFinished && (getPlayerBest() > getBankBest() || getBankBest() > 21))
        {
            return true;
        }
        return false;
    }

    public boolean isBankWinner() {
        if(getBankBest() <= 21 && gameFinished && (getBankBest() > getPlayerBest() || getPlayerBest() > 21))
        {
            return true;
        }
        return false;
    }

    public boolean isGameFinished() {
        return gameFinished;
    }

    public void playerDrawAnotherCard() throws EmptyDeckException {
        if (!gameFinished) {
            playerHand.add(deck.draw());
            if (getPlayerBest() > 21) {
                gameFinished = true;
            }
        }
    }

    public void bankLastTurn() throws EmptyDeckException {
        System.out.println(getBankBest());
        System.out.println(getPlayerBest());

        while ((!gameFinished && getPlayerBest() <= 21) &&
                (getBankBest() <= 21) &&
                (getBankBest() < getPlayerBest())) {
            this.bankHand.add(deck.draw());
        }
        gameFinished = true;
    }

    public void updateScores() {
        if (isPlayerWinner()) {
            playerScore++;
        } else if (isBankWinner()){
            bankScore++;
        }
    }

    public void writeScore() {
        try {
            PrintWriter write;
            write = new PrintWriter(new BufferedWriter(new FileWriter(SCORE_FILENAME)));
            updateScores();
            write.println("Player " + playerScore + "\nBank " + bankScore);
            write.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readScore() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(SCORE_FILENAME));
            HashMap<String, Integer> scores;
            playerScore = Integer.parseInt(reader.readLine().split(" ")[1]);
            bankScore = Integer.parseInt(reader.readLine().split(" ")[1]);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
