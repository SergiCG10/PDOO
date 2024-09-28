package irrgarten;

/**
 *
 * @author usuario
 */
public class Irrgarten {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Dice dice=new Dice();
        float intelligence=dice.randomIntelligence();
        while(intelligence!=10f)
        {
            intelligence=dice.randomIntelligence();
        }
            
    }
    
}
