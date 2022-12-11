package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public abstract class Player{
    
    protected static final int VALUE_OF_JOCKER = 0;
    protected static final int VALUE_OF_JOCKER_DOS = 2;
    protected static final int MINIMUM_NB_CARDS_IN_LARGEST_COMBINATION = 2;

    protected int valueSameCard;
    private String name;

    public Map <String, List<Integer>> handPlayer = new HashMap<>();

    public abstract Card matchValue(Card carteRangeCentral);

    public abstract Card matchColor (String color );

    public abstract void playARound(Card [] centerRow);

    public abstract int getNbOfSameCards(List <Integer> cards);
    

    protected Player( String theName, Card[] hand){
        this.name = theName;  
        arrange(hand); 
    }
    
    public String getName(){
        return this.name;
    }

    
    private void arrange( Card[] hand) {

        for (int i=0; i<hand.length; i++ ){
            if (handPlayer.containsKey(hand[i].getCouleur())){
                handPlayer.get(hand[i].getCouleur()).add(hand[i].getValeur());
            }else{
                List <Integer> tab = new ArrayList<>();
                tab.add(hand[i].getValeur());
                handPlayer.put(hand[i].getCouleur(),tab);

            }
        }
        
    }

    public Map <String, List<Integer>> getHand(){
        return handPlayer ;
    }


    public Card match (Card [] centerRow ){
        
        String color = "";
        Card card = null;
        
        for (Card carteRangeCentral : centerRow ){  
            color = carteRangeCentral.getCouleur();      
            if (handPlayer.containsKey(color)){
                card = matchColor(color);
                if (card != null){return card;}   
            }
            if (isNullCard(card)){
                card = matchValue(carteRangeCentral);
                if (card != null){
                    return card;
                }
            }
        }
        return card;
    }

    public boolean isNullCard(Card card){
        if(card == null){
            return true ;
        }
        return false;
    }

    public void takeOneCard() {
        
        Card[] one = Deck.getCards(1);
        arrange(one);
        return; 
    }

    public List<Card> doubleMatch(Card [] centerRow){
        List<Integer> valuesCardsForSum;
        List<Card> cardsDoubleMatch = new ArrayList<>();
        for (Card cardCenterRow : centerRow ){  
            String color = cardCenterRow.getCouleur();
            if (handPlayer.containsKey(color)){
                int sizeHand = handPlayer.get(color).size();
                if(sizeHand >= 2){ 
                    valuesCardsForSum = sommePairCards(handPlayer.get(color),cardCenterRow.getValeur());
                    if(!valuesCardsForSum.isEmpty()){
                        Card card1 = new Card(color, valuesCardsForSum.get(0) );
                        Card card2 = new Card(color, valuesCardsForSum.get(1) );
                        cardsDoubleMatch.add(card1);
                        cardsDoubleMatch.add(card2);
                        cardsDoubleMatch.add(cardCenterRow );

                        return cardsDoubleMatch;
                    }
                }
            }
        }
        return cardsDoubleMatch;
    }

    public List<Integer> sommePairCards(List<Integer> valuesCards, int x){
        List<Integer> valuesCardsForSum = new ArrayList<>();
        for (int i = 0; i < valuesCards.size(); i++){
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

    public List<Card> findLargestCombination(Card [] centerRow) {
        List<Card> listeCardsLargestCombination = new ArrayList<Card>();
        int maxCombination = 0;
        int value = 0;
        String color = "";
        Set <String> colors = handPlayer.keySet();
        for(String currentColor : colors) {
            if(handPlayer.get(currentColor).size() >= MINIMUM_NB_CARDS_IN_LARGEST_COMBINATION && currentColor != null) {
                int sameCards = getNbOfSameCards(handPlayer.get(currentColor));
                for(Card cardCenterRow : centerRow) {
                    if((cardCenterRow.getValeur() == valueSameCard || currentColor.equals(cardCenterRow.getCouleur()))
                        && maxCombination < sameCards){
                            maxCombination = sameCards;
                            value = valueSameCard;
                            color = currentColor;
                           
                        }}}
        }
        while(maxCombination-- != 0)
        {
            listeCardsLargestCombination.add(new Card(color, value));
        }
      return listeCardsLargestCombination;
    }

    public boolean isJockerDos(){
        if (handPlayer.containsKey(null)){
            return true;
        }
        return false;
    }

    public Card choseCardAfterJockerDos() {
        String color = "";
        int value = 0;
        int maxSize = 0;
        Set <String> colors = handPlayer.keySet();
        for(String currentColor : colors) {
            if(currentColor != null && maxSize < handPlayer.get(currentColor).size()) {
                maxSize = handPlayer.get(currentColor).size();
                color = currentColor;
                value = handPlayer.get(currentColor).get(0);
            }    
        }
        return new Card(color, value);
    }

    public void removeCards(List <Card> cardsToRemove){

        for(int i = 0; i < cardsToRemove.size(); i++ ){
            Card card = cardsToRemove.get(i);
            List<Integer> valuesOfCards = handPlayer.get(card.getCouleur());

            for (int j = 0; j < valuesOfCards.size(); j++){
                if (valuesOfCards.get(j) == card.getValeur()){
                    valuesOfCards.remove(j);
                    break;
                }
            }
            if(valuesOfCards.isEmpty()){
                handPlayer.remove(card.getCouleur());

            }
        }
    }

   
    public void changeCentralRow(Card card, Card [] centerRow) {
        for(int i = 0; i < centerRow.length; i++) {
                if(centerRow[i].getValeur()==card.getValeur() || Objects.equals(centerRow[i].getCouleur(), card.getCouleur()))
                {
                    centerRow[i] = card;
                    return ;
                }  
            }
            return ;  
    }

    public void changeCentralRow(Card card, Card [] centerRow, Card cardCR) {
        for (int i = 0; i < centerRow.length; i++){
            if (centerRow[i].equals(cardCR)){
                    centerRow[i] = card;
                }
            }
        return ;
    }
    
    public boolean win(){
        if(handPlayer.isEmpty()){
            return true;
        }
        return false;
    }

    protected void makeAMove(Card [] centerRow, List <Card> cardsToRemove, Card... cards) {

        if(cards.length == 1) {
            changeCentralRow(cards[0], centerRow); 
        }
        else {
            changeCentralRow(cards[0], centerRow, cards[1]);
        }
    
        removeCards(cardsToRemove);
    
    }

    protected void makeAMoveWithJockerDos(Card [] centerRow){
            Card card = new Card(null , VALUE_OF_JOCKER_DOS);
            List <Card> cardsToRemove = new ArrayList<>();
            cardsToRemove.add(card);
            if(handPlayer.isEmpty() || (handPlayer.size() == 1 && handPlayer.containsKey(null)) ) {
                takeOneCard();
            }
            Card cardAfterJockerDos = choseCardAfterJockerDos();
            String colorOfJockerDos = cardAfterJockerDos.getCouleur();
            Card cardJockerDos = new Card(colorOfJockerDos, VALUE_OF_JOCKER_DOS);
            centerRow[1] = cardJockerDos;  
            removeCards(cardsToRemove);

    }

}
