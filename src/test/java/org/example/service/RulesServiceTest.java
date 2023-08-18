package org.example.service;

import org.example.model.Card;
import org.example.model.Player;
import org.example.service.RulesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;

class RulesServiceTest {

    private RulesService rulesService;
    private final Integer snapRules = 1;


    @BeforeEach
    void setup() {
        rulesService = new RulesService(snapRules);
    }

    @Test
    void isSnap_shouldReturnTrueWhenItsSnap() {
        Player activePlayer = new Player("test", new ArrayList<>());
        Card card1 = new Card("heart", "5");
        Card card2 = new Card("club", "5");

        Stack<Card> stack = new Stack<>();
        stack.push(card1);
        stack.push(card2);

        assertTrue(rulesService.isSnap(stack, activePlayer, snapRules));
    }

    @Test
    void isSnap_shouldReturnFalseWhenStackSizeIsOne() {
        Player activePlayer = new Player("test", new ArrayList<>());
        Card card1 = new Card("heart", "5");

        Stack<Card> stack = new Stack<>();
        stack.push(card1);

        assertFalse(rulesService.isSnap(stack, activePlayer, snapRules));
    }

    @Test
    void isSnap_shouldReturnFalseWhenStackSizeIsZero() {
        Player activePlayer = new Player("test", new ArrayList<>());

        Stack<Card> stack = new Stack<>();

        assertFalse(rulesService.isSnap(stack, activePlayer, snapRules));
    }

    @Test
    void isSnap_shouldGetPlayerToPickUpCards() {
        Player activePlayer = new Player("test", new ArrayList<>());
        Card card1 = new Card("heart", "5");
        Card card2 = new Card("club", "5");

        Stack<Card> stack = new Stack<>();
        stack.push(card1);
        stack.push(card2);

        rulesService.isSnap(stack, activePlayer, snapRules);

        assertEquals(2, activePlayer.getHandOfCards().size());
    }

    @Test
    void isSnap_shouldClearStackAfterSnap() {
        Player activePlayer = new Player("test", new ArrayList<>());
        Card card1 = new Card("heart", "5");
        Card card2 = new Card("club", "5");

        Stack<Card> stack = new Stack<>();
        stack.push(card1);
        stack.push(card2);

        rulesService.isSnap(stack, activePlayer, snapRules);

        assertEquals(0, stack.size());
    }


}