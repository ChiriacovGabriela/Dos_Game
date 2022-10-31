package main;
import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    public static ArrayList <Card> cards = new ArrayList <Card>() ;

    public Deck(){
        generateDeck(); // initialisation du tous les combinaisons possibles des cartes
    }

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
            cards.add(new Card(null,2));
        }
       Collections.shuffle(cards);
       
    }

    public static Card[] getCards(int n){
        Card[] offertsCards = new Card[n];
        for(int i = 0; i<n; i++) {
            int positionCurent = cards.size() - (i + 1);
            offertsCards[i] = cards.get(positionCurent);

            cards.remove(positionCurent);

        }
        return offertsCards;
    } 

    public static Card[] newHand(){
        //use a constant here
        Card[] hand = new Card[7];
        hand = getCards(7);
        return hand;
    }


    public  static Card [] centerRow(){
        Card [] centerRow = new Card[2];
        centerRow = getCards(2);
        return centerRow;

    }
}
    

