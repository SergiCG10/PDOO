require_relative 'Dice'
require_relative 'LabyrinthCharacter'
# Clase Monster

# Clase Monster. Representación de la clase monster con sus métodos y variables 
# 
# @author Miguel Ángel Luque Gómez
# correo e.mangelluqg@go.ugr.es
  
# @author Sergio Calvo González
# correo e.sergiocg10@go.ugr.es

class Monster < LabyrinthCharacter
    public_class_method :new
    
    @@INITIAL_HEALTH = 5;
    
    # Constructor de monster.
    # @param name El nombre
    # @param howsmart La inteligencia
    # @param howstrong La fuerza
    def	initialize(name, howSmart, howStrong)
		super(name, howSmart, howStrong, @@INITIAL_HEALTH)
    end
    
    # Devuelve el poder de ataque del monstruo calculado como un porcentaje de su fuerza
    def attack
    	return Dice.intensity(@strength);
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
end
