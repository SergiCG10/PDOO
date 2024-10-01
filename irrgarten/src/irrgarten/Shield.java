package irrgarten;

/**
 * Clase Shield. Implementación de la clase Shield, de sus métodos y variables.
 * 
 * @author Miguel Ángel Luque
 * @correo e.mangelluqg@go.ugr.es
 * 
 * @author Sergio Calvo González
 * @author e.sergiocg10@go.ugr.es
 * 
 */
public class Shield {
    private float protection;       //Valor de defensa del escudo
    private int uses;               //Número de usos disponibles para el escudo
    
    /**
     * Constructor por parámetro de la clase Shield
     * @param p defensa del escudo a crear
     * @param us usos del escudo a crear
     */
    public Shield(float p, int us){
        protection=p;
        uses=us;
    }
    
    /**
     * Función Protect. Devuelve el valor de protección del escudo
     * y decrementa su uso en uno
     * @return Valor de la defensa. 0 si no le quedan usos, protection si no.
     */
    public float protect(){
        float defense=0f;
        if(uses>0){
            defense=protection;
            uses--;
        }
        return defense;
    }
    
    /**
     * Funcion toString de la clase Shield. Muestra los valores de la clase
     * en un string en el formato [protection, uses].
     * @return String con la información de la clase
     */
    public String toString(){
        return "S["+protection+", "+uses+"]\n";
    }
    
    /**
     * Funcion discard. Devuelve si se debe de descartar el escudo.
     * @return True si se descarta, false si no.
     */
    public boolean discard(){
        Dice dice=new Dice();
        return dice.discardElement(uses);
    }
}
