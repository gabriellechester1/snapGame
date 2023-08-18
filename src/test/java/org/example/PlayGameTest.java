package org.example;

import org.example.PlayGame;
import org.example.enums.SuitTypeEnum;
import org.example.model.Card;
import org.example.model.Player;
import org.example.service.RulesService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class PlayGameTest {

    @Mock
    private RulesService mockRulesService;

    @InjectMocks
    private PlayGame playGame;

    @Test
    void play_shouldEndGameWhenNumberOfPlayersIsOne() {
        playGame.isPlaying = true;
        Card card = new Card(SuitTypeEnum.HEARTS.name(), "2");
        List<Card> handOfCards = new ArrayList<>();
        handOfCards.add(card);

        List<Player> players = new ArrayList<>();
        Player player1 = new Player("player 1", handOfCards);
        players.add(player1);

        Stack<Card> stack = new Stack<>();

        playGame.play(players, stack);

        assertFalse(playGame.isPlaying);
    }

    @Test
    void play_shouldDeclareDrawIfBothPlayersRunOutOfCards() {
        Card card = new Card(SuitTypeEnum.HEARTS.name(), "2");
        List<Card> handOfCards1 = new ArrayList<>();
        handOfCards1.add(card);

        Card card2 = new Card(SuitTypeEnum.DIAMONDS.name(), "2");
        List<Card> handOfCards2 = new ArrayList<>();
        handOfCards2.add(card2);
        playGame.isPlaying = true;

        List<Player> players = new ArrayList<>();
        Player player1 = new Player("player 1", handOfCards1);
        Player player2 = new Player("player 2", handOfCards2);
        players.add(player1);
        players.add(player2);

        Stack<Card> stack = new Stack<>();

        playGame.play(players, stack);

        Mockito.verify(mockRulesService, times(1)).isSnap(stack, player1, 0);
    }

    @Test
    void play_shouldCallRulesServiceToCheckSnap() {
        playGame.isPlaying = true;

        List<Player> players = new ArrayList<>();
        Player player1 = new Player("player 1", new ArrayList<>());
        Player player2 = new Player("player 2", new ArrayList<>());
        players.add(player1);
        players.add(player2);

        Stack<Card> stack = new Stack<>();

        playGame.play(players, stack);
    }

}