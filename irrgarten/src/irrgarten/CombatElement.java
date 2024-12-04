package irrgarten;

/**
 *
 * @author usuario
 */
abstract public class CombatElement {
    private float effect;       //Valor de defensa del escudo
    private int uses;               //Número de usos disponibles para el escudo
    
    //P5
    public CombatElement(float e, int us){
        effect=e;
        uses=us;
    }
    
    //P5
    protected float produceEffect(){
        float e=0f;
        if(uses>0){
            e=effect;
            uses--;
        }
        return e;
    }
    
    //P5
    public String toString(){
        return "["+effect+", "+uses+"]\n";
    }
    
    //P5
    public boolean discard(){
        return Dice.discardElement(uses);
    }
}
