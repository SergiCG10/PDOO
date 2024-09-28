package irrgarten;
import java.util.Random;

/**
 *
 * @author Mangel y Sergio Calvo
 */
public class Dice {
    private static int MAX_USES=5;
    private static float MAX_INTELLIGENCE=10f;
    private static float MAX_STRENGTH=10f;
    private static float RESURRECT_PROB=0.3f;
    private static int WEAPONS_REWARD=2;
    private static int SHIELDS_REWARD=3;
    private static int HEALTH_REWARD=5;
    private static int MAX_ATTACK=3;
    private static int MAX_SHIELD=2;
    private Random generator = new Random();
    
    public int randomPos(int max){
        return generator.nextInt(max);    
    }
    public int whoStarts(int nplayers){
        return generator.nextInt(nplayers);
    }
    public float randomIntelligence(){
        float intelligence=0f;
        int random_int=generator.nextInt(1001);
        intelligence=(float)(random_int)/100;
        //System.out.println(intelligence);
        return intelligence;
    }
    public float randomStrength(){
        float strength=0f;
        int random_int=generator.nextInt(1001);
        strength=(float)(random_int)/100;
        return strength;
    }
    public boolean resurrectPlayer(){
        boolean resurrect=false; 
        float prob=generator.nextFloat();
            if(prob<=RESURRECT_PROB)
                resurrect=true;
        return resurrect;
    }
    public int weaponsReward(){
        return generator.nextInt(WEAPONS_REWARD+1);
    }
    public int shieldsReward(){
        return generator.nextInt(SHIELDS_REWARD+1);
    }
    public int healthReward(){
        return generator.nextInt(HEALTH_REWARD+1);
    }
}
    
