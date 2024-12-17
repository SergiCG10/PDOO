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
public class Player extends LabyrinthCharacter{
    private static final int INVALID_POS=-1;//Variable que indica la posición inicial del monstruo
    private static int MAX_WEAPONS = 2;     //Número de armas máximas que puede llevar el jugador
    private static int MAX_SHIELDS = 3;     //Número de escudos máximos que puede llevar el jugador
    private static int INITIAL_HEALTH = 3;  //Cantidad de vida con la que empieza el jugador
    private static int HITS2LOSE = 3;       //Cantidad de golpes recibidos para perder
    
    private char number;                    //Número de jugador
    private int consecutiveHits = 0;        //Golpes consecutivos recibidos por el jugador
    
    private ArrayList<Weapon> weapons= new ArrayList<>();   //Array donde almacenaremos las armas del jugador
    private ArrayList<Shield> shields= new ArrayList<>();   //Array donde almacenaremos los escudos del jugador
    
    private WeaponCardDeck weaponCardDeck;
    private ShieldCardDeck shieldCardDeck;
    
    /**
     * Constructor por parámetro de la clase Player.
     * 
     * @param nmb Número del jugador
     * @param howsmart Cómo de inteligente es
     * @param howstrong Cómo de fuerte es    
     */
    Player(char nmb, float howsmart, float howstrong){
        super("Player#"+nmb, howsmart, howstrong, (float)INITIAL_HEALTH);
        number = nmb;
        weaponCardDeck = new WeaponCardDeck();
        shieldCardDeck = new ShieldCardDeck();
    }
    
    //P5
    Player(Player other){
        super("Player#"+other.number, other.getIntelligence(), other.getStrength(), other.getHealth());
        this.setPos(other.getRow(), other.getCol());
        
        number = other.number;
        consecutiveHits = other.consecutiveHits;
        //Añadimos las armas
        for(int i = 0; i < other.weapons.size(); i++){
            this.weapons.add(other.weapons.get(i));
        }
        
        //Añadimos los escudos
        for(int i = 0; i < other.shields.size(); i++){
            this.shields.add(other.shields.get(i));
        }
    }
    
    /**
     * Funcion resurrect. Resucita al jugador, perdiendo las armas y escudos 
     * que poseía y recuperando la salud máxima, además de resetear el número de
     * golpes recibidos.
     */
    public void resurrect(){
        weapons.removeAll(weapons);
        shields.removeAll(shields);
        this.setHealth((float)INITIAL_HEALTH); 
        resetHits();       
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
     * Mueve al personaje en una dirección, si esa dirección no es posible se mueve hacia una válida
     * @param direction Direccion preferente a moverse
     * @param validMoves Vector con direcciones posibles para moverse
     * @return direccion a la que se mueve finalmente
     */
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
    
    @Override
    /**
     * Funcion attack. Devuelve el valor de ataque del jugador, calculandolo
     * como la suma del ataque de sus armas y la suma de su fuerza
     * 
     * @return float con el valor de ataque
     */
    public float attack(){
        return this.getStrength() + sumWeapons();
    }
    
    @Override
    /**
     * Funcion defend. Devuelve si el jugador se defiende del ataque
     * @param receivedAttack Cantidad de daño del atacante
     * @return True si se defiende, false si no
     */
    public boolean defend(float receivedAttack){
        return manageHit(receivedAttack);
    }
    
    /**
     * Le da al jugador armas escudos y vida aleatoris como recompensa
     */
    public void receiveReward()
    {
        int wReward=Dice.weaponsReward();
        System.out.print("\t\tJugador "+this.number+" recibe "+wReward+" armas\n");
        int sReward=Dice.shieldsReward();
        System.out.print("\t\tJugador "+this.number+" recibe "+sReward+" escudos\n");

        for(int i=0;i<wReward;i++)
        {
            //Weapon wnew=this.newWeapon();
            this.receiveWeapon( this.weaponCardDeck.nextCard());
        }
        for(int i=0;i<sReward;i++)
        {
            //Shield snew=this.newShield();
            this.receiveShield( this.shieldCardDeck.nextCard());
        }
        int extraHealth=Dice.healthReward();
        this.setHealth(this.getHealth()+extraHealth);
        System.out.print("\t\tJugador "+this.number+" recibe "+this.getHealth()+" corazones\n");

    }
    
    @Override
    /**
     * Funcion toString. Devuelve la información del jugador, su vida, inteligencia fuerza
     * y la infomación de sus armas y escudos.
     * @return String con toda la información del jugador
     */
    public String toString(){
        String info = super.toString();
        
        //Añadimos las armas
        info += "\nWeapons:\n";
        for(int i = 0; i <weapons.size() ; i++){
            info += weapons.get(i).toString();
        }
        
        //Añadimos los escudos
        info += "\nShields:\n";
        for(int i = 0; i <shields.size() ; i++){
            info += shields.get(i).toString();
        }
        info += "\n";
        
        return info;
    }
    
    /**
     * Comprueba si algún arma se descarta, la elimina, y posteriormente le da el arma nueva al jugador
     * @param w Arma a recibir por el jugador
     */
    protected void receiveWeapon(Weapon w)
    {
        for(int i=0;i<weapons.size();i++){
            Weapon wi=weapons.get(i); 
            boolean discard=wi.discard();
            if(discard)
                weapons.remove(wi);
        }
        int size=weapons.size();
        if(size<MAX_WEAPONS)
            weapons.add(w);
    }
    
    /**
     * Comprueba si algún escudo se descarta, lo elimina, y posteriormente le da el escudo nuevo al jugador
     * @param s Escudo a recibir por el jugador
     */
    protected void receiveShield(Shield s)
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
    protected float sumWeapons(){
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
    protected float sumShields(){
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
    protected float defensiveEnergy(){
        return this.getIntelligence() + sumShields();
    }  
    
    /**
     * Administra el daño recibido por el jugador y las consecuencias del mismo.
     * - Si el daño es mayor que la suma de su inteligencia más la defensa 
     *   de sus escudos, recibe uno de daño e incrementa el numero de golpes 
     *   consecutivos recibidos
     * - Si el daño es menor que la suma de su inteligencia más la defensa 
     *   resetea los golpes consecutivos recibidos a 0
     * Después comprueba:
     *   - Si alcanza el numero de golpes consecutivos, pierde
     *   - Si tiene 0 de vida pierde
     *      
     * @param receivedAttack Valor del ataque recibido
     * @return Si el jugador ha perdido o no
     */
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
     * Funcion incConsecutiveHits. Incrementa el número de golpes consecutivos
     * recibidos por el jugador
     */
    private void incConsecutiveHits(){
        consecutiveHits++;
    }
}
