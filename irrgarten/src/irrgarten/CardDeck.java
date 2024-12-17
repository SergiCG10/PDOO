/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author usuario
 * @param <T>
 */
abstract public class CardDeck<T>{
    private ArrayList<T> cardDeck;
    
    public CardDeck(){
        cardDeck = new ArrayList<>();
    }
    
    abstract protected void addCards();
    
    protected void addCard(T card){
        cardDeck.add(card);
    }
    
    public T nextCard(){
        if(cardDeck.size() == 0 ){
            this.addCards();
            Collections.shuffle(cardDeck);
        }
            
        T card = cardDeck.get(0);
        cardDeck.remove(0);
        return card;
    }
}
