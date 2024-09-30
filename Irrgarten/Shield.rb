#Clase Shield

class Shield
    @@protection
    @@uses
    
    #Constructor de la clase weapon
    def initialize(pr, u)
    	@protection=pr
    	@uses=u
    end
    
    #Funcion attack de la clase weapon
    def protect
    	defense=0;
        if( @uses > 0 )
            defense=@protection;
            @uses -= 1;
        end
        
        return defense;
    end
    
    #Funcion to_s de la clase weapon
    def to_s
    	return "[" + @protection.to_s +  ", " + @uses.to_s + "]"; 
    end    
end
