package irrgarten;


/**
 * Clase Weapon. Implementación de la clase Weapon, de sus métodos y variables.
 * 
 * @author Miguel Ángel Luque Gómez
 * correo e.mangelluqg@go.ugr.es
 * 
 * @author Sergio Calvo González
 * correo e.sergiocg10@go.ugr.es
 * 
 */
public class Weapon {
    
    
    private float power;        //Valor de daño del arma
    private int uses;           //Número de usos disponibles del arma

     /**
     * Constructor por parámetro de la clase Weapon
     * @param p daño del arma a crear
     * @param us usos del arma a crear
     */
    public Weapon(float p, int us){
        power=p;
        uses=us;
    }
    
    /**
     * Función Attack. Devuelve el valor de daño del arma
     * y decrementa su uso en uno
     * @return Valor de ataque. 0 si no le quedan usos, power si no.
     */
    public float attack(){
        float damage=0f;
        if(uses>0){
            damage=power;
            uses--;
        }
        
        return damage;
    }
    
    /**
     * Funcion toString de la clase Weapon. Muestra los valores de la clase
     * en un string en el formato [power, uses].
     * @return String con la información de la clase
     */
    public String toString(){
        return "W["+power+", "+uses+"]\n";
    }
    
    /**
     * Funcion discard. Devuelve si se debe de descartar el arma.
     * @return True si se descarta, false si no.
     */
    public boolean discard(){
        Dice dice=new Dice();
        return dice.discardElement(uses);
    }
}
