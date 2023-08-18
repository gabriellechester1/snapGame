package org.example;

import org.example.model.Card;
import org.example.model.Player;
import org.example.service.RulesService;

import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class PlayGame {

    private RulesService rulesService;
    boolean isPlaying;

    public PlayGame(RulesService rulesService) {
        this.rulesService = rulesService;
        this.isPlaying = true;
    }

    public void play(List<Player> players, Stack<Card> stack) {
        while(isPlaying) {
            players = players.stream().filter(p -> p.getNumberOfCardsLeft() != 0).collect(Collectors.toList());
            for (Player player : players) {
                if (players.size() == 1) {
                    System.out.println(players.get(0).getName() + " IS THE WINNER!");
                    isPlaying = false;
                } else {
                    player.playCard(stack);
                    rulesService.isSnap(stack, player, rulesService.getSnapRules());
                    System.out.println("-------------------");
                }
            }
            if(players.size() == 0) {
                System.out.println("All cards have been dealt, neither player wins!");
                isPlaying = false;
            }
        }
    }
}
