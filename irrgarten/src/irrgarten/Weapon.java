
package irrgarten;


/**
 *
 * @author Faraon Love Shady (si no me acuerdo de quitar esto soy gay)
 */
public class Weapon {
    
    
    private float power;
    private int uses;

    
    public Weapon(float p, int us){
        power=p;
        uses=us;
    }
    
    public float attack(){
        float damage=0f;
        if(uses>0){
            damage=power;
            uses--;
        }
        
        return damage;
    }
    public String toString(){
        return "W["+power+", "+uses+"]\n";
    }
}
