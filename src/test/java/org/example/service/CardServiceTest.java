package org.example.service;

import org.example.enums.SuitTypeEnum;
import org.example.model.Card;
import org.example.service.CardService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CardServiceTest {

    CardService cardService = new CardService();

    @Test
    void createDeckOfCards_shouldCreateFullSetOfCards() {
        List<Card> deck = cardService.createDeckOfCards(1);
        int numberOfHearts = 0;
        int numberOfDiamonds = 0;
        int numberOfClubs = 0;
        int numberOfSpades = 0;

        for(Card card : deck) {
            if(card.getSuit().equalsIgnoreCase(SuitTypeEnum.HEARTS.name())) {
                numberOfHearts++;
            }
            if(card.getSuit().equalsIgnoreCase(SuitTypeEnum.DIAMONDS.name())) {
                numberOfDiamonds++;
            }
            if(card.getSuit().equalsIgnoreCase(SuitTypeEnum.CLUBS.name())) {
                numberOfClubs++;
            }
            if(card.getSuit().equalsIgnoreCase(SuitTypeEnum.SPADES.name())) {
                numberOfSpades++;
            }
        }

        assertEquals(13, numberOfHearts);
        assertEquals(13, numberOfClubs);
        assertEquals(13, numberOfSpades);
        assertEquals(13, numberOfDiamonds);
    }

    @ParameterizedTest
    @MethodSource("provideNumberOfDecks")
    void createDeckOfCards_shouldCreateFullSetOfCardsWithSpecifiedNumberOfDecks(int numberOfDecks, int numberOfCards) {
        List<Card> deck = cardService.createDeckOfCards(numberOfDecks);

        assertEquals(numberOfCards, deck.size());
    }

    private static Stream<Arguments> provideNumberOfDecks() {
        return Stream.of(
                Arguments.of(1, 52),
                Arguments.of(2, 104),
                Arguments.of(3, 156),
                Arguments.of(24, 1248)
        );
    }

    @Test
    void dealCards_shouldReturnTwoEqualSizedLists() {
        List<Card> deck = cardService.createDeckOfCards(1);

        List[] hands = cardService.dealCards(deck);

        assertEquals(hands[0].size(), hands[1].size());
    }



}