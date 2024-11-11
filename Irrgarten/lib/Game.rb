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
require_relative 'Orientations'
require_relative 'GameCharacter'
class Game
	@@MAX_ROUNDS=10
	
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
	
	def finished
		@labyrinth.haveAWinner
	end
	
	#P3
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
	      winner=self.combat(monster)
	      self.manageReward(winner)
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
	
	def configureLabyrinth
		nRows=7
		nCols=7
		@labyrinth=Labyrinth.new(nRows,nCols,nRows-1,nCols-1)
		#Añadimos los muros
		@labyrinth.addBlock(Orientations::VERTICAL, 0, 1, 2)
		@labyrinth.addBlock(Orientations::HORIZONTAL, 1, 2, 2)
		@labyrinth.addBlock(Orientations::HORIZONTAL, 0, 5, 1)
		@labyrinth.addBlock(Orientations::VERTICAL, 2, 5, 2)
		@labyrinth.addBlock(Orientations::HORIZONTAL, 3, 0, 2)
		@labyrinth.addBlock(Orientations::HORIZONTAL, 3, 3, 2)
		@labyrinth.addBlock(Orientations::HORIZONTAL, 5, 0, 4)
		@labyrinth.addBlock(Orientations::HORIZONTAL, 5, 5, 2)
		@labyrinth.addBlock(Orientations::VERTICAL, 4, 3, 2)
				
		nMonstruos=Dice.randomPos(5)+3
		
		for i in 0..nMonstruos-1
		  monster=Monster.new("Orco", Dice.randomIntelligence, Dice.randomStrength)
		  r = Dice.randomPos( @labyrinth.getRows ) 
		  c = Dice.randomPos( @labyrinth.getCols )
		  @labyrinth.addMonster(r, c, monster)
		  @monsters.push(monster)
		end
		#puts @labyrinth.to_s
	end
	
	def nextPlayer
		@players[@currentPlayerIndex]= @currentPlayer
		if @currentPlayerIndex+1==@players.size
			@currentPlayerIndex=0
		else
			@currentPlayerIndex+=1
		end
		@currentPlayer=@players[@currentPlayerIndex]
	end
	
	def actualDirection(preferredDirection)
		r = @currentPlayer.getRow
		c = @currentPlayer.getCol
		
		validMoves = @labyrinth.validMoves(r,c)
		dir = @currentPlayer.move(preferredDirection, validMoves)
		return dir
	end
	
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
	
	def manageReward(winner)
		if(winner == GameCharacter::PLAYER)
			@currentPlayer.receiveReward
			logPlayerWon
		else
			logMonsterWon
		end
	end
	
	def manageResurrection
		resurrect = Dice.resurrectPlayer
		if resurrect
			@currentPlayer.resurrect
			logResurrected
		else
			logPlayerSkipTurn
		end
	end
	
	def logPlayerWon
		@log+="El jugador ha ganado el combate\n"
	end
	
	def logMonsterWon
		@log+="El monstruo ha ganado el combate\n"
	end
	
	def logResurrected
		@log+="El jugador ha resucitado\n"
	end
	
	def logPlayerSkipTurn
		@log += "El jugador ha perdido turno por estar muerto\n"
	end
	
	def logPlayerNoOrders
		@log += "El jugador no ha podido seguir las instrucciones\n"
	end
	
	def logNoMonster
		@log += "El jugador se ha movido a una celda vacía o no ha podido moverse\n"
	end
	
	def logRounds(rounds, max)
		@log+="Se han producido " + rounds.to_s + " de " + max.to_s + "\n"
	end
end
