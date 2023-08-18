package org.example.service;

import org.example.enums.SuitTypeEnum;
import org.example.model.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CardService {

    private List<Card> deck;
    private Integer numberOfDecks;

    public List<Card> createDeckOfCards(Integer numberOfDecks) {
        deck = new ArrayList<>();
        String[] suits = {SuitTypeEnum.SPADES.name(), SuitTypeEnum.CLUBS.name(), SuitTypeEnum.DIAMONDS.name(), SuitTypeEnum.HEARTS.name()};

        for(int j = 0; j < numberOfDecks; j++) {
            for (String suit : suits) {
                for (int i = 1; i < 11; i++) {
                    Card card = new Card(suit, String.valueOf(i));
                    deck.add(card);
                }
                deck.add(new Card(suit, "Jack"));
                deck.add(new Card(suit, "Queen"));
                deck.add(new Card(suit, "King"));
            }
        }
        System.out.println("Number of cards in the game: " + deck.size());
        shuffle(deck);
        return deck;
    }

    private void shuffle(List<Card> cards) {
        Collections.shuffle(cards);
    }

    public List[] dealCards(List<Card> cards) {
        int midIndex = ((cards.size() / 2) - (((cards.size() % 2) > 0) ? 0 : 1));

        List<List<Card> > lists = new ArrayList<>(
                cards.stream()
                        .collect(Collectors.partitioningBy(
                                s -> cards.indexOf(s) > midIndex))
                        .values());

        return new List[] { lists.get(0), lists.get(1) };

    }


}
