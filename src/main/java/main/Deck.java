package main;
import java.util.ArrayList;
import java.util.Collections;

/** */    
public class Deck {

    private static ArrayList <Card> cards = new ArrayList <Card>() ;
    private static Card [] centerRow ;
    private static final int NB_OF_CARDS_INITIAL_HAND = 7;
    private static final int NB_OF_CARDS_CENTER_ROW = 2;
    private static final int VALUE_OF_JOCKER_DOS = 2;

    
    public Deck(){
        generateDeck(); // initialisation du tous les combinaisons possibles des cartes 
    }
    /**
     * 
     */
    public static void generateDeck(){
        
        String [] couleurs = {"V", "R", "G","A"};
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
     * 
     * @param n
     * @return
     */

    public static Card[] getCards(int n){
        Card[] offertsCards = new Card[n];
        for(int i = 0; i<n; i++) {
            int positionCurent = cards.size() - (i + 1);
            offertsCards[i] = cards.get(positionCurent);

            cards.remove(positionCurent);
            System.out.println(cards.size()+" lunigimea cards");

        }
        return offertsCards;
    } 
    /**
     * 
     * @return
     */

    public Card[] newHand(){
        Card[] hand = new Card[NB_OF_CARDS_INITIAL_HAND];
        hand = getCards(NB_OF_CARDS_INITIAL_HAND);
        return hand;
    }
    /**
     * 
     * @return
     */

    public  static Card [] centerRow(){
        centerRow = getCards(NB_OF_CARDS_CENTER_ROW );
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
     * 
     * @return
     */
    public static boolean isCardsEmpty(){
        if(cards.isEmpty()){

            return true;
        }
        return false;

    }
}
    

