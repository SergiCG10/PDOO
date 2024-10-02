#Clase Weapon
require_relative 'Dice'
class Weapon
    @@power
    @@uses
    
    #Constructor de la clase weapon
    def initialize(pw, u)
    	@power=pw
    	@uses=u
    end
    
    #Funcion attack de la clase weapon
    def attack
    	damage=0;
        if( @uses > 0 )
            damage=@power;
            @uses -= 1;
        end
        
        return damage;
    end
    
    #Funcion to_s de la clase weapon
    def to_s
    	return "[" + @power.to_s +  ", " + @uses.to_s + "]"; 
    end 
    #Funcion discard de la clase weapon
    def discard
    	return Dice.discardElement(@uses)
    end
     
end
