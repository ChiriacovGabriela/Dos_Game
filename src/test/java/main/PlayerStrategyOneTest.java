package main;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PlayerStrategyOneTest {
    Deck deck = new Deck();

    Card[] hand = {new Card("Bleu", 0), new Card("Rouge", 4),  new Card(null, 2),
    new Card("Vert", 1), new Card("Vert", 8), new Card("Vert", 5)};
    
    Card[] hand1 = { new Card("Rouge", 4),  new Card(null, 2),
    new Card("Vert", 1)};
    
    Card[] hand2 = { new Card("Rouge", 4),  new Card(null, 2), new Card("Jaune", 3) };

    Card[] hand3 = { new Card("Bleu", 4), new Card("Bleu", 4), new Card("Bleu", 6) };
    
    Card[] hand4 = { new Card("Rouge", 7)};

    Card[] hand5 = {new Card("Bleu", 0), new Card("Bleu", 3)};

    Card [] centerRow = {new Card("Bleu", 5), new Card("Vert", 0)};

    Card [] centerRow1 = {new Card("Bleu", 5), new Card("Vert", 9)};

    Card [] centerRow2 = { new Card("Rouge", 5), new Card("Vert", 0) };

    Card [] centerRow3 = { new Card("Bleu", 7 ), new Card("Jaune", 7) };

    PlayerStrategyOne playerOfStrategyOne  = new PlayerStrategyOne("Elena", hand); 
    PlayerStrategyOne playerOfStrategyOne1  = new PlayerStrategyOne("Alice", hand1); 
    Player player = new PlayerStrategyOne("Marc", hand);
    Player player1 = new PlayerStrategyOne("Pierre", hand1);
    Player player2 = new PlayerStrategyOne("Louis", hand2);
    Player player3 = new PlayerStrategyOne("Andra", hand3);
    Player player4 = new PlayerStrategyOne("Gabriella", hand4);
    Player player5 = new PlayerStrategyOne("Nicolas", hand5);


    @Test
    public void jockerTest(){
        String color = playerOfStrategyOne.getColorOfJocker(centerRow);
        assertEquals("Bleu", color);
    }
    @Test 
    public void getColorOfJockerTest(){
        String color = playerOfStrategyOne1.getColorOfJocker(centerRow);
        assertEquals("", color);
    }
    @Test
    public void testGetNbOfSameCards() {
        List <Integer> cards = new ArrayList<>();
        cards.add(0);
        cards.add(0);
        cards.add(3);
        int nb = player.getNbOfSameCards(cards);
        assertEquals(0, nb);
    }
    @Test
    public void testGetNbOfSameCards2() {
        List <Integer> cards = new ArrayList<>();
        cards.add(3);
        cards.add(0);
        cards.add(3);
        int nb = player.getNbOfSameCards(cards);
        assertEquals(2, nb);
    }

    @Test
    public void matchValueTest(){
        Card card = player.matchValue(new Card("Jaune", 4));
        assertEquals("Rouge", card.getCouleur());
        assertEquals(4, card.getValeur()) ;
    }
    @Test
    public void matchValueTest1(){
        Card card = player.matchValue(new Card("Jaune", 0));
        assertEquals(null, card);
       
    }
    @Test
    public void matchColorTest(){
        Card card = player.matchColor("Rouge");
        assertEquals("Rouge", card.getCouleur());
        assertEquals(4, card.getValeur()) ;
    }
    @Test
    public void matchColorTest2(){
        Card card = player.matchColor("Bleu");
        assertEquals(null, card);
    }
    @Test
    public void playARoundTest() {
        player.playARound(centerRow);
        Map <String, List<Integer>> handPlayer = player.getHand();
        List<Integer> values = handPlayer.get("Vert");
        assertEquals(2, values.size());
    }

    @Test
    public void playARoundTest1() {
        player.playARound(centerRow1);
        Map <String, List<Integer>> handPlayer = player.getHand();
        List<Integer> values = handPlayer.get("Vert");
        assertEquals(1, values.size());
    }
    @Test
    public void playARoundTest2() {
        player2.playARound(centerRow);
        Map <String, List<Integer>> handPlayer = player2.getHand();
        assertEquals(2, handPlayer.size());
    }

    @Test
    public void playARoundTest3() {
        player3.playARound(centerRow);
        Map <String, List<Integer>> handPlayer = player3.getHand();
        assertEquals(1, handPlayer.size());
    }

    @Test
    public void playARoundTest4() {
        player4.playARound(centerRow);
        Map <String, List<Integer>> handPlayer = player4.getHand();
        int nbOfCards = 0;
        boolean win = player4.win();
        for (String color : handPlayer.keySet()){
            nbOfCards += handPlayer.get(color).size();
        }
        assertEquals(2, nbOfCards);
        assertFalse(win);
    }
    @Test
    public void playARoundTest5() {
        player5.playARound(centerRow);
        assertEquals(3, centerRow[0].getValeur());

    }

    @Test
    public void playARoundTest6() {
        player.playARound(centerRow3);
        assertEquals(0, centerRow3[0].getValeur());
    }

    @Test
    public void winTest() {
        player4.playARound(centerRow2);
        boolean win = player4.win();
        assertTrue(win);
        
    }    
}

