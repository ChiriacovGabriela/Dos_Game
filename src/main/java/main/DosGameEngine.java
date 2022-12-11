package main;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public abstract class DosGameEngine {
    
    protected static Deck deck = new Deck(); 
    protected abstract Set <String> getInitialPlayers();
    protected Card [] centerRow = Deck.centerRow();
    
    public void play(){

        int count = 0; 
        for(String playerName : getInitialPlayers()){
            Card[] hand = deck.newHand();
            if(count %2 == 0){
                giveCardsToPlayerForStrategyOne(playerName, hand, count);
            }else{
                giveCardsToPlayerForStrategyTwo(playerName, hand, count);
            }
            
            count++;
        }
    
        final Queue<Player> players = new LinkedList<>();

        Player[] allPlayers = getPlayerCards();
        for(Player player : allPlayers ){
            players.add(player);
        }

        System.out.println("Rangé Centrale initial est : ");
        for(Card card : centerRow){
            System.out.println(card.getCouleur()+" "+ card.getValeur());
        } 

        boolean win = false;
        while(!win && !Deck.isCardsEmpty()){
            
            Player currentPlayer = players.poll();
            players.offer(currentPlayer);

                System.out.println(currentPlayer.getName() + " joue");
                currentPlayer.playARound(centerRow);

                System.out.println("Rangé Centrale: ");

                for(Card card : centerRow){
                    System.out.println(card.getCouleur()+" "+ card.getValeur());
                } 

                if (currentPlayer.win()){
                    System.out.println(currentPlayer.getName() + " a gagné");
                    win = true;
                }
            }
        }

    protected abstract void giveCardsToPlayerForStrategyOne(String playerName, Card[] hand, int index);
    protected abstract void giveCardsToPlayerForStrategyTwo(String playerName, Card[] hand, int index);
    protected abstract Player[] getPlayerCards() ;
}

