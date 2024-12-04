package irrgarten;

/**
 * Clase Shield. Implementación de la clase Shield, de sus métodos y variables.
 * 
 * @author Miguel Ángel Luque Gómez
 * correo e.mangelluqg@go.ugr.es
 * 
 * @author Sergio Calvo González
 * correo e.sergiocg10@go.ugr.es
 * 
 */
public class Shield extends CombatElement{
    
    public Shield(float defense, int us){
        super(defense, us);
    }
    
    public float protect(){
        return super.produceEffect();
    }
    
    @Override
    /**
     * Funcion toString de la clase Shield. Muestra los valores de la clase
     * en un string en el formato [protection, uses].
     * @return String con la información de la clase
     */
    public String toString(){
        return "S" + super.toString();
    }
}
