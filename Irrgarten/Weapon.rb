require_relative 'Dice'

# Clase Weapon. Implementación de la clase Weapon, de sus métodos y variables.
# 
# @author Miguel Ángel Luque Gómez
# correo e.mangelluqg@go.ugr.es
# 
# @author Sergio Calvo González
# correo e.sergiocg10@go.ugr.es
#
class Weapon
    @@power
    @@uses
    
    # Constructor por parámetro de la clase Weapon
    # @param p daño del arma a crear
    # @param us usos del arma a crear
    #
    def initialize(pw, u)
    	@power=pw
    	@uses=u
    end
    
    # Función Attack. Devuelve el valor de daño del arma
    # y decrementa su uso en uno
    # @return Valor de ataque. 0 si no le quedan usos, power si no.
    #
    def attack
    	damage=0;
        if( @uses > 0 )
            damage=@power;
            @uses -= 1;
        end
        
        return damage;
    end
    
    # Funcion toString de la clase Weapon. Muestra los valores de la clase
    # en un string en el formato [power, uses].
    # @return String con la información de la clase
    #
    def to_s
    	return "[" + @power.to_s +  ", " + @uses.to_s + "]"; 
    end 
    
    # Funcion discard. Devuelve si se debe de descartar el arma.
    # @return True si se descarta, false si no.
    #
    def discard
    	return Dice.discardElement(@uses)
    end
     
end
