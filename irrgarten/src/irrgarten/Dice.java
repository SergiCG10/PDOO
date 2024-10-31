package irrgarten;
import java.util.Random;

 /**
 * Clase Dice. Representación de la clase dice con sus métodos y variables 
 * 
 * @author Miguel Ángel Luque Gómez
 * correo e.mangelluqg@go.ugr.es
 * 
 * @author Sergio Calvo González
 * correo e.sergiocg10@go.ugr.es
 * 
 */
public class Dice {     
    private static final int MAX_USES=5;              //Número máximo de usos de un objeto
    private static final float MAX_INTELLIGENCE=10f;  //Número máximo de inteligencia para un personaje
    private static final float MAX_STRENGTH=10f;      //Número máximo de fuerza para un personaje
    private static final float RESURRECT_PROB=0.3f;   //Probabilidad de resucitar de un personaje
    private static final int WEAPONS_REWARD=2;        //Número de armas máximas como recompensa
    private static final int SHIELDS_REWARD=3;        //Número de escudos máximos como recompensa
    private static final int HEALTH_REWARD=5;         //Número de vidas máximas como recompensa
    private static final int MAX_ATTACK=3;            //Número máximo de ataque para un arma
    private static final int MAX_SHIELD=2;            //Número máximo de protección para un escudo
    private static final Random generator = new Random();    //Variable generadora de números aleatorios   
    
    /**
     * Funcion randomPos. Devuelve un valor que muestra el valor de fila o 
     * de columna donde empezará el jugador
     * @param max Número de filas o de columnas del laberinto
     * @return valor entero aletorio en el intervalo [0, max)
     */
    public static int randomPos(int max){
        return generator.nextInt(max);    
    }
    
    /**
     * Funcion whoStars. Devuelve un número que muestra que jugador empieza.
     * @param nplayers Número de jugadores en la partida
     * @return Un número entero aleatorio en el intervalo [0, nplayers)
     */
    public static int whoStarts(int nplayers){
        return generator.nextInt(nplayers);
    }
    
    /**
     * Funcion randomIntelligence. Devuelve un valor aleatorio que mostrará 
     * la inteligencia del personaje
     * @return Un número aleatorio con coma flotante en el intervalo [0, MAX_INTELLIGENCE)
     */
    public static float randomIntelligence(){
        return generator.nextFloat()*MAX_INTELLIGENCE;
    }
    
    /**
     * Funcion randomStrength. Devuelve en valor aleatorio que mostrará 
     * la fuerza del personaje
     * @return Un número aleatorio con coma flotante en el intervalo [0, MAX_STRENGTH)
     */
    public static float randomStrength(){
        return generator.nextFloat()*MAX_STRENGTH;
    }
    
    /**
     * Funcion resurrectPlayer. Devuelve si el personaje debe de revivir.
     * La probabilidad se calcula de forma que si un float aleatorio es 
     * menor que la probabilidad de revivir, devuelve true, si no, false.
     * @return True si el personaje revive, false si no. 
     */
    public static boolean resurrectPlayer(){
        return generator.nextFloat() < RESURRECT_PROB;
    }
    
    /**
     * Funcion weaponsReward. Devuelve el número aleatorio de armas que recibe como recompensa
     * @return Valor entero aleatorio en el intervalo [0, WEAPONS_REWARD]
     */
    public static int weaponsReward(){
        return generator.nextInt(WEAPONS_REWARD+1);
    }
    
    /**
     * Funcion shieldsReward. Devuelve el número aleatorio de escudos que recibe como recompensa
     * @return Valor entero aleatorio en el intervalo [0, SHIELDS_REWARD]
     */
    public static int shieldsReward(){
        return generator.nextInt(SHIELDS_REWARD+1);
    }
    
    /**
     * Funcion healthReward. Devuelve el número aleatorio de vidas que recibe como recompensa
     * @return Valor entero aleatorio en el intervalo [0, HEALTH_REWARD]
     */
    public static int healthReward(){
        return generator.nextInt(HEALTH_REWARD+1);
    }
    
    /**
     * Funcion weaponPower. Devuelve un valor que representará el valor de daño de un arma
     * @return Valor con coma flotante aleatorio en el intervalo [0, MAX_ATTACK)
     */
    public static float weaponPower(){
        return generator.nextFloat()*MAX_ATTACK;
    }
    
    /**
     * Funcion weaponPower. Devuelve un valor que representará el valor de defensa de un escudo
     * @return Valor con coma flotante aleatorio en el intervalo [0, MAX_SHIELD)
     */
    public static float shieldPower(){
        return generator.nextFloat(MAX_SHIELD);
    }
    
    /**
     * Funcion usesLeft. Devuelve el número aleatorio de usos restantes de un arma o escudo
     * @return Número entero aleatorio en el intervalo [0, MAX_USES]
     */
    public static int usesLeft(){
        return generator.nextInt(MAX_USES+1);
    }
    
    /**
     * Funcion intensity. Devuelve un valor aleatorio que muestra el grado de intensidad de una acción
     * @param competence
     * @return Valor  aleatorio con coma flotante en el intervalo [0, competence)
     */
    public static float intensity(float competence){
        return generator.nextFloat()*competence;
    }
    
    /**
     * Funcion discardElement. Muestra true si se debe de descartar o no un objeto. 
     *  Se calcula si se decarta de forma que :
     *  Si tiene el número máximo de usos, no se descartará, 
     *  Si tiene 0 se descartará de forma asegurada
     *  En cualquier otro caso, la probabilidad de que se descarte será 
     *  proporcionalmente inversa al número de usos que le quedan respecto 
     *  al número máximo de usos. 
     * @param usesLeft Número de usos restantes
     * @return True si se descarta, false si no
     */
    public static boolean discardElement(int usesLeft){
        if(usesLeft==MAX_USES){
           return false;
        }
        if(usesLeft==0){
            return true;
        }
        double discard_Prob=1.0-((double)usesLeft/MAX_USES);
        return generator.nextDouble()<discard_Prob;
    }
}
    
