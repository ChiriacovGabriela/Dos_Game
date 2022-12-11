package main;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Represents a Deck of cards
 */    

public class Deck {

    private static ArrayList <Card> cards = new ArrayList <>();
    private static final int NB_OF_CARDS_INITIAL_HAND = 7;
    private static final int NB_OF_CARDS_CENTER_ROW = 2;
    private static final int VALUE_OF_JOCKER_DOS = 2;
   
    public Deck(){
        generateDeck(); 
    }
    
    /**
     * create all game cards with their color and value
     */
    private static void generateDeck(){
        
        String [] couleurs = {"Vert", "Rouge", "Jaune","Bleu"};
        for (String c : couleurs ){
            for (int i = 0; i < 4; i++){
                cards.add(new Card(c, 1)) ;
                cards.add(new Card(c,3));
            if(i < 3){
                cards.add(new Card(c, 4));
                cards.add(new Card(c,5));
            }
            if(i < 2){
                cards.add(new Card(c, 6));
                cards.add(new Card(c,7));
                cards.add(new Card(c,8));
                cards.add(new Card(c,9));
                cards.add(new Card(c,10));
                cards.add(new Card(c,0));
            }
        }
            cards.add(new Card(null,VALUE_OF_JOCKER_DOS));
        }
       Collections.shuffle(cards);
       
    }
    
    /**
     * create an array of cards
     * @param n the size of the array
     * @return an array of cards of length n
     */

    public static Card[] getCards(int n){
        Card[] offertsCards = new Card[n];
        for(int i = 0; i<n; i++) {
            int positionCurent = cards.size() - (i + 1);
            offertsCards[i] = cards.get(positionCurent);

            cards.remove(positionCurent);
        }
        return offertsCards;
    } 
    /**
     * create an array(hand) of cards
     * @return an array of cards with length 7 
     */

    public Card[] newHand(){
        Card[] hand = getCards(NB_OF_CARDS_INITIAL_HAND);
        return hand;
    }

    /**
     * create an array(Center Row) of cards
     * @return an array of cards with length 2
     */
    public static Card [] centerRow(){
        Card [] centerRow = getCards(NB_OF_CARDS_CENTER_ROW );
        while((centerRow[0].getValeur() == VALUE_OF_JOCKER_DOS) || (centerRow[1].getValeur() == VALUE_OF_JOCKER_DOS)){
            Card [] anotherCardForCR = getCards(1);
            if((centerRow[0].getValeur() == VALUE_OF_JOCKER_DOS)){
                centerRow[0]=anotherCardForCR[0];
            }else{
                centerRow[1]=anotherCardForCR[0];
            }
        }
        return centerRow;
    }

    /**
     * return if there are no more cards and false if there are cards
     * @return a boolean 
     */
    public static boolean isCardsEmpty(){
        if(cards.isEmpty()){
            return true;
        }
        return false;
    }
}
    

