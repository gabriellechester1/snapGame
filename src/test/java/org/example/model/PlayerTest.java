package org.example.model;

import org.example.enums.SuitTypeEnum;
import org.example.model.Card;
import org.example.model.Player;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PlayerTest {

    @Test
    void playCard_shouldDecreaseHandOfCardsByOne() {
        Card card = new Card(SuitTypeEnum.DIAMONDS.name(), "5");
        Card card2 = new Card(SuitTypeEnum.HEARTS.name(), "6");
        Card card3 = new Card(SuitTypeEnum.CLUBS.name(), "7");

        List<Card> handOfCards = new ArrayList<>();
        handOfCards.add(card);
        handOfCards.add(card2);
        handOfCards.add(card3);

        Player player = new Player("Player 1", handOfCards);

        Stack<Card> stack = new Stack<>();

        player.playCard(stack);

        assertEquals(2, player.getHandOfCards().size());
    }

    @Test
    void playCard_shouldIncreaseStackByOne() {
        Card card = new Card(SuitTypeEnum.HEARTS.name(), "5");
        Card card2 = new Card(SuitTypeEnum.DIAMONDS.name(), "6");
        Card card3 = new Card(SuitTypeEnum.CLUBS.name(), "7");

        List<Card> handOfCards = new ArrayList<>();
        handOfCards.add(card);
        handOfCards.add(card2);
        handOfCards.add(card3);

        Player player = new Player("Player 1", handOfCards);

        Stack<Card> stack = new Stack<>();

        player.playCard(stack);

        assertEquals(1, stack.size());
    }

    @Test
    void playCard_shouldNoTryToPlayCardIfPlayerHasNoCards() {
        List<Card> handOfCards = new ArrayList<>();
        Player player = new Player("Player 1", handOfCards);
        Stack<Card> stack = new Stack<>();

        player.playCard(stack);

        assertEquals(0, stack.size());
    }

    @Test
    void pickUpCards_shouldPickUpStackAndAddItToHand() {
        List<Card> handOfCards = new ArrayList<>();
        handOfCards.add(new Card(SuitTypeEnum.HEARTS.name(), "5"));
        handOfCards.add(new Card(SuitTypeEnum.DIAMONDS.name(), "6"));
        handOfCards.add(new Card(SuitTypeEnum.DIAMONDS.name(), "7"));

        Player player = new Player("Player 1", handOfCards);

        Stack<Card> stack = new Stack<>();
        stack.push(new Card(SuitTypeEnum.HEARTS.name(), "1"));
        stack.push(new Card(SuitTypeEnum.DIAMONDS.name(), "8"));
        stack.push(new Card(SuitTypeEnum.CLUBS.name(), "4"));

        player.pickUpCards(stack);

        assertEquals(6, player.getHandOfCards().size());
    }


}