require_relative 'Dice'
require_relative 'Weapon'
require_relative 'Shield'
require_relative 'Directions'
require_relative 'GameState'
require_relative 'GameCharacter'
require_relative 'Orientations'

w1=Weapon.new(3,4)
s1=Shield.new(2,2)
gm=GameCharacter::PLAYER;
puts gm
#Comprobando la clase Weapon
n=100
w1.to_s
puts w1
w1.attack
puts w1
w1.discard

#Comprobando la clase Shield
s1.to_s
puts s1
s1.protect
puts s1
s1.discard

#Comprobando la clase Dice
n.times do puts Dice.randomPos(10) end
n.times do puts Dice.whoStarts(5) end
n.times do puts Dice.randomIntelligence end
n.times do puts Dice.randomStrength end
n.times do puts Dice.resurrectPlayer end
n.times do puts Dice.weaponsReward end
n.times do puts Dice.shieldsReward end
n.times do puts Dice.healthReward end
n.times do puts Dice.weaponPower end
n.times do puts Dice.shieldPower end
n.times do puts Dice.usesLeft end
n.times do puts Dice.intensity(0.7) end
i=5;
5.times do puts Dice.discardElement(i); i-=1;
end
