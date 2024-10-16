package irrgarten;

/**
 *
 * @author Mangel
 */

public class Monster {
    static final int INVALID_POS=-1;
    private static final int INITIAL_HEALTH=5;
    private String name;
    private float strength;
    private float intelligence;
    private float health;
    private int row;
    private int col;
    
    public Monster(String n, float intel, float str){
        name=n;
        intelligence=intel;
        strength=str;
        row=INVALID_POS;
        col=INVALID_POS;
        health=(float)INITIAL_HEALTH;
    }
    public boolean dead(){
        return (health<=0);
    }
    public float attack(){
        return Dice.intensity(strength);
    }
    public void setPos(int r, int c){
        row=r;
        col=c;
    }
    public String toString(){
        return "M[ name: "+name+", intelligence: "+intelligence+", stregth: "+strength+", row: "+row+", col: "+col+", health: "+health+"]\n";
    }
    public void gotWounded(){
        health--;
    }
    /*public boolean defend(float receivedAttack){
        
    }*/
}
