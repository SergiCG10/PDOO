# Clase Game. Esta clase contiene la implementación de la variable game y sus
# funciones básicas para la utilización de la clase
#
# @author Miguel Ángel Luque Gómez
# correo e.mangelluqg@go.ugr.es
#  
# @author Sergio Calvo González
# correo e.sergiocg10@go.ugr.es

require_relative 'Player'
require_relative 'GameState'
class Game
	@@MAX_ROUNDS=10
	@monsters=Array.new
	@currentPlayer
	@labyrinth
	
	def initialize (nplayers)
		@players=Array.new(nplayers)
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
		@labyrinth.haveAWinner
	end
	
	def getGameState
		allPlayers=""
		allMonsters=""
		@players.each do |player|
			allPlayers+=player.toString
		end
		@monsters.each do |monster|
			allMonsters+=monster.toString
		end
		return Gamestate.new(@labyrinth.toString, allPlayers, allMonsters, @currentPlayer)
	end


end

g=Game.new(3)

