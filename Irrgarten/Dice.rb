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
    
    def initialize
    	@generator = Random.new;
    end
    
    def randomPos(max)
    	return @generator.rand(max);
    end
    
    def whoStarts(nPlayers)
    	return @generator.rand(nPlayers);
    end
    
    def randomIntelligence
    	return @generator.rand*@@MAX_INTELLIGENCE;
    end
    
    def randomStrength
    	return @generator.rand*@@MAX_STRENGTH;
    end
    
    def resurrectPlayer
    	return @generator.rand < @@RESURRECT_PROB;
    end
    
    def weaponsReward
    	return @generator.rand(@@WEAPONS_REWARD+1);
    end
    
    def shieldsReward
    	return @generator.rand(@@SHIELDS_REWARD+1);
    end
    
    def healthReward
    	return @generator.rand(@@HEALTH_REWARD+1);
    end
    
    def weaponPower
    	return @generator.rand * @@MAX_ATTACK;
    end
    
    def shieldPower
    	return @generator.rand * @@MAX_SHIELD;
    end
    
    def usesLeft
    	return @generator.rand(@@MAX_USES+1);
    end
    
    def intensity(competence)
    	return @generator.rand * competence;
    end
    
    def discardElement(usesLeft)
    	discard = true;
    	if (usesLeft == @@MAX_USES)
    	    discard = false;
    	elsif(usesLeft != 0)
    	    discard = @generator.rand < 1 - usesLeft.to_f/@@MAX_USES;
    	end
    	return discard; 
    end
end

dc = Dice.new
n = 10
# n.times do puts dc.randomPos(10) end
# n.times do puts dc.whoStarts(5) end
# n.times do puts dc.randomIntelligence end
# n.times do puts dc.randomStrength end
# n.times do puts dc.resurrectPlayer end
# n.times do puts dc.weaponsReward end
# n.times do puts dc.shieldsReward end
# n.times do puts dc.healthReward end
# n.times do puts dc.weaponPower end
# n.times do puts dc.shieldPower end
# n.times do puts dc.usesLeft end
# n.times do puts dc.intensity(0.7) end
i=5;
5.times do puts dc.discardElement(i); i-=1; end
