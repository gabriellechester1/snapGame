package org.example.service;

import lombok.Getter;
import org.example.model.Card;
import org.example.model.Player;

import java.util.Stack;

@Getter
public class RulesService {

    Integer snapRules;

    public RulesService(Integer snapRules) {
        this.snapRules = snapRules;
    }

    public boolean isSnap(Stack<Card> stack, Player activePlayer, Integer snapRules) {

        if (stack.size() > 1) {
            Card topOfStack = stack.peek();
            stack.pop();
            Card secondTopOfStack = stack.peek();
            stack.push(topOfStack);

            if (snapRules(topOfStack, secondTopOfStack, snapRules)) {
                System.out.println("SNAP - " + activePlayer.getName() + " picks up " + stack.size() + " cards");
                activePlayer.pickUpCards(stack);
                stack.clear();
                return true;
            }
        }
        return false;
    }

    private boolean snapRules(Card topOfStack, Card secondTopOfStack, Integer rules) {
        return switch (rules) {
            case 1 -> topOfStack.getValue().equals(secondTopOfStack.getValue());
            case 2 -> topOfStack.getSuit().equals(secondTopOfStack.getSuit());
            default -> (topOfStack.getSuit().equals(secondTopOfStack.getSuit()) &&
                    topOfStack.getValue().equals(secondTopOfStack.getValue()));
        };

    }

}
