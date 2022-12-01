package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class Player{
    private static final int VALUE_OF_JOCKER = 0;
    private static final int VALUE_OF_JOCKER_DOS = 2;
    
    private int valueSameCard;
    
    private String name;
   
    public Map <String, List<Integer>> handPlayer = new HashMap<>();

    public Player( String theName){

        this.name = theName; 
    }

    public void arrange( Card[] hand) {
        
        for (int i=0; i<hand.length; i++ ){
            if (handPlayer.containsKey(hand[i].getCouleur())){
                handPlayer.get(hand[i].getCouleur()).add(hand[i].getValeur());
            }else{
                List <Integer > tab = new ArrayList<>();
                tab.add(hand[i].getValeur());
                handPlayer.put(hand[i].getCouleur(),tab);

            }
        }
    }

   
    public void takeOneCard() {

        Card[] one = Deck.getCards(1);
        arrange(one);

    }

    public Card match (Card [] centerRow){
        String color = "";
        Card card = null;
        
        for (Card carteRangeCentral : centerRow ){  
            color = carteRangeCentral.getCouleur();      
            if (handPlayer.containsKey(color)){
                List<Integer> values = handPlayer.get(color);
                card = new Card(color, values.get(0));
                return card;
                
            }else{
                Set<String> entrySet = handPlayer.keySet();
                for(String key : entrySet){
                    List <Integer> valuesTab = handPlayer.get(key);
                    for ( int currentValue : valuesTab ){
                        if (currentValue == carteRangeCentral.getValeur()){
                            card = new Card(key, currentValue);
                            return card;
                    }
                }
            }       
         }
    }
        return card;
    }

    public List<Card> doubleMatch(Card [] centerRow){
        String color = "";
        List<Integer> valuesCardsForSum;
        List<Card> cardsDoubleMatch = new ArrayList<>();
        for (Card carteCenterRow : centerRow ){  
            color = carteCenterRow.getCouleur();
            if (handPlayer.containsKey(color)){
                int sizeHand = handPlayer.get(color).size();
                if(sizeHand >= 2){ 
                    valuesCardsForSum = sommePairCards(handPlayer.get(color),carteCenterRow.getValeur());
                    if(!valuesCardsForSum.isEmpty()){
                        Card card1 = new Card(color, valuesCardsForSum.get(0) );
                        Card card2 = new Card(color, valuesCardsForSum.get(1) );
                        cardsDoubleMatch.add(card1);
                        cardsDoubleMatch.add(card2);
                        return cardsDoubleMatch;
                    }
                }
            }
        
    }
        return cardsDoubleMatch;
    }

    private List<Integer> sommePairCards(List<Integer> valuesCards, int x){
        List<Integer> valuesCardsForSum = new ArrayList<>();
        for (int i=0; i < valuesCards.size(); i++){
            if (valuesCards.get(i) != VALUE_OF_JOCKER && valuesCards.get(i)!= VALUE_OF_JOCKER_DOS) {
                for(int j = i+1; j < valuesCards.size(); j++) {
                    if (valuesCards.get(j) != VALUE_OF_JOCKER && 
                            valuesCards.get(j) != VALUE_OF_JOCKER_DOS && 
                                valuesCards.get(i) + valuesCards.get(j) == x){
                            valuesCardsForSum.add(valuesCards.get(i));
                            valuesCardsForSum.add(valuesCards.get(j));
                            return valuesCardsForSum;
                }
            }
        }
    }
        return valuesCardsForSum;
    }

    public boolean isJockerDos(){
        if (handPlayer.containsKey(null)){
            return true;
        }
        return false;
    }

    
    public String jocker(Card [] centerRow){
        for(Card c : centerRow) {
            String color = c.getCouleur();
            if (handPlayer.containsKey(color)){
                List<Integer> val = handPlayer.get(color);
                for (int j : val){
                    if (j == 0){
                        return color;
                    }
                }
            }
        }
        return "";

    }


    public Card choseCardAfterJockerDos() {
        String color = "";
        int value = 0;
        int m = 0;
        for(String c : handPlayer.keySet()) {
            
            if(c != null && m < handPlayer.get(c).size()) {
                m = handPlayer.get(c).size();
                color = c;
                value = handPlayer.get(c).get(0);
            }    
        }
        return new Card(color, value);
    }

    

    public int getNbOfSameCards(List <Integer> cards) {
        int max = 0;
        for(int i = 0; i < cards.size(); i++) {
            if(cards.get(i)!= VALUE_OF_JOCKER && cards.get(i)!= VALUE_OF_JOCKER_DOS){
                int nbOfSameCards = 1; 
                for (int j = i+1; j < cards.size(); j++) {
                    if(Objects.equals(cards.get(i), cards.get(j))) {
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
   
    public List<Card> findLargestCombination(Card [] centerRow) {
        List<Card> listeCardsLargestCombination = new ArrayList<Card>();
        int maxCombination = 0;
        int value = 0;
        String color = "";
        for(String currentColor : handPlayer.keySet()) {
            //use constant here instead of 2
            if(handPlayer.get(currentColor).size() >= 2 && currentColor != null) {
                System.out.println("culoarea curenta: " + currentColor);
                
                int sameCards = getNbOfSameCards(handPlayer.get(currentColor));
                for(Card cardCenterRow : centerRow) {
                    if(cardCenterRow .getValeur() == valueSameCard || currentColor.equals(cardCenterRow.getCouleur())){
                        if(maxCombination < sameCards) {
                            maxCombination = sameCards;
                            value = valueSameCard;
                            color = currentColor;
                           
                        }}}}
        }
        while(maxCombination-- != 0)
        {
            listeCardsLargestCombination.add(new Card(color, value));
        }
      return listeCardsLargestCombination;
    }

    public void removeCards(List <Card> cardsToRemove){
        for(int i = 0; i < cardsToRemove.size(); i++ ){
            Card card = cardsToRemove.get(i);
            List<Integer> valuesOfCards = handPlayer.get(card.getCouleur());
            for (int j = 0; j < valuesOfCards.size(); j++){
                if (valuesOfCards.get(j) == card.getValeur()){
                    valuesOfCards.remove(j);
            }
                if(valuesOfCards.isEmpty()){
                    handPlayer.remove(card.getCouleur());

            }
        }
    }
}

    public void changeCentralRow(Card card, Card [] centerRow) {
        for(int i = 0; i < centerRow.length; i++) {
            if(centerRow[i].getValeur()==card.getValeur() || centerRow[i].getCouleur().equals(card.getCouleur()))
            {
                centerRow[i] = card;
                return ;
            }  
        }
        return ;
        
    }
    
    public void gameOfPlayer(Card [] centerRow) {
        System.out.println("Clés: " + handPlayer.keySet());
        System.out.println("Values: " + handPlayer.values());  
        List <Card> cardsToRemove = new ArrayList<>();
        Card card;
        if((card = match(centerRow)) != null){
            
            cardsToRemove = findLargestCombination(centerRow);
            if(!cardsToRemove.isEmpty()){
                changeCentralRow(cardsToRemove.get(1), centerRow);
                removeCards(cardsToRemove);
                return;
            }
            else if(!doubleMatch(centerRow).isEmpty()) {
                cardsToRemove = doubleMatch( centerRow);
                changeCentralRow(cardsToRemove.get(1), centerRow);
                removeCards(cardsToRemove);
                return;
            }else {
                
                cardsToRemove.add(card);
                changeCentralRow(card,centerRow);
                removeCards(cardsToRemove);
                return;

            }
        }

        String color = jocker(centerRow);

        if(!"".equals(color)) {
            card = new Card(color, VALUE_OF_JOCKER);
            cardsToRemove.add(card);
            changeCentralRow(card, centerRow);
            removeCards(cardsToRemove);
            return ;
        } else if(isJockerDos()) {
            card = new Card(null , VALUE_OF_JOCKER_DOS);
            cardsToRemove.add(card);
            removeCards(cardsToRemove);
            if(handPlayer.isEmpty() || (handPlayer.size() == 1 && handPlayer.containsKey(null)) ){
                takeOneCard();
            }
            Card cardAfterJockerDos = choseCardAfterJockerDos();
            cardsToRemove.remove(card);
            cardsToRemove.add(cardAfterJockerDos);
            centerRow[1] = cardAfterJockerDos;    
            removeCards(cardsToRemove);
            return;
        }
        
        takeOneCard();
        return;

    }
    public boolean win(){
        if(handPlayer.isEmpty()){
            return true;
        }
        return false;

    }
}
