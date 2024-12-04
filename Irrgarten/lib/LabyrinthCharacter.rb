class LabyrinthCharacter
	@@INVALID_POS=-1
	
	attr_reader :name 
	attr_reader :intelligence 
	attr_reader :strength 
	attr_accessor :health
	attr_reader :row
	attr_reader :col
	
	def initialize(nm, howSmart, howStrong, hp)
		@name = nm
		@intelligence = howSmart
		@strength = howStrong
		@health = hp
		@row = @@INVALID_POS
		@col = @@INVALID_POS
	end
	
	def copy(other)
		@name = other.name
		@intelligence = other.intelligence
		@strength = other.strength
		@health = other.health
		@row = other.row
		@col = other.col
	end
	
	# Devuelve true si muere el monstruo
    def dead
    	return @health <= 0;
    end
    
	# Funcion setPos. Establece la posición del jugador
    # 
    # @param r Número de fila
    # @param c Número de columna
	def setPos(r,c)
		@row=r
		@col=c
	end
	
	def to_s
		info ="\n"+@name+":\n"
		info +="\nIntelligence: " + @intelligence.to_s
		info +="\nStregth: " + @strength.to_s
		info +="\nHealth: " + @health.to_s
		info +="\nPosition: (" + @row.to_s + ", " + @col.to_s + ")"
		return info
	end
	
	def gotWounded
		@health -= 1
	end
	
	protected :intelligence, :strength, :health, :gotWounded
	private_class_method :new
end
