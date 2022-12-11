package main;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DosGameEngineTest {
    DosGameEngine engine;
    Queue<String> players;
    Player [] playerCards; 

    @BeforeEach
    void setUp() {

        this.players = new LinkedList<>();
        this.players.addAll(Arrays.asList("Joueur1", "Joueur2", "Joueur3"));
        this.engine = new LocalDosGame(Set.of("Joueur1", "Joueur2", "Joueur3"));
        
        
    }

    @Test
    void getInitialPlayers() {
        assertTrue(engine.getInitialPlayers().containsAll(Set.of("Joueur1", "Joueur2", "Joueur3")));
    }
    @Test
    void giveCardsToPlayer()  {
        Card[] cards = {new Card("Bleu", 0), new Card("Rouge", 4),  new Card(null, 2),new Card("Vert", 1), new Card("Vert", 8), new Card("Vert", 5)};
        engine.giveCardsToPlayerForStrategyOne("Joueur1", cards, 0);
        playerCards = engine.getPlayerCards();
        Player player = playerCards[0] ;
        Map<String, List<Integer>> cardInHand = player.getHand();
        int nbOfCards = 0;
        for (String color : cardInHand .keySet()){
            nbOfCards += cardInHand.get(color).size();
        }
        assertEquals(6, nbOfCards);

    }

    
}
