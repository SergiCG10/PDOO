package irrgarten;

/**
 * Clase Monster. Esta clase contiene la implementación de la variable monster y sus 
 * funciones básicas para la utilización de la clase
 * 
 * @author Miguel Ángel Luque Gómez
 * correo: e.mangelluqg@go.ugr.es
 * 
 * @author Sergio Calvo González
 * correo: e.sergiocg10@go.ugr.es
 */
public class Monster extends LabyrinthCharacter{
    private static final int INITIAL_HEALTH=5;  //Salud inicial 
    
    /**
     * Constructor de la clase Monster.
     * @param hownamed nombre del monstruo
     * @param howsmart inteligencia del monstruo
     * @param howstrong fuerza del monstruo
     */
    public Monster(String hownamed, float howsmart, float howstrong){
        super(hownamed, howsmart, howstrong, (float)INITIAL_HEALTH);
    }
    
    @Override
    /**
     * Funcion attack. Devuelve el ataque del monstruo
     * @return Valor aleatorio entre 0 y strength
     */
    public float attack(){
        return Dice.intensity(this.getStrength());
    }
    
    @Override
    /**
     * Funcion toString. Devuelve toda la información de la clase monstruo en un string
     * @return Info del monstruo
     */
    public String toString(){
        return "\n" + super.toString();
    }
    
    @Override
    public boolean defend(float receivedAttack){
        boolean isDead=this.dead();
        if(!isDead)
        {
            float defensiveEnergy=Dice.intensity(this.getIntelligence());
            if(defensiveEnergy < receivedAttack){
                System.out.print("\tMonstruo pierde 1 corazon\n");
                this.gotWounded();
                isDead=this.dead();
                if(isDead)
                    System.out.print("\nMonstruo muere\n");
            }
            else
                System.out.print("\tMonstruo se defiende\n");
        }
        return isDead;
    }
}
