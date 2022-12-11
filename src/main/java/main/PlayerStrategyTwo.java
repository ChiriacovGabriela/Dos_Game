package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class PlayerStrategyTwo extends Player {

    public PlayerStrategyTwo(String theName, Card[] hand) {
        super(theName, hand);
    }

    @Override
    public Card matchValue(Card carteRangeCentral) {
        Card card = null;
        Set<String> entrySet = handPlayer.keySet();
        for(String key : entrySet){
            if(key != null) {
                List <Integer> valuesTab = handPlayer.get(key);
                for ( int currentValue : valuesTab ){
                    if (currentValue == carteRangeCentral.getValeur()){
                        card = new Card(key, currentValue);
                        return card;
                    }
                }
            }
        }
        return card;      
    }

    @Override
    public Card matchColor(String color) {
        Card card = null;
        List<Integer> values = handPlayer.get(color);
        card = new Card(color, values.get(0));
        return card;
    }

    @Override
    public int getNbOfSameCards(List <Integer> cards) {
        int max = 0;
        for(int i = 0; i < cards.size(); i++) {
            if( cards.get(i) != VALUE_OF_JOCKER_DOS ){
                int nbOfSameCards = 1; 
                for (int j = i+1; j < cards.size(); j++) {
                    if(cards.get(i).equals(cards.get(j))) {
                        nbOfSameCards++;
                    } 
                }
            if (max < nbOfSameCards ){
                max = nbOfSameCards;
                valueSameCard = cards.get(i) ;
            }  
            } 
        }
        if(max >= 2){
            return max;
        }
    
    return 0;
    }

    @Override
    public void playARound(Card [] centerRow) {
         
        List <Card> cardsToRemove = new ArrayList<>();
        Card card;
        if((card = match(centerRow)) != null) {
            cardsToRemove = findLargestCombination(centerRow);
            if(!cardsToRemove.isEmpty()){
                makeAMove(centerRow, cardsToRemove, cardsToRemove.get(1));
                return;
            }
            else if(!doubleMatch(centerRow).isEmpty()) {
    
                List <Card> cardsDoubleMatch = doubleMatch(centerRow);
                cardsToRemove.add(cardsDoubleMatch.get(0));
                cardsToRemove.add(cardsDoubleMatch.get(1));
                Card cardRCDoubleMatch =  cardsDoubleMatch.get(2);
                makeAMove(centerRow, cardsToRemove, cardsToRemove.get(1), cardRCDoubleMatch);
                return;

            }else {
                cardsToRemove.add(card);
                makeAMove(centerRow, cardsToRemove, card);
                return;

            }
        }

        if(isJockerDos()) {
            makeAMoveWithJockerDos(centerRow);
            return;

        }
        
        takeOneCard();
        return;


    }
    
}

