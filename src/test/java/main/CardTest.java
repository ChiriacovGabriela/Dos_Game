package main;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class CardTest {
    @Test
    public void testCouleur1(){
        Card card = new Card("Y",9);
        assertEquals("Y",card.getCouleur());
    }
    @Test
    public void testCouleur2(){
        Card card = new Card(null,9);
        assertEquals(null,card.getCouleur());
    }
    @Test
    public void testValeur(){
        Card card = new Card("Y",9);
        assertEquals(9,card.getValeur());
    }
}