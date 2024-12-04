require_relative 'Player'

class FuzzyPlayer < Player 
	
	def initialize(other) 
		copy(other)
	end
	
	def move(direction, validMoves)
		move = Dice.nextStep(super, validMoves, intelligence)
		return move
	end
	
	def attack
		return Dice.intensity(strength) + sumWeapons
	end
	
	def defensiveEnergy
		return Dice.intensity(intelligence) + sumShields
	end
	
	def to_s
		return "\nFuzzy" + super
	end
	
	protected :defensiveEnergy
end
