package irrgarten;

/**
 * Clase Player. Esta clase contiene la implementación de la variable player y sus 
 * funciones básicas para la utilización de la clase
 * 
 * @author Miguel Ángel Luque Gómez
 * correo: e.mangelluqg@go.ugr.es
 * 
 * @author Sergio Calvo González
 * correo: e.sergiocg10@go.ugr.es
 */
public class Monster {
    private static final int INVALID_POS=-1;    //Variable que indica la posición inicial del monstruo
    private static final int INITIAL_HEALTH=5;  //Salud inicial del monstruo
    private String name;                        //Nombre del monstruo
    private float strength;                     //Fuerza del monstruo
    private float intelligence;                 //Inteligencia del monstruo
    private float health;                       //Vida actual del monstruo
    private int row;                            //Fila del monstruo
    private int col;                            //Columna del monstruo
    
    /**
     * Constructor de la clase Monster.
     * @param n nombre del monstruo
     * @param intel inteligencia del monstruo
     * @param str fuerza del monstruo
     */
    public Monster(String n, float intel, float str){
        name=n;
        intelligence=intel;
        strength=str;
        row=INVALID_POS;
        col=INVALID_POS;
        health=(float)INITIAL_HEALTH;
    }
    
    /**
     * Funcion dead. Devuelve si el monstruo está muerto o no
     * @return True si está muerto, false si no
     */
    public boolean dead(){
        return (health<=0);
    }
    
    /**
     * Funcion attack. Devuelve el ataque del monstruo
     * @return Valor aleatorio entre 0 y strength
     */
    public float attack(){
        return Dice.intensity(strength);
    }
    
    /**
     * Función setPos. Establece la posición del monstruo
     * @param r Número de fila
     * @param c Número de columna
     */
    public void setPos(int r, int c){
        row=r;
        col=c;
    }
    
    /**
     * Funcion toString. Devuelve toda la información de la clase monstruo en un string
     * @return Info del monstruo
     */
    public String toString(){
        return "M[ name: "+name+", intelligence: "+intelligence+", stregth: "+strength+", row: "+row+", col: "+col+", health: "+health+"]\n";
    }
    
    /**
     * Funcion gotWounded. Resta uno de vida al monstruo.
     */
    public void gotWounded(){
        health--;
    }
    /*public boolean defend(float receivedAttack){
        
    }*/
}
