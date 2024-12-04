package irrgarten;
/**
 *
 * @author usuario
 */
public abstract class LabyrinthCharacter {
    private static final int INVALID_POS=-1;    //Variable que indica la posición inicial
    
    private String name;                        //Nombre
    private float strength;                     //Fuerza 
    private float intelligence;                 //Inteligencia
    private float health;                       //Vida actual
    private int row;                            //Fila
    private int col;                            //Columna
    
    //P5
    LabyrinthCharacter(String hownamed, float howsmart, float howstrong, float hp){
        name = hownamed;
        intelligence = howsmart;
        strength = howstrong;
        health = hp;
        row = INVALID_POS; 
        col = INVALID_POS;
    }
    
    //P5
    LabyrinthCharacter(LabyrinthCharacter other){
        name = other.name;
        intelligence = other.intelligence;
        strength = other.strength;
        health = other.health;
        row = other.row; 
        col = other.col;
    }
    
    /**
     * Funcion dead. Devuelve si está o no muerto
     * 
     * @return true si está muerto, false si no
     */
    public boolean dead(){
        return (health <= 0);
    }
    
    /**
     * Funcion getRow. Devuelve la fila en la que se encuentra
     * 
     * @return Número de fila 
     */
    public int getRow(){
         return row;
    }
    
    /**
     * Funcion getCol. Devuelve la columna en la que se encuentra 
     * 
     * @return Número de columna
     */
    public int getCol(){
        return col;
    }
    
    //P5
    protected float getIntelligence(){
        return intelligence;
    }
    
    //P5
    protected float getStrength(){
        return strength;
    }
    
    //P5
    protected float getHealth(){
        return health;
    }
    
    //P5
    protected void setHealth(float hp){
        health = hp;
    }
    
    /**
     * Función setPos. Establece la posición
     * @param r Número de fila
     * @param c Número de columna
     */
    public void setPos(int r, int c){
        row=r;
        col=c;
    }
    
    public String toString(){
        String info = name + ":\n";
        info += "\nIntelligence: " + intelligence;
        info += "\nStregth: " + strength;
        info += "\nHealth: " + health;
        info += "\nPosition: (" + row + ", " + col + ")\n\n";      
        
        return info;
    }
    
    /**
     * Funcion gotWounded. Resta uno de vida.
     */
    protected void gotWounded(){
        health--;
    }
    
    abstract public float attack();
    
    abstract public boolean defend(float attack);
}
