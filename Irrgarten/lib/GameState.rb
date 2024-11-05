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

