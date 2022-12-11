package main;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PlayerStrategyTwoTest {
    Deck deck = new Deck();

    Card[] hand = {new Card("Bleu", 4),new Card("Bleu", 1), new Card("Rouge", 4),  new Card(null, 2),
    new Card("Vert", 0), new Card("Jaune", 0)};
    
    Card[] hand1 = {new Card("Bleu", 0), new Card("Rouge", 4),  new Card(null, 2),
    new Card("Vert", 0)};
    
    Card[] hand2 = {new Card("Bleu", 0), new Card("Rouge", 5),new Card("Rouge", 5), new Card(null, 2),
    new Card("Vert", 0),new Card("Rouge", 9),};
    
    Card[] hand3 = {new Card("Rouge", 9), new Card(null, 2),new Card("Jaune", 7)};
    
    Card[] hand4 = {new Card("Rouge", 9)};

    Card [] centerRow = {new Card("Bleu", 5), new Card("Vert", 0)};

    Player player = new PlayerStrategyTwo("Elena", hand); 
    Player player1 = new PlayerStrategyTwo("Ela", hand1); 
    Player player2 = new PlayerStrategyTwo("Mira", hand2); 
    Player player3 = new PlayerStrategyTwo("Elion", hand3); 
    Player player4 = new PlayerStrategyTwo("Lilya", hand4); 
    
    @Test
    public void testGetName() {
        
        String name = player.getName();
        assertEquals("Elena", name);
    }

    @Test
    public void testDoubleMatch() {
        List<Card> cardsDoubleMatch1 = new ArrayList<>();
        cardsDoubleMatch1 = player.doubleMatch(centerRow);
        assertEquals(4, cardsDoubleMatch1.get(0).getValeur());    
    }

    @Test
    public void testJockerDos() {
        boolean bol = player.isJockerDos();
        assertTrue(bol);   
    }

    @Test
    public void testChangeCentralRow() {
        Card card = new Card("Bleu", 7);
        player.changeCentralRow(card, centerRow);
        assertEquals(7 , centerRow[0].getValeur());
    }

    @Test
    public void testGetNbOfSameCards() {
        List <Integer> cards = new ArrayList<>();
        cards.add(0);
        cards.add(0);
        cards.add(3);
        int nb = player.getNbOfSameCards(cards);
        assertEquals(2, nb);
    }

    @Test
    public void testGetNbOfSameCards1() {
        List <Integer> cards = new ArrayList<>();
        cards.add(0);
        cards.add(9);
        cards.add(3);
        int nb = player.getNbOfSameCards(cards);
        assertEquals(0, nb);
    }

    @Test
    public void testChoseCardAfterJockerDos() {
        Card card = player.choseCardAfterJockerDos();
        assertEquals("Bleu" , card.getCouleur());
    }

    @Test
    public void matchValueTest(){
        Card card = player1.matchValue(new Card("Jaune", 0));
        assertEquals("Bleu", card.getCouleur());
        assertEquals(0, card.getValeur()) ;
    }

    @Test
    public void matchValueTest1(){
        Card card = player1.matchValue(new Card("Jaune", 9));
        assertEquals(null, card);
        
    }

    @Test
    public void takeOneCardTest(){
        player.takeOneCard();
        Map <String, List<Integer>> handPL = player.getHand();
        assertEquals(5, handPL.size());
       
    }

    @Test
    public void matchColorTest(){
        Card card = player.matchColor("Jaune");
        assertEquals("Jaune", card.getCouleur());
        assertEquals(0, card.getValeur()) ;
    }

    @Test
    public void playARoundTest() {
        player1.playARound(centerRow);
        Map <String, List<Integer>> handPlayer = player1.getHand();
        assertEquals(3, handPlayer.size());
    }

    @Test
    public void playARoundTest1() {
        player.playARound(centerRow);
        Map <String, List<Integer>> handPlayer = player.getHand();
        assertEquals(4, handPlayer.size());
    }

    @Test
    public void playARoundTest2() {
        player2.playARound(centerRow);
        Map <String, List<Integer>> handPlayer = player.getHand();
        List<Integer> values = handPlayer.get("Rouge");
        assertEquals(1, values.size());
    }

    @Test
    public void playARoundTest3() {
        player3.playARound(centerRow);
        Map <String, List<Integer>> handPlayer = player3.getHand();
        assertEquals(2, handPlayer.size());
    }

    @Test
    public void playARoundTest4() {
        player4.playARound(centerRow);
        Map <String, List<Integer>> handPlayer = player4.getHand();
        int nbOfCards= 0;
        for (String color : handPlayer.keySet()){
            nbOfCards+= handPlayer.get(color).size();
        }
        assertEquals(2, nbOfCards);
    }
}
