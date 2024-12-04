/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

/**
 *
 * @author usuario
 */
public class WeaponCardDeck extends CardDeck<Weapon>{
    static int numCards = 15;
    
    protected void addCards(){
        Weapon weaponCard;
        int cont = 0;
        while(cont < numCards){
            weaponCard = new Weapon(Dice.randomStrength() , Dice.usesLeft());
            this.addCard(weaponCard);
            cont++;
        }
    }
}
