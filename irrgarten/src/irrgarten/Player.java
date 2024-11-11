
package irrgarten;

import java.util.ArrayList;
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
public class Player {
    private static final int INVALID_POS=-1;//Variable que indica la posición inicial del monstruo
    private static int MAX_WEAPONS = 2;     //Número de armas máximas que puede llevar el jugador
    private static int MAX_SHIELDS = 3;     //Número de escudos máximos que puede llevar el jugador
    private static int INITIAL_HEALTH = 3;  //Cantidad de vida con la que empieza el jugador
    private static int HITS2LOSE = 3;       //Cantidad de golpes recibidos para perder
    
    private String name;                    //Nombre del jugador Ej. Player#<number>
    private char number;                    //Número de jugador
    private float intelligence;             //Cantidad de inteligencia del jugador
    private float strength;                 //Cantidad de fuerza del jugador
    private float health;                   //Cantidad de vida del jugador
    private int row;                        //Fila en la que se encuentra el jugador
    private int col;                        //Columna en la que se encuentra el jugador
    private int consecutiveHits = 0;        //Golpes consecutivos recibidos por el jugador
    
    private ArrayList<Weapon> weapons= new ArrayList<>();   //Array donde almacenaremos las armas del jugador
    private ArrayList<Shield> shields= new ArrayList<>();   //Array donde almacenaremos los escudos del jugador
    
    /**
     * Constructor por parámetro de la clase Player.
     * 
     * @param nmb Número del jugador
     * @param howsmart Cómo de inteligente es
     * @param howstrong Cómo de fuerte es    
     */
    Player(char nmb, float howsmart, float howstrong){
        name = "Player#"+nmb;
        number = nmb;
        intelligence = howsmart;
        strength = howstrong;
        health = (float)INITIAL_HEALTH;
        row = INVALID_POS; 
        col = INVALID_POS;
    }
    
    /**
     * Funcion resurrect. Resucita al jugador, perdiendo las armas y escudos 
     * que poseía y recuperando la salud máxima, además de resetear el número de
     * golpes recibidos.
     */
    public void resurrect(){
        
        weapons.removeAll(weapons);
        shields.removeAll(shields);
        health = (float)INITIAL_HEALTH;
        resetHits();
        
    }
    
    /**
     * Funcion getRow. Devuelve la fila en la que se encuentra el jugador
     * 
     * @return Número de fila del jugador 
     */
    public int getRow(){
         return row;
    }
    
    /**
     * Funcion getCol. Devuelve la columna en la que se encuentra el jugador
     * 
     * @return Número de columna del jugador 
     */
    public int getCol(){
        return col;
    }
    
    /**
     * Funcion getNumber. Devuelve el número del jugador
     * 
     * @return Número del jugador 
     */
    public char getNumber(){
        return number;
    }
    
    /**
     * Funcion setPos. Establece la posición del jugador
     * 
     * @param r Número de fila
     * @param c Número de columna
     */
    public void setPos(int r, int c){
        row = r;
        col = c;
    }
    
    /**
     * Funcion dead. Devuelve si el jugador está o no muerto
     * 
     * @return true si está muerto, false si no
     */
    public boolean dead(){
        return health <= 0;
    }
    
    //IMPLEMENTACION P3
    public Directions move(Directions direction, ArrayList<Directions> validMoves )
    {
        Directions move;
        int size=validMoves.size();
        boolean contained=false;
        for(int i=0;i<size && !contained;i++){
            if(validMoves.get(i)==direction){
                contained=true;
            }
        }
        if(size>0 && !contained)
        {
            Directions firstElement=validMoves.get(0);
            move=firstElement;
        }else
            move=direction;
        return move;
    }
    
    /**
     * Funcion attack. Devuelve el valor de ataque del jugador, calculandolo
     * como la suma del ataque de sus armas y la suma de su fuerza
     * 
     * @return float con el valor de ataque
     */
    public float attack(){
        return strength + sumWeapons();
    }
    
    /**
     * Funcion defend. Devuelve si el jugador se defiende del ataque
     * @param receivedAttack Cantidad de daño del atacante
     * @return True si se defiende, false si no
     */
    public boolean defend(float receivedAttack){
        return manageHit(receivedAttack);
    }
    
    //IMPLEMENTACION P3
    public void receiveReward()
    {
        int wReward=Dice.weaponsReward();
        System.out.print("\t\tJugador "+this.number+" recibe "+wReward+" armas\n");
        int sReward=Dice.shieldsReward();
        System.out.print("\t\tJugador "+this.number+" recibe "+sReward+" escudos\n");

        for(int i=0;i<wReward;i++)
        {
            Weapon wnew=this.newWeapon();
            this.receiveWeapon(wnew);
        }
        for(int i=0;i<sReward;i++)
        {
            Shield snew=this.newShield();
            this.receiveShield(snew);
        }
        int extraHealth=Dice.healthReward();
        health+=extraHealth;
        System.out.print("\t\tJugador "+this.number+" recibe "+health+" corazones\n");

    }
    
    /**
     * Funcion toString. Devuelve la información del jugador, su vida, inteligencia fuerza
     * y la infomación de sus armas y escudos.
     * @return String con toda la información del jugador
     */
    public String toString(){
        String info = "\n" + name + "\n";
        info += "\nIntelligence: " + intelligence;
        info += "\nStregth: " + strength;
        info += "\nHealth: " + health;
        info += "\nPosition: (" + row + ", " + col + ")";
        info += "\n\nWeapons:\n";
        for(int i = 0; i <weapons.size() ; i++){
            info += weapons.get(i).toString();
        }
        info += "\nShields:\n\n";
        for(int i = 0; i <shields.size() ; i++){
            info += shields.get(i).toString();
        }
        info += "\n";
        
        return info;
    }
    
    //IMPLEMENTACION P3
    private void receiveWeapon(Weapon w)
    {
        for(int i=0;i<weapons.size();i++){
            Weapon wi=weapons.get(i);   //el diag dice algo de next pero no me sale la funcion
            boolean discard=wi.discard();
            if(discard)
                weapons.remove(wi);
        }
        int size=weapons.size();
        if(size<MAX_WEAPONS)
            weapons.add(w);
    }
    
    //IMPLEMENTACION P3
    private void receiveShield(Shield s)
    {
        for(int i=0;i<shields.size();i++){
            Shield si=shields.get(i);
            boolean discard=si.discard();
            if(discard)
                shields.remove(si);
        }
        int size=shields.size();
        if(size<MAX_SHIELDS)
            shields.add(s);
    }
    
    /**
     * Funcion newWeapon. Crea y devuelve una nueva arma con parámetros aleatorios
     * @return arma creada con parámetros aleatorios
     */
    private Weapon newWeapon(){
        return new Weapon(Dice.weaponPower(), Dice.usesLeft());
    }
    
    /**
     * Funcion newShield. Crea y devuelve un nueva escudo con parámetros aleatorios
     * @return escudo creado con parámetros aleatorios
     */
    private Shield newShield(){
        return new Shield(Dice.shieldPower() , Dice.usesLeft());
    }
    
    /**
     * Funcion sumWeapons. Devuelve el valor de ataque total de todas las armas del jugador
     * También se decrementa el uso de dichas armas (con la llamada de attack en cada arma)
     * 
     * @return float con la suma de ataque
     */
    private float sumWeapons(){
        float totalAttack = 0f;
        for(int i = 0; i < weapons.size(); i++){
            totalAttack += weapons.get(i).attack();
        }
        return totalAttack;
    }
    
    /**
     * Funcion sumShields. Devuelve el valor de defensa total de todos los escudos del jugador
     * También se decrementa el uso de dichos escudos (con la llamada de protect en cada escudo)
     * 
     * @return float con la suma de ataque
     */
    private float sumShields(){
        float totalDefense = 0f;
        for(int i = 0; i < shields.size(); i++){
            totalDefense += shields.get(i).protect();
        }
        return totalDefense;
    }
    
    /**
     * Funcion defensiveEnergy. Devuelve el valor de defensa del jugador, calculandolo
     * como la suma de la denfesa de sus escudos y la suma de su inteligencia
     * 
     * @return float con el valor de ataque
     */
    private float defensiveEnergy(){
        return intelligence + sumShields();
    }  
    
    //IMPLEMENTACION P3
    private boolean manageHit(float receivedAttack){
        float defense=this.defensiveEnergy();
        if(defense < receivedAttack){
            this.gotWounded();
            this.incConsecutiveHits();
            System.out.print("\tJugador pierde un corazon\n"+consecutiveHits+" golpes consecutivos\n");

        }
        else{
            this.resetHits();
            System.out.print("\tJugador "+this.number+" se defiende\n");
        }
        
        boolean lose;
        if(consecutiveHits==HITS2LOSE){
            System.out.print("\tJugador recibe "+HITS2LOSE+" golpes, queda fuera de combate\n");
            this.resetHits();
            lose=true;
        }
        else if(this.dead()){
            System.out.print("\nJugador "+this.number+" muere\n");
            this.resetHits();
            lose=true;
        }
        else
            lose=false;
        
        return lose;
    }
    
    /**
     * Funcion resetHits. Resetea el número de golpes recibidos por el jugador
     */
    private void resetHits(){
        consecutiveHits = 0;
    }
    
    /**
     * Funcion gotWounded. Resta uno de vida al jugador
     */
    private void gotWounded(){
        health--;
    }
    
    /**
     * Funcion incConsecutiveHits. Incrementa el número de golpes consecutivos
     * recibidos por el jugador
     */
    private void incConsecutiveHits(){
        consecutiveHits++;
    }
}
