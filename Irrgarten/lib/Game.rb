# Clase Game. Esta clase contiene la implementación de la variable game y sus
# funciones básicas para la utilización de la clase
#
# @author Miguel Ángel Luque Gómez
# correo e.mangelluqg@go.ugr.es
#  
# @author Sergio Calvo González
# correo e.sergiocg10@go.ugr.es

require_relative 'Player'
require_relative 'Monster'
require_relative 'GameState'
require_relative 'Labyrinth'
require_relative 'Orientation'
require_relative 'GameCharacter'
class Game
	@@MAX_ROUNDS=10
	  
    # Inicializa el juego, creando jugadores y monstruos, configurando el laberinto, y asignando el turno inicial.
	def initialize (nplayers)
		@players=Array.new(nplayers)
		@monsters=Array.new
		configureLabyrinth
		
		nplayers.times do |i|
			c=(i+1).to_s
			p=Player.new(c,Dice.randomIntelligence,Dice.randomStrength)	
			@players[i]=p
			p.receiveReward
		end
		
		@labyrinth.spreadPlayers(@players)
		@currentPlayerIndex=Dice.whoStarts(nplayers)
		@currentPlayer=@players[@currentPlayerIndex]
		@log=""
	end
	
	# Indica si el juego ha terminado, delegando en el método del laberinto que verifica la presencia de un ganador.
    # @return [Boolean] true si hay un ganador, de lo contrario false.
	def finished
		@labyrinth.haveAWinner
	end
	
	# Mientras el jugador esté vivo, intenta moverse en la direccion pasada por parametro. Si no puede, se mueve en una de las posibles y comprueba si se produce combate.
	# Si el jugador está muerto, se comprueba si revive. La función devuelve true si se ha alcanzado el final del juego
	# @param preferredDirection. Dirección a la que se pretende mover el Player.
	# @return [Boolean] true si el juego ha terminado, de lo contrario false
	def next_step (preferredDirection)
	  @log=""
	  dead=@currentPlayer.dead
	  if(!dead)
	    direction=actualDirection(preferredDirection)
	    if(direction != preferredDirection)
	      self.logPlayerNoOrders
	    end
	    monster=@labyrinth.putPlayer(direction, @currentPlayer)
	    if(monster == nil)
	      self.logNoMonster
	    else
	    	if !monster.dead
	      		winner=self.combat(monster)
	      		self.manageReward(winner)
	      	end
	    end
	  else
	    self.manageResurrection
	  end
	  endGame=self.finished
	  if(!endGame)
	    self.nextPlayer
	  end
	  return endGame
	end
	
	# Genera una instancia de GameState con toda la información del estado actual del juego.
    # @return [GameState] El estado actual del juego.
	def game_state
		allPlayers=""
		allMonsters=""
		for i in 0..@players.size-1
			allPlayers+= @players[i].to_s
		end
	
		for i in 0..@monsters.size-1
			allMonsters+="\t "+ @monsters[i].to_s 
		end
		return GameState.new(@labyrinth.toRealRepresentation, allPlayers, allMonsters, @currentPlayerIndex, self.finished, @log)
		
	end
	
	private
	
	# Configura el laberinto, añadiendo obstáculos y monstruos.
    # Los monstruos se guardan tanto en el laberinto como en el contenedor de monstruos de la clase.
	def configureLabyrinth
		nRows=7
		nCols=7
		@labyrinth=Labyrinth.new(nRows,nCols,nRows-1,nCols-1)
		#Añadimos los muros
		@labyrinth.addBlock(Orientation::VERTICAL, 0, 1, 2)
		@labyrinth.addBlock(Orientation::HORIZONTAL, 1, 2, 2)
		@labyrinth.addBlock(Orientation::HORIZONTAL, 0, 5, 1)
		@labyrinth.addBlock(Orientation::VERTICAL, 2, 5, 2)
		@labyrinth.addBlock(Orientation::HORIZONTAL, 3, 0, 2)
		@labyrinth.addBlock(Orientation::HORIZONTAL, 3, 3, 2)
		@labyrinth.addBlock(Orientation::HORIZONTAL, 5, 0, 4)
		@labyrinth.addBlock(Orientation::HORIZONTAL, 5, 5, 2)
		@labyrinth.addBlock(Orientation::VERTICAL, 4, 3, 2)
				
		nMonstruos=Dice.randomPos(5)+3
		
		for i in 0..nMonstruos-1
		  monster=Monster.new("Orco", Dice.randomIntelligence, Dice.randomStrength)
		  r = Dice.randomPos( @labyrinth.getRows ) 
		  c = Dice.randomPos( @labyrinth.getCols )
		  @labyrinth.addMonster(r, c, monster)
		  @monsters.push(monster)
		end
	end
	
	# Cambia el turno al siguiente jugador en la lista, actualizando los atributos de turno actual.
	def nextPlayer
		@players[@currentPlayerIndex]= @currentPlayer
		if @currentPlayerIndex+1==@players.size
			@currentPlayerIndex=0
		else
			@currentPlayerIndex+=1
		end
		@currentPlayer=@players[@currentPlayerIndex]
	end
	

    #Devuelve el resultado  del ultimo step 
    #@param preferredDirection. Dirección preferible
    #@return la dirección final   
	def actualDirection(preferredDirection)
		r = @currentPlayer.getRow
		c = @currentPlayer.getCol
		
		validMoves = @labyrinth.validMoves(r,c)
		dir = @currentPlayer.move(preferredDirection, validMoves)
		return dir
	end
	
    
	# Funcion Combat. Devuelve el gameCharacter ganador de la batalla
	# @param monster
	# @return gameCharacter ganador
	def combat(monster)
		rounds = 0
		winner = GameCharacter::PLAYER
		playerAttack = @currentPlayer.attack
		puts "Jugador " +  (@currentPlayerIndex + 1).to_s + "hace " + playerAttack.to_s + " daño\n"
		lose = monster.defend(playerAttack)
		if lose
			puts "Monstruo muere\n"
		end
		while !lose && rounds < @@MAX_ROUNDS
			winner = GameCharacter::MONSTER
			monsterAttack = monster.attack
			puts "Monstruo hace " + monsterAttack.to_s + " daño\n"
			lose = @currentPlayer.defend(monsterAttack)
			if !lose
				playerAttack = @currentPlayer.attack
				puts "Jugador " +  (@currentPlayerIndex + 1).to_s + "hace " + playerAttack.to_s + " daño\n"
				winner = GameCharacter::PLAYER
				lose = monster.defend(playerAttack)
			end
			rounds += 1
		end
		logRounds(rounds, @@MAX_ROUNDS)
		return winner
	end
	
	# Otorga al Player las recompensas por ganar el combate
	# @param winner
	def manageReward(winner)
		if(winner == GameCharacter::PLAYER)
			@currentPlayer.receiveReward
			logPlayerWon
		else
			logMonsterWon
		end
	end
	
	# Actualiza los valores del Player que acaba de resucitar
	def manageResurrection
		resurrect = Dice.resurrectPlayer
		if resurrect
			@currentPlayer.resurrect
			logResurrected
		else
			logPlayerSkipTurn
		end
	end
	
	# Registra en el log que el jugador ha ganado el combate.
	def logPlayerWon
		@log+="El jugador ha ganado el combate\n"
	end
	
	# Registra en el log que el monstruo ha ganado el combate.
	def logMonsterWon
		@log+="El monstruo ha ganado el combate\n"
	end
	
	# Registra en el log que el jugador ha resucitado.
	def logResurrected
		@log+="El jugador ha resucitado\n"
	end
	
	# Registra en el log que el jugador ha perdido el turno debido a que está muerto.
	def logPlayerSkipTurn
		@log += "El jugador ha perdido turno por estar muerto\n"
	end
	
	# Registra en el log que el jugador no ha seguido las instrucciones del jugador humano.
	def logPlayerNoOrders
		@log += "El jugador no ha podido seguir las instrucciones\n"
	end
	
	# Registra en el log que el jugador se movió a una celda vacía o que no pudo moverse.
	def logNoMonster
		@log += "El jugador se ha movido a una celda vacía o no ha podido moverse\n"
	end
	
	# Registra en el log el número de rondas completadas de combate.
    # @param rounds [Integer] Número de rondas completadas.
    # @param max [Integer] Número máximo de rondas permitidas.
	def logRounds(rounds, max)
		@log+="Se han producido " + rounds.to_s + " de " + max.to_s + "\n"
	end
end
