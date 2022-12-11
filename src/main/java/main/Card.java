package main;



public class Card{
    protected String couleur;
    protected int valeur;
                
    public Card(String coul, int val)
    {
        this.couleur = coul;
        this.valeur = val;
    }
    
    public String getCouleur() {
        return this.couleur;
    }
    
    public int getValeur() {
        return this.valeur;
    }

   
 }

