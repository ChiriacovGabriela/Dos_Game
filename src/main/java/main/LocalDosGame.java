package main;

import java.util.Set;


public class LocalDosGame extends DosGameEngine{
    private final Set<String> initialPlayers;
    Player [] playerCards;  


    public LocalDosGame(Set<String> initialPlayers){
        this.initialPlayers = initialPlayers;
        int size =  this.initialPlayers.size();  
        playerCards = new Player [size];
    }

    @Override
    protected Set<String> getInitialPlayers() {
        return this.initialPlayers;
    }

    

    public static void main(String... args) {
        LocalDosGame localDosGame = new LocalDosGame(Set.of("Joueur 1", "Joueur 2"));
        localDosGame.play();
    }

    @Override
    protected void giveCardsToPlayerForStrategyOne(String playerName, Card[] hand, int index) {
        Player player = new PlayerStrategyOne(playerName,hand);
        playerCards[index] = player;
    }
    @Override
    protected void giveCardsToPlayerForStrategyTwo(String playerName, Card[] hand, int index) {
        Player player = new PlayerStrategyTwo(playerName,hand);
        playerCards[index] = player;
    }

    @Override
    public Player[] getPlayerCards() {
        return this.playerCards;
    }

}
