# Clase Player

# Clase Player. Representación de la clase player con sus métodos y variables 
# 
# @author Miguel Ángel Luque Gómez
# correo e.mangelluqg@go.ugr.es
  
# @author Sergio Calvo González
# correo e.sergiocg10@go.ugr.es
 
class GameState
		
	#Constructor de la clase GameState
	def initialize(lab, pl, monst, cp, w, l)
		@labyrinth=lab
		@players=pl
		@monsters=monst
		@currentPlayer=cp
		@winner=w
		@log=l
	end
	#Método obtención labyrinth clase GameState
	def getLabyrinth
		return @labyrinth
	end
	#Método obtención players clase GameState
	def getPlayers
		return @players
	end
	#Método obtención monsters clase GameState
	def getMonsters
		return @monsters
	end
	#Método obtención currentPlayer clase GameState
	def getCurrentPlayer
		return @currentPlayer
	end
	#Método obtención winner clase GameState
	def getWinner
		return @winner
	end
	#Método obtención log clase GameState
	def getLog
		return @log
	end
end

