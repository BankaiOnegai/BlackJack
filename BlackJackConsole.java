import java.util.Scanner;
import java.util.HashMap;
import java.io.*;

public class BlackJackConsole {

    static Scanner scan = new Scanner(System.in);

    public BlackJackConsole() throws EmptyDeckException {

        System.out.println("Welcome to the BlackJack table. Let's play !");

        BlackJack blackjack = new BlackJack();
        blackjack.readScore();

        System.out.println("The bank draw : " + blackjack.getBankHandString());
        System.out.println("Your hand : " + blackjack.getPlayerHandString());

        while (!blackjack.gameFinished) {
            System.out.println("Do you want another card ? [y/n]");
            String choice = scan.next();
            switch (choice.charAt(0)) {
                case 'y' :
                    blackjack.playerDrawAnotherCard();
                    System.out.println("Your new hand : " + blackjack.getPlayerHandString());
                    break;
                case 'n' :
                    blackjack.gameFinished = true;
                    break;
                default :
                    System.out.println("Bad choice . Try again . ");
                    break;
            }
        }
        blackjack.gameFinished = false;
        blackjack.bankLastTurn();
        System.out.println("The bank draws cards . New hand: " + blackjack.getBankHandString());

        System.out.println("Player best : " + blackjack.getPlayerBest());
        System.out.println("Bank best: " + blackjack.getBankBest());
        System.out.println(blackjack.isPlayerWinner());

        if (!blackjack.isPlayerWinner()) {
            if (!blackjack.isBankWinner()) {//no winner
                System.out.println("Draw !");
            } else {//Bank Win
                System.out.println("Bank Win ! Try again !");
            }
        } else {  // Player won
            System.out.println("You win ! GG well play ");
        }
        blackjack.writeScore();
    }


    public static void main(String[] args) {
        try {
            new BlackJackConsole();
        } catch (EmptyDeckException ex) {
            System.out.println(ex);
        }
    }
}