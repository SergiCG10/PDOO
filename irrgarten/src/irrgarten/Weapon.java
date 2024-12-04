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
public class Weapon extends CombatElement{
    
    public Weapon(float defense, int us){
        super(defense, us);
    }
    
    public float attack(){
        return super.produceEffect();
    }
    
    @Override
    /**
     * Funcion toString de la clase Shield. Muestra los valores de la clase
     * en un string en el formato [protection, uses].
     * @return String con la información de la clase
     */
    public String toString(){
        return "W" + super.toString();
    }
}
