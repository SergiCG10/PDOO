/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

/**
 *
 * @author usuario
 */
public class ShieldCardDeck extends CardDeck<Shield>{
    static int numCards = 15;
    
    protected void addCards(){
        Shield shieldCard;
        int cont = 0;
        while(cont < numCards){
            shieldCard = new Shield(Dice.randomIntelligence(), Dice.usesLeft());
            this.addCard(shieldCard);
            cont++;
        }
    }
}
