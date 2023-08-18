package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.Stack;

@Getter
@AllArgsConstructor
public class Player {
    String name;
    List<Card> handOfCards;

    public void playCard(Stack<Card> stack) {
        if(handOfCards.size() > 0) {
            System.out.println(name + " plays card: " + handOfCards.get(0).getValue() + " of " + handOfCards.get(0).getSuit());
            stack.push(handOfCards.get(0));
            handOfCards.remove(0);
            System.out.println(name + " has " + handOfCards.size() + " cards left");
        } else {
            System.out.println(name + " has ran out of cards! ");
        }

    }

    public void pickUpCards(Stack<Card> stack) {
        handOfCards.addAll(stack);
    }

    public int getNumberOfCardsLeft() {
        return handOfCards.size();
    }

}
