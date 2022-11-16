package main;

public class Game {

    public static void main(String[] args) {
        Player p1 = new Player("Paul");
        Player p2 = new Player("Elio");

        Deck deck = new Deck(); 
        
        Card [] centerRow = Deck.centerRow();
        
        Card[] hand= deck.newHand();
      
        p1.arrange(hand);
        System.out.println("Player 1");
        for(Card card : hand){
            System.out.println(card.getCouleur()+" "+ card.getValeur());
        }
        Card[] hand2 = deck.newHand();
      
        p2.arrange(hand2);
        System.out.println("Player 2");
        for(Card card : hand2){
            System.out.println(card.getCouleur()+" "+ card.getValeur());
        }


        System.out.println("RC DEBUT: ");

            for(Card card : centerRow){
                
                System.out.println(card.getCouleur()+" "+ card.getValeur());
            } 





        boolean win = false;
        
        while(!win && !Deck.isCardsEmpty()){

            

            System.out.println("Player 1 ");

            p1.gameOfPlayer(centerRow );

            System.out.println("RC: ");

            for(Card card : centerRow){
                
                System.out.println(card.getCouleur()+" "+ card.getValeur());
            } 

            System.out.println("Player 2 ");

            p2.gameOfPlayer(centerRow );

            System.out.println("RC: ");

            for(Card card : centerRow){
                
                System.out.println(card.getCouleur()+" "+ card.getValeur());
            } 


         
            
            if (p1.win()){
                System.out.println("p1 a gagner");
                win = true;
            }else if(p2.win()){
                System.out.println("p2 a gagner");
                win = true;
            }

        }
    
}
}
