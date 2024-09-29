package irrgarten;

/**
 *
 * @author Mangel y SergiCG10
 */
public class Shield {
    private float protection;
    private int uses;
    
    public Shield(float p, int us){
        protection=p;
        uses=us;
    }
    public float protect(){
        float defense=0f;
        if(uses>0){
            defense=protection;
            uses--;
        }
        return defense;
    }
    public String toString(){
        return "S["+protection+", "+uses+"]\n";
    }
    public boolean discard(){
        Dice dice=new Dice();
        return dice.discardElement(uses);
    }
}
