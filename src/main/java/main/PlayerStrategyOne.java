package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class PlayerStrategyOne extends Player {

    public PlayerStrategyOne(String theName, Card[] hand) {
        super(theName, hand);
    }

    @Override
    public Card matchColor (String color){
        Card card = null;
        List<Integer> values = handPlayer.get(color);
        values = values.stream().filter(v -> v != VALUE_OF_JOCKER ).collect(Collectors.toList());
        if(!values.isEmpty()){
            return (new Card(color, values.get(0)));
        }
        return card;
    }

    @Override
    public Card matchValue(Card carteRangeCentral) {
        Card card = null;
        Set<String> entrySet = handPlayer.keySet();
        for(String key : entrySet){
            if(key != null) {
                List <Integer> valuesTab = handPlayer.get(key);
                for ( int currentValue : valuesTab ){
                    if ((currentValue != VALUE_OF_JOCKER) && (currentValue == carteRangeCentral.getValeur())){
                        card = new Card(key, currentValue);
                        return card;
                    }
                }
            }
        }
        return card;
        
    }

    public String getColorOfJocker(Card [] centerRow){
        for(Card card : centerRow) {
            String color = card.getCouleur();
            if (handPlayer.containsKey(color)){
                List<Integer> valuesOfCards = handPlayer.get(color);
                valuesOfCards =  valuesOfCards .stream().filter(v -> v == 0).collect(Collectors.toList());
                if(valuesOfCards.size() >= 1 ){
                        return color;

                    }
                }
            }
        
        return "";

    }


    @Override
    public void playARound(Card [] centerRow) {
         
        List <Card> cardsToRemove = new ArrayList<>();
        Card card;
        if((card = match(centerRow)) != null){
            
            cardsToRemove = findLargestCombination(centerRow);

            if(!cardsToRemove.isEmpty()){
                makeAMove(centerRow, cardsToRemove, cardsToRemove.get(1));
                return;
            } else if (!doubleMatch(centerRow).isEmpty()) {

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

        String color = getColorOfJocker(centerRow);

        if(!"".equals(color)) {
            card = new Card(color, VALUE_OF_JOCKER);
            cardsToRemove.add(card);
            makeAMove(centerRow, cardsToRemove, card);
            return ;
        } else if(isJockerDos()) {
            card = new Card(null , VALUE_OF_JOCKER_DOS);
            cardsToRemove.add(card);
            if(handPlayer.isEmpty() || (handPlayer.size() == 1 && handPlayer.containsKey(null)) ) {
                takeOneCard();
            }
            Card cardAfterJockerDos = choseCardAfterJockerDos();
            String colorOfJockerDos = cardAfterJockerDos.getCouleur();
            Card cardJockerDos = new Card(colorOfJockerDos, VALUE_OF_JOCKER_DOS);
            centerRow[1] = cardJockerDos;  
            removeCards(cardsToRemove);
            return;
        }

        takeOneCard();
        return;   
    }

    @Override
    public int getNbOfSameCards(List <Integer> cards) {
        int max = 0;
        for(int i = 0; i < cards.size(); i++) {
            if(cards.get(i) != VALUE_OF_JOCKER_DOS && cards.get(i) != VALUE_OF_JOCKER ){
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
    
}

