package main;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

public class DeckTest {

    Deck deck = new Deck();

    @Test
    public void testTableauSize(){
        Card [] tab = Deck.getCards(2);
        assertEquals(2, tab.length);
    }

    @Test
    public void testSizeNewHand() {
        Card [] hand = deck.newHand();
        assertEquals(7, hand.length);
    }

    @Test
    public void testCRSize(){
        Card [] centraRow = Deck.centerRow();
        assertEquals(2, centraRow.length);
    }
    
    @Test
    public void testCardsCentralRow(){
        Card [] centraRow = Deck.centerRow();
        Card jockerDos = new Card(null,2);
        for (Card card: centraRow){
            assertNotEquals(card, jockerDos);
        }
       
    }

    @Test
    public void testIsCardsEmpty() {
        boolean emptyDeck = Deck.isCardsEmpty();
        assertFalse(emptyDeck);
    }    
}

