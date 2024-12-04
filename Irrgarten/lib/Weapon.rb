require_relative 'Dice'
require_relative 'CombatElement'
# Clase Weapon. Implementación de la clase Weapon, de sus métodos y variables.
# 
# @author Miguel Ángel Luque Gómez
# correo e.mangelluqg@go.ugr.es
# 
# @author Sergio Calvo González
# correo e.sergiocg10@go.ugr.es
#
class Weapon < CombatElement
    # Constructor por parámetro de la clase Weapon
    # @param pw daño del arma a crear
    # @param u usos del arma a crear
    #
    def initialize(pw, u)
    	super
    end
    
    
    # Función Attack. Devuelve el valor de daño del arma
    # y decrementa su uso en uno
    # @return Valor de ataque. 0 si no le quedan usos, power si no.
    #
    def attack
    	return super
    end
    
    # Funcion toString de la clase Weapon. Muestra los valores de la clase
    # en un string en el formato [power, uses].
    # @return String con la información de la clase
    #
    def to_s
    	return "W" + super; 
    end      
end
