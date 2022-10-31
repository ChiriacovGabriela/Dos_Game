package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Player{
    private static final int VALUE_OF_JOCKER = 0;
    private static final int VALUE_OF_JOCKER_DOS = 2;
    public static int valueSameCard;
    protected final Card[] hand;
    protected  static Card[] centerRow;

    public static Map <String, ArrayList<Integer>> handPlayer = new HashMap<>();

    public Player(Card[] hand) {
        this.hand = hand;
    }

    public static void arrange( Card[] hand) {
        for (int i=0; i<hand.length; i++ ){
            if (handPlayer.containsKey(hand[i].getCouleur())){
                handPlayer.get(hand[i].getCouleur()).add(hand[i].getValeur());
            }else{
                ArrayList <Integer > tab = new ArrayList<>();
                tab.add(hand[i].getValeur());
                handPlayer.put(hand[i].getCouleur(),tab);

            }
        }
    }

    public static void getOneCard() {

        Card[] one = Deck.getCards(1);
        arrange(one);

    }

    public static Card isMatch (){
        String color = "";
        int value = 0;
        Card card = null;
        
        for (Card carteRangeCentral :  centerRow ){  
            color = carteRangeCentral.getCouleur();      
            if (handPlayer.containsKey(color)){
                value = handPlayer.get(color).get(0);
                card = new Card(color, value);
                return card;
             }else{
                for(String key : handPlayer.keySet()){
                    ArrayList <Integer> valuesTab = handPlayer.get(key);
                    for (int currentValue : valuesTab ){
                        if (currentValue == carteRangeCentral.getValeur()){
                            value = currentValue;
                            color=key;
                            card = new Card(color, value);
                            return card;
                    }
                }
            }       
         }
    }
        return card;
    }

    public static Card isDoubleMatch(){
        String color = "";
        int value = 0;
        Card card = null;
        for (Card carteCenterRow : centerRow ){  
            color = carteCenterRow.getCouleur();
            if (handPlayer.containsKey(color)){
                int sizeHand = handPlayer.get(color).size();
                if(sizeHand >= 2){ 
                    value = sommePairCards(handPlayer.get(color),carteCenterRow.getValeur());
                    if(value != 0){
                        card = new Card(color, value);
                        return card;
                    }
                }
            }
        
    }
        return card;
    }

    private static int sommePairCards(List<Integer> valuesCards, int x){
        
        for (int i=0; i < valuesCards.size(); i++){
            if (valuesCards.get(i) != VALUE_OF_JOCKER && valuesCards.get(i)!= VALUE_OF_JOCKER_DOS) {
                for(int j = i+1; j < valuesCards.size(); j++) {
                    if (valuesCards.get(j) != VALUE_OF_JOCKER && valuesCards.get(j) != VALUE_OF_JOCKER_DOS){
                        if (valuesCards.get(i) + valuesCards.get(j) == x){    
                             return valuesCards.get(i);
                    }
                }
            }
        }
    }
        return 0;
    }

    public static boolean isJockerDos(){
        if (handPlayer.containsKey(null)){
            return true;
        }
        return false;
    }

    public static String isJocker(){
        for(Card c : centerRow) {
            String color = c.getCouleur();
            if (handPlayer.containsKey(color)){
                ArrayList<Integer> val = handPlayer.get(color);
                for (int j : val){
                    if (j == 0){
                        return color;
                    }
                }
            }
        }
        return "";

    }


    public static Card choseCardAfterJockerDos() {
        String color = "";
        int value = 0;
        int m = 0;
        for(String c : handPlayer.keySet()) {
            if(m < handPlayer.get(c).size()) {
                m = handPlayer.get(c).size();
                color = c;
                value = handPlayer.get(c).get(0);
            }    
        }
        Card card = new Card(color, value);
        return card;
    }

    public static int sameCards(ArrayList <Integer> cards) {
        int max = 0;
        
        for(int i = 0; i < cards.size(); i++) {
            int nbOfSameCards = 1; 
            for (int j = i+1; j < cards.size(); j++) {
                if(cards.get(i) == cards.get(j)) {
                    nbOfSameCards++;
                } 

            }
            if (max < nbOfSameCards ){
                max = nbOfSameCards;
                valueSameCard = cards.get(i) ;
            }
            
        } 
        if(max >= 2){
            return max;
        }
    
    return 0;
    }
   
    public static Card findLargestCombination() {
        int maxCombination = 0;
        int value = 0;
        String color="";
        for(String currentColor : handPlayer.keySet()) {
            if(handPlayer.get(currentColor).size() >= 2) {
                int sameCards = sameCards(handPlayer.get(currentColor));
                for(Card cardCenterRow : centerRow) {
                    if(cardCenterRow .getValeur() == valueSameCard || currentColor.equals(cardCenterRow.getCouleur())){
                        if(maxCombination < sameCards) {
                            maxCombination = sameCards;
                            value = valueSameCard;
                            color = currentColor;
                           
                        }}}}
        }
        if(maxCombination != 0)
        {
            Card card = new Card(color, value);
            return card;
        }
      return null;
    }
    public static void removeCard(Card card){
        ArrayList<Integer> tab = handPlayer.get(card.getCouleur());
        for (int i =0; i<tab.size(); i++){
            if (tab.get(i) == card.getValeur()){
                tab.remove(i);
            }
            if(tab.size() == 0){
                handPlayer.remove(card.getCouleur());

            }
        }
    }

    public static void changeCentralRow(Card card) {
        for(int i = 0; i < centerRow.length; i++) {
            if(centerRow[i].getValeur()==card.getValeur() || centerRow[i].getCouleur().equals(card.getCouleur()))
            {
                centerRow[i] = card;
                return ;
            }  
        }
        return ;
        
    }

    public static void test() {
        Card card = null;
        String color = "";
        

        if((card = isMatch()) != null){
            if((card = findLargestCombination()) != null){
                changeCentralRow(card);
                removeCard(card);
            }
            else if((card = isDoubleMatch()) != null) {
                changeCentralRow(card);
                removeCard(card);
            }
            else if((color = isJocker()) != "") {
                card = new Card(color, 0);
                changeCentralRow(card);
                removeCard(card);
            } 
            else if(isJockerDos()) {
                card = new Card(null , 2);
                removeCard(card);
                card = choseCardAfterJockerDos();
                changeCentralRow(card);
                removeCard(card);
            }
        }
        else {
            getOneCard();
        }
    }

    public static void main(String[] args){
        Deck deck = new Deck();
        
        Card[] hand = new Card[7];
        hand = Deck.newHand();
        System.out.println("Tes Cartes :");
            for(int i=0; i<hand.length; i++){
                
                System.out.println(hand[i].getCouleur()+" "+ hand[i].getValeur());
            }
            arrange(hand);

            
            centerRow = deck.getCards(2);
            System.out.println("Clés: " + handPlayer.keySet());
            System.out.println("Values: " + handPlayer.values());
            System.out.println("Cartes sur la table :");
            for(int i=0; i<centerRow .length; i++){
                
                System.out.println(centerRow[i].getCouleur()+" "+ centerRow[i].getValeur());
            } 

            test();
            
            System.out.println("Clés: " + handPlayer.keySet());
            System.out.println("Values: " + handPlayer.values());    
    
        }
}