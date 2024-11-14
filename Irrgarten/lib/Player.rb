# Clase Player

# Clase Player. Representación de la clase player con sus métodos y variables 
# 
# @author Miguel Ángel Luque Gómez
# correo e.mangelluqg@go.ugr.es
  
# @author Sergio Calvo González
# correo e.sergiocg10@go.ugr.es
 
require_relative 'Weapon'
require_relative 'Shield'
require_relative "Directions"

class Player
	
	@@INVALID_POS=-1
	@@MAX_WEAPONS=2
	@@MAX_SHIELDS=3
	@@INITIAL_HEALTH=3
	@@HITS2LOSE=3	
     
     # Constructor por parámetro de la clase Player.
     # 
     # @param nmb Número del jugador
     # @param howsmart Cómo de inteligente es
     # @param howstrong Cómo de fuerte es    
	def initialize(nmb, howsmart, howstrong)
		@name="Player#"+nmb
		@number=nmb
		@intelligence=howsmart
		@strength=howstrong
		@health=@@INITIAL_HEALTH
		@row=@@INVALID_POS
		@col=@@INVALID_POS
		@consecutiveHits=0
		@weapons=Array.new
		@shields=Array.new
	end
	
	attr_accessor: weapons
	attr_acessor: shields
     
     # Funcion resurrect. Resucita al jugador, perdiendo las armas y escudos 
     # que poseía y recuperando la salud máxima, además de resetear el número de
     # golpes recibidos.
	def resurrect
		@weapons.clear
		@shields.clear
		@health=@@INITIAL_HEALTH
		#resetHits
	end
     
     # Funcion getRow. Devuelve la fila en la que se encuentra el jugador
     # 
     # @return Número de fila del jugador 
	def getRow
		@row
	end
     
     # Funcion getCol. Devuelve la columna en la que se encuentra el jugador
     # 
     # @return Número de columna del jugador 
	def getCol
		@col
	end
     
     #Funcion getNumber. Devuelve el número del jugador
     #
     #@return Número del jugador 
     #
	def getNumber
		@number
	end
    
     # Funcion setPos. Establece la posición del jugador
     # 
     # @param r Número de fila
     # @param c Número de columna
	def setPos(r,c)
		@row=r
		@col=c
	end
     
     # Funcion dead. Devuelve si el jugador está o no muerto
     # 
     # @return true si está muerto, false si no
     #
	def dead
		@health <=0
	end
	
	# Funcion move. Mueve al personaje hacia direction, si no se puede, hacia una valida dentro de validMoves
	#
	# @param direction Direccion preferente a moverse
	# @param validMoves Vector con las direcciones validas a moverse
	def move(direction, validMoves)
		
		size = validMoves.size
		contained = false
		i = 0
		while ( i < size && !contained ) 
			if validMoves[i] == direction
				contained = true
			end
			i += 1
		end
		if (size > 0 && !contained)
			firstElement = validMoves[0]
			move = firstElement
		else
			move = direction
		end
		return move
	end
	
     #
     # Funcion attack. Devuelve el valor de ataque del jugador, calculandolo
     # como la suma del ataque de sus armas y la suma de su fuerza
     # 
     # @return float con el valor de ataque
     #
	def attack
		return @strength+sumWeapons
	end
     
     #
     # Funcion defend. Devuelve si el jugador se defiende del ataque
     # @param receivedAttack Cantidad de daño del atacante
     # @return True si se defiende, false si no
     #
	def defend(receivedAttack)
		return manageHit(receivedAttack)
	end
	
	# Funcion receiveReward. Le da al jugador armas, escudos y vida aleatorios
	def receiveReward
		nW = Dice.weaponsReward()
		nS = Dice.shieldsReward()
		
		nW.times do
		    w = newWeapon
			receiveWeapon(w)
		end
		
		nS.times do 
			s = newShield
			receiveShield(s)
		end
		
		extraHealth = Dice.healthReward
		@health += extraHealth
	end
	
     #
     # Funcion toString. Devuelve la información del jugador, su vida, inteligencia, fuerza
     # y la infomación de sus armas y escudos.
     # @return String con toda la información del jugador
     #
	def to_s
		info ="\n\t"+@name+"\n"
		info +="\n\tIntelligence: " + @intelligence.to_s
		info +="\n\tStregth: " + @strength.to_s
		info +="\n\tHealth: " + @health.to_s
		info +="\n\tPosition: (" + @row.to_s + ", " + @col.to_s + ")"
		info +="\n\n\tWeapons:\n"
		weapons.each do |weapon| 
			info+="\t" + weapon.to_s
			info+="\n"
		end
		info +="\n\n\tShields:\n"
		shields.each do |shield| 
			info+="\t" + shield.to_s
			info+="\n"
		end
		return info
	end
	
	# Funcion receiveWeapon. Elimina las armas que se deben de descartar y le da al jugador el arma nueva si se puede
	#
	# @param newW Arma nueva para el jugador
	def receiveWeapon(newW)
		@weapons.each do |w| 
			if w.discard
				@weapons.delete(w)
			end
		end
		
		if @weapons.size < @@MAX_WEAPONS
			@weapons.push(newW)
		end
	end
	
	# Funcion receiveShield. Elimina los escudos que se deben de descartar y le da al jugador el escudo nuevo si se puede
	#
	# @param newS Escudo nuevo para el jugador
	def receiveShield(newS)
		@shields.each do |s| 
			if s.discard
				@weapons.delete(s)
			end
		end
		
		if @shields.size < @@MAX_SHIELDS
			@shields.push(newS)
		end
	end
	
    #    
    # Funcion newWeapon. Crea y devuelve una nueva arma con parámetros aleatorios
    # @return arma creada con parámetros aleatorios
    #
	def newWeapon
		return Weapon.new(Dice.weaponPower, Dice.usesLeft)
	end
     
     #
     # Funcion newShield. Crea y devuelve un nueva escudo con parámetros aleatorios
     # @return escudo creado con parámetros aleatorios
     #
	def newShield
		return Shield.new(Dice.shieldPower, Dice.usesLeft)
	end

     # Funcion sumWeapons. Devuelve el valor de ataque total de todas las armas del jugador
     # También se decrementa el uso de dichas armas (con la llamada de attack en cada arma)
     # 
     # @return float con la suma de ataque
     #
	def sumWeapons
		totalAttack=0
		weapons.each do |weapon|
			totalAttack+=weapon.attack
		end
		return totalAttack
	end
     #
     # Funcion sumShields. Devuelve el valor de defensa total de todos los escudos del jugador
     # También se decrementa el uso de dichos escudos (con la llamada de protect en cada escudo)
     # 
     # @return float con la suma de ataque
     #
	def sumShields
		totalDefense=0
		shields.each do |shield|
			totalDefense+=shield.protect
		end
		return totalDefense
	end
     #
     # Funcion defensiveEnergy. Devuelve el valor de defensa del jugador, calculandolo
     # como la suma de la denfesa de sus escudos y la suma de su inteligencia
     # 
     # @return float con el valor de ataque
     #
	def defensiveEnergy
		return @intelligence + sumShields
	end
	
	#Funcion manageHit. Gestiona el ataque recibido por el jugador
	# Si el jugador recibe un ataque mayor que su inteligencia más la suma de la defensa de sus escudos, recibe uno de daño, si no, se resetea el numero de golpes consecutivos recibidos
	# Si el numeo de golpes consecutivos alcanza @@HITS2LOSE el jugador pierde
	# Si la vida del jugador es 0, el jugadir pierde tambien
	# 
	# @param receivedAttack Valor de ataque recibido
	# @return Booleano de si el jugador pierde o no
	#
	def manageHit(receivedAttack)
		defense = defensiveEnergy()
				
		if defense < receivedAttack
			gotWounded()
			incConsecutiveHits()
		else
			resetHits()
		end
		
		if (@consecutiveHits == @@HITS2LOSE) || dead()
			lose = true
		else
			lose = false
		end
		
		return lose
	end
	
     #	   
     # Funcion resetHits. Resetea el número de golpes recibidos por el jugador
     #
	def resetHits
		@consecutiveHits=0
	end
     #	  
     # Funcion gotWounded. Resta uno de vida al jugador
     #
	def gotWounded
		@health-=1
	end
     
     #
     # Funcion incConsecutiveHits. Incrementa el número de golpes consecutivos
     # recibidos por el jugador
     #
	def incConsecutiveHits
		@consecutiveHits+=1
	end
		
end

#w1=p.newWeapon
#w2=p.newWeapon
#s1=p.newShield
#s2=p.newShield
#s3=p.newShield
#p.shields.push(s1)
#p.shields.push(s2)
#p.shields.push(s3)
#puts p.toString
#puts "\nsuma defensa escudos"
#sum=p.sumShields
#puts sum
#puts "defensive energy" 
#puts p.defensiveEnergy


