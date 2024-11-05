require_relative 'Dice'

# Clase Monster

# Clase Monster. Representación de la clase monster con sus métodos y variables 
# 
# @author Miguel Ángel Luque Gómez
# correo e.mangelluqg@go.ugr.es
  
# @author Sergio Calvo González
# correo e.sergiocg10@go.ugr.es

class Monster
    @@INITIAL_HEALTH = 5;
    @@OUT_POS = -1;	
    
    def	initialize(name, howsmart, horwstrong)
	@name = name;
	@intelligence = howsmart;  
	@strength = howstrong;
	@health = @@INITIAL_HEALTH;
	@row = @@OUT_POS;
	@col = @@OUT_POS;
    end
    
    def dead
    	return @health <= 0;
    end
    
    def attack
    	return Dice.intensity(@strength);
    end
    
    #def defend(receivedAttack)
    	
    #end
    
    def setPos(r, c)
    	@row = r;
    	@col = c;
    end
    
    def to_s
    	return "M[ name: "+@name.to_s+", intelligence: "+@intelligence.to_s+", stregth: "+@strength.to_s+", row: "+@row.to_s+", col: "+@col.to_s+", health: "+@health.to_s+"]\n";
    end
    
    private
    
    def gotWounded
    	@health -= 1;
    end
end
