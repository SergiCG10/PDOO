#Clase weapon

class Weapon

#Hay que pensar si son con uno o dos @
    @@power
    @@uses
    
    #Constructor de la clase weapon
    def initialize(p, u)
    	@power=p
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
    	
    end    
end

objeto = Weapon.new(2,0)
puts objeto.attack   
objeto.to_s