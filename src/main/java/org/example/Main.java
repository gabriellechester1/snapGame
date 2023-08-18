package org.example;

import org.example.model.Card;
import org.example.model.Player;
import org.example.service.CardService;
import org.example.service.RulesService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class Main {

    static CardService cardService = new CardService();
    private static Integer SNAP_RULES = 0;
    private static Integer NUMBER_OF_DECKS = 0;

    public static void main(String[] args) {
        System.out.println("Please enter the number of decks you want to play with: (e.g) 1, 2 ,3");
        Scanner inputNumberOfDecks = new Scanner(System.in);
        while (!inputNumberOfDecks.hasNextInt()) {
            System.out.println("Please enter a number");
            inputNumberOfDecks.next();
        }
        NUMBER_OF_DECKS = inputNumberOfDecks.nextInt();

        if(NUMBER_OF_DECKS == 1) {
            System.out.println("Please enter the number for which set of rules you would like to snap on: (e.g) 1, 2 ,3");
            System.out.println("1.value  2.suit");
            Scanner inputSnapRules = new Scanner(System.in);
            while (!inputSnapRules.hasNext("[1-2]")) {
                System.out.println("Please enter 1 or 2");
                inputSnapRules.next();
            }
        } else {
            System.out.println("Please enter the number for which set of rules you would like to snap on: (e.g) 1, 2 ,3");
            System.out.println("1.value  2.suit  3.value & suit");
            Scanner inputSnapRules = new Scanner(System.in);
            while (!inputSnapRules.hasNext("[1-3]")) {
                System.out.println("Please enter 1, 2 or 3");
                inputSnapRules.next();
            }
            SNAP_RULES = inputSnapRules.nextInt();
        }

        List<Card> cards = cardService.createDeckOfCards(Integer.valueOf(NUMBER_OF_DECKS));

        List[] hands = cardService.dealCards(cards);

        List<Player> players = new ArrayList<>();
        players.add(new Player("Player 1", hands[0]));
        players.add(new Player("Player 2", hands[1]));

        Stack<Card> stack = new Stack<>();

        PlayGame playGame = new PlayGame(new RulesService(SNAP_RULES));
        playGame.play(players, stack);

    }
}