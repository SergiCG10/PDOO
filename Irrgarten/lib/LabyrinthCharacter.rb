class LabyrinthCharacter
	@@INVALID_POS=-1
	
	attr_reader :name 
	attr_reader :intelligence 
	attr_reader :strength 
	attr_reader :health
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
	
	##PREGUNTA, como se hace?
	def copy(other)
		@name = other.name
		@intelligence = other.intelligence
		@strength = other.strength
		@health = other.health
		setPos(other.row, other.col)
	end
	
	# Devuelve true si muere el monstruo
    def dead
    	return @health <= 0;
    end
    
    #PREGUNTAR AL PROFESOR SI CON ATTR_ACCESSOR SIRVE O HAY QUE PONER ESTO PARA QUE SEA PROTECTED
    def getRow
    	return @row
    end
    
	def getCol
		return @col
	end
	
	def getIntelligence
		return @intelligence
	end
	
	def getStrength
		return @strength
	end
	
	def getHealth
		return @health
	end
	
	def setHealth(hp)
		@health = hp
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
	
	#PREGUNTAR SI ESTO ESTÁ BIEN ASÍ (ATTACK y DEFEND)
	def attack
		raise NotImplementedError, "El metodo attack no está implementado para CombatElement"
	end
	
	def defend
		raise NotImplementedError, "El metodo defend no está implementado para CombatElement"
	end
	
	protected :getIntelligence, :getStrength, :getHealth, :setHealth, :gotWounded
end
