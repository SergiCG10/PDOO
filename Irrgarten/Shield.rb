#Clase Shield
require_relative 'Dice'
class Shield
    @@protection
    @@uses
    
    #Constructor de la clase shield
    def initialize(pr, u)
    	@protection=pr
    	@uses=u
    end
    
    #Funcion protect de la clase shield
    def protect
    	defense=0;
        if( @uses > 0 )
            defense=@protection;
            @uses -= 1;
        end
        
        return defense;
    end
    
    #Funcion to_s de la clase shield
    def to_s
    	return "[" + @protection.to_s +  ", " + @uses.to_s + "]"; 
    end  
    #Funcion discard de la clase shield
    def discard
    	return Dice.discardElement(@uses)
    end
end

