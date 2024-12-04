require_relative 'Player'

class FuzzyPlayer < Player 
	
	def initialize(other) 
		copy(other)
	end
	
	def move(direction, validMoves)
		move = Dice.nextStep(super, validMoves, getIntelligence)
		return move
	end
	
	def attack
		return Dice.intensity(getStrength) + sumWeapons
	end
	
	def defensiveEnergy
		return Dice.intensity(getIntelligence) + sumShields
	end
	
	def to_s
		return "\nFuzzy" + super
	end
	
	protected :defensiveEnergy
end
