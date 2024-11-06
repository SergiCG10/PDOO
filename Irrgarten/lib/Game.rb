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
class Game
	@@MAX_ROUNDS=10
	@currentPlayer
	@labyrinth
	
	def initialize (nplayers)
		@players=Array.new(nplayers)
		@monsters=Array.new
		for i in 0..nplayers-1
			c=(i+1).to_s
			p=Player.new(c,Dice.randomIntelligence,Dice.randomStrength)	
			@players[i]=p
			#puts @players[i].toString
		end
		@currentPlayerIndex=0
		@log=""
	end
	
	def finished
		#@labyrinth.haveAWinner
	end
	
	def getGameState
		allPlayers=""
		allMonsters=""
		for i in 0..@players.size-1
			allPlayers+=@players[i].toString
		end
		for i in 0..@monsters.size-1
			allMonsters+=@monsters[i].toString
		end
		return GameState.new(@labyrinth.to_s, allPlayers, allMonsters, @currentPlayerIndex, self.finished, @log)
		
	end
	
	def configureLabyrinth
		nRows=7
		nCols=7
		@labyrinth=Labyrinth.new(nRows,nCols,nRows-1,nCols-1)
		#puts @labyrinth.to_s
	end
	
	def nextPlayer
		@players[@currentPlayerIndex]= @currentPlayer
		if @currentPlayerIndex+1==@players.size
			@currentPlayerIndex=0
		else
			@currentPlayerIndex+=1
		end
		@currentPlayer=players[@currentPlayerIndex]
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
		@log+="Se han producido " + rounds + " de " + max + "\n"
	end
end

g=Game.new(3)
g.configureLabyrinth

