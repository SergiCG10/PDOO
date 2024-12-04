require_relative 'CombatElement'
require_relative 'Dice'

# Clase Shield. Implementación de la clase Shield, de sus métodos y variables.
# 
# @author Miguel Ángel Luque Gómez
# correo e.mangelluqg@go.ugr.es
# 
# @author Sergio Calvo González
# correo e.sergiocg10@go.ugr.es
#
class Shield < CombatElement
    # Constructor por parámetro de la clase Shield
    # @param pr defensa del escudo a crear
    # @param u usos del escudo a crear
    #
    def initialize(pr, u)
    	super
    end
    
    # Función Protect. Devuelve el valor de defensa del escudo
    # y decrementa su uso en uno
    # @return Valor de denfesa. 0 si no le quedan usos, protection si no.
    #
    def protect
    	return super
    end
    
    # Funcion toString de la clase Shield. Muestra los valores de la clase
    # en un string en el formato [protection, uses].
    # @return String con la información de la clase
    #
    def to_s
    	return "S" + super; 
    end  
end

