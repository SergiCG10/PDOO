require_relative 'Dice'

class CombatElement
	#P4
	def initialize(efct, us) 
		@effect = efct
		@uses = us
	end
	
	def produceEffect
		eff=0;
        if( @uses > 0 )
            eff = @effect;
            @uses -= 1;
        end
        
        return eff;
	end
	
	def to_s
    	return "[" + @effect.to_s +  ", " + @uses.to_s + "]"; 
    end 
	
	# Funcion discard. Devuelve si se debe de descartar.
    # @return True si se descarta, false si no.
    #
    def discard
    	return Dice.discardElement(@uses)
    end
    
	protected :produceEffect
	private_class_method :new
end
