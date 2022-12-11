package main;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;

public class LocalDosGameTest {
    
    LocalDosGame localDosGametest = new LocalDosGame(Set.of("Joueur1", "Joueur2"));

    @Test
    public void getPlayerCardsTest(){
        Player [] players = localDosGametest.getPlayerCards();
        assertEquals(2, players.length);
    }










    
}
