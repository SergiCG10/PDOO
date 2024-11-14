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
    
    # Constructor de monster.
    # @param name El nombre
    # @param howsmart La inteligencia
    # @param howstrong La fuerza
    def	initialize(name, howsmart, howstrong)
	@name = name;
	@intelligence = howsmart;  
	@strength = howstrong;
	@health = @@INITIAL_HEALTH;
	@row = @@OUT_POS;
	@col = @@OUT_POS;
    end
    
    # Devuelve true si muere el monstruo
    def dead
    	return @health <= 0;
    end
    
    # Devuelve el poder de ataque del monstruo calculado como un porcentaje de su fuerza
    def attack
    	return Dice.intensity(@strength);
    end
    
    # Establece la posicion del monstruo
    def setPos(r, c)
    	@row = r;
    	@col = c;
    end
    
    # Representacion en forma de string de los atributos del monstruo
    def to_s
    	return "M[ name: "+@name.to_s+", intelligence: "+@intelligence.to_s+", stregth: "+@strength.to_s+", row: "+@row.to_s+", col: "+@col.to_s+", health: "+@health.to_s+"]\n";
    end
    
    # Disminuye en uno la vida del monstruo
    def gotWounded
    	@health -= 1;
    end
    
    # El monstruo es dañado si su energia defensiva es menor que el daño recibido. En caso contrario se defiende.
    # @param receivedAttack El daño recibido
    # @return true si el monstruo muere, false en caso contrario
    def defend(receivedAttack)
      isDead=self.dead
      if(!isDead)
        defensiveEnergy=Dice.intensity(@intelligence)
        if(defensiveEnergy < receivedAttack)
          puts "\tMonstruo pierde 1 corazon\n"
          self.gotWounded
          isDead=self.dead
          if(isDead)
            puts "\tMonstruo "+@name.to_s+" muere\n"
          end  
        else
          puts "\tMonstruo "+@name.to_s+" se defiende\n"
        end
      end
      return isDead
   end
   
   private :gotWounded
end
