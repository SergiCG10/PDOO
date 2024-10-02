# Clase Dice
class Dice
    @@MAX_USES = 5; 
    @@MAX_INTELLIGENCE = 10.0;
    @@MAX_STRENGTH = 10.0;
    @@RESURRECT_PROB = 0.3;
    @@WEAPONS_REWARD = 2;
    @@SHIELDS_REWARD = 3;
    @@HEALTH_REWARD = 5;
    @@MAX_ATTACK =3;
    @@MAX_SHIELD = 2;
    
    @@generator = Random.new;
    
    def self.randomPos(max)
    	return @@generator.rand(max);
    end
    
    def self.whoStarts(nPlayers)
    	return @@generator.rand(nPlayers);
    end
    
    def self.randomIntelligence
    	return @@generator.rand*@@MAX_INTELLIGENCE;
    end
    
    def self.randomStrength
    	return @@generator.rand*@@MAX_STRENGTH;
    end
    
    def self.resurrectPlayer
    	return @@generator.rand < @@RESURRECT_PROB;
    end
    
    def self.weaponsReward
    	return @@generator.rand(@@WEAPONS_REWARD+1);
    end
    
    def self.shieldsReward
    	return @@generator.rand(@@SHIELDS_REWARD+1);
    end
    
    def self.healthReward
    	return @@generator.rand(@@HEALTH_REWARD+1);
    end
    
    def self.weaponPower
    	return @@generator.rand * @@MAX_ATTACK;
    end
    
    def self.shieldPower
    	return @@generator.rand * @@MAX_SHIELD;
    end
    
    def self.usesLeft
    	return @@generator.rand(@@MAX_USES+1);
    end
    
    def self.intensity(competence)
    	return @@generator.rand * competence;
    end
    
    def self.discardElement(usesLeft)
    	discard = true;
    	if (usesLeft == @@MAX_USES)
    	    discard = false;
    	elsif(usesLeft != 0)
    	    discard = @@generator.rand < 1 - usesLeft.to_f/@@MAX_USES;
    	end
    	return discard; 
    end
end

