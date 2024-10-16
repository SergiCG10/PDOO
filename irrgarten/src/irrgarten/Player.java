/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

import java.util.ArrayList;
/**
 *
 * @author usuario
 */
public class Player {
    private static int MAX_WEAPONS = 2;
    private static int MAX_SHIELDS = 3;
    private static int INITIAL_HEALTH = 3;
    private static int HITS2LOSE = 3;
    
    private String name;
    private char number;
    private float intelligence;
    private float strength;
    private float health;
    private int row;
    private int col;
    private int consecutiveHits = 0;
    
    private ArrayList<Weapon> weapons= new ArrayList<>(); 
    private ArrayList<Shield> shields= new ArrayList<>();
    
    Player(char nmb, float howsmart, float howstrong){
        name = "Player#"+nmb;
        number = nmb;
        intelligence = howsmart;
        strength = howstrong;
        health = (float)INITIAL_HEALTH;
    }
    
    public void resurrect(){
        
        weapons.removeAll(weapons);
        shields.removeAll(shields);
        health = (float)INITIAL_HEALTH;
        resetHits();
        
    }
    
    public int getRow(){
         return row;
    }
    
    public int getCol(){
        return col;
    }
    
    public char getNumber(){
        return number;
    }
    
    public void setPos(int r, int c){
        row = r;
        col = c;
    }
    
    public boolean dead(){
        return health <= 0;
    }
    
    //IMPLEMENTACION P3
    //public Directions move(Directions direction, Directions validMoves[] )
    
    public float attack(){
        return strength + sumWeapons();
    }
    
    public boolean defend(float receivedAttack){
        return manageHit(receivedAttack);
    }
    
    //IMPLEMENTACION P3
    //public void receiveReward()
    
    public String toString(){
        String info = name + "\n";
        info += "\nIntelligence: " + intelligence;
        info += "\nStregth: " + strength;
        info += "\nHealth: " + health;
        info += "\nPosition: (" + row + ", " + col + ")";
        info += "\n\nWepons:\n";
        for(int i = 0; i <weapons.size() ; i++){
            info += weapons.get(i).toString();
        }
        info += "\nShields:\n\n";
        for(int i = 0; i <shields.size() ; i++){
            info += shields.get(i).toString();
        }
        
        return info;
    }
    
    //IMPLEMENTACION P3
    //private void receiveWeapon(Weapon w)
    
    //IMPLEMENTACION P3
    //private void receiveShield(Shield s)
    
    private Weapon newWeapon(){
        return new Weapon(Dice.weaponPower(), Dice.usesLeft());
    }
    
    private Shield newShield(){
        return new Shield(Dice.shieldPower() , Dice.usesLeft());
    }
    
    private float sumWeapons(){
        float totalAttack = 0f;
        for(int i = 0; i < weapons.size(); i++){
            totalAttack += weapons.get(i).attack();
        }
        return totalAttack;
    }
    
    private float sumShields(){
        float totalDefense = 0f;
        for(int i = 0; i < shields.size(); i++){
            totalDefense += shields.get(i).protect();
        }
        return totalDefense;
    }
    
    private float defensiveEnergy(){
        return intelligence + sumShields();
    }  
    
    //IMPLEMENTACION P3
    private boolean manageHit(float receivedAttack){
        return false;
    }
    
    private void resetHits(){
        consecutiveHits = 0;
    }
    
    private void gotWounded(){
        health--;
    }
    
    private void incConcecutiveHits(){
        consecutiveHits++;
    }
}
