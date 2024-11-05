# Clase Game. Esta clase contiene la implementación de la variable game y sus
# funciones básicas para la utilización de la clase
#
# @author Miguel Ángel Luque Gómez
# correo e.mangelluqg@go.ugr.es
#  
# @author Sergio Calvo González
# correo e.sergiocg10@go.ugr.es

require_relative 'Player'

class Game
	@@MAX_ROUNDS=10
	@players=Array.new
	@monsters=Array.new
	@currentPlayer
	@labyrinth
	
	def initialize (nplayers)
		players.each do |player|
			c=(char)(player+'1')
			p=Player.new(c,Dice.randomIntelligence,Dice.randomStrength)	
			players.push(p)
		end
		@currentPlayerIndex=0
		@log=""
	end


end
