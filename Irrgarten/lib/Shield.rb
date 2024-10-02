require_relative 'Dice'

# Clase Shield. Implementación de la clase Shield, de sus métodos y variables.
# 
# @author Miguel Ángel Luque Gómez
# correo e.mangelluqg@go.ugr.es
# 
# @author Sergio Calvo González
# correo e.sergiocg10@go.ugr.es
#
class Shield
    @@protection
    @@uses
    
    # Constructor por parámetro de la clase Shield
    # @param p defensa del escudo a crear
    # @param us usos del escudo a crear
    #
    def initialize(pr, u)
    	@protection=pr
    	@uses=u
    end
    
    # Función Protect. Devuelve el valor de defensa del escudo
    # y decrementa su uso en uno
    # @return Valor de denfesa. 0 si no le quedan usos, protection si no.
    #
    def protect
    	defense=0;
        if( @uses > 0 )
            defense=@protection;
            @uses -= 1;
        end
        
        return defense;
    end
    
    # Funcion toString de la clase Shield. Muestra los valores de la clase
    # en un string en el formato [protection, uses].
    # @return String con la información de la clase
    #
    def to_s
    	return "[" + @protection.to_s +  ", " + @uses.to_s + "]"; 
    end  
    
    # Funcion discard. Devuelve si se debe de descartar el escudo.
    # @return True si se descarta, false si no.
    #
    def discard
    	return Dice.discardElement(@uses)
    end
end

