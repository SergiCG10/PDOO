require_relative 'Dice'
require_relative 'Weapon'
require_relative 'Shield'
require_relative 'Directions'
require_relative 'GameState'
require_relative 'GameCharacter'
require_relative 'Orientations'

dc=Dice.new
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
n.times do puts dc.randomPos(10) end
n.times do puts dc.whoStarts(5) end
n.times do puts dc.randomIntelligence end
n.times do puts dc.randomStrength end
n.times do puts dc.resurrectPlayer end
n.times do puts dc.weaponsReward end
n.times do puts dc.shieldsReward end
n.times do puts dc.healthReward end
n.times do puts dc.weaponPower end
n.times do puts dc.shieldPower end
n.times do puts dc.usesLeft end
n.times do puts dc.intensity(0.7) end
i=5;
5.times do puts dc.discardElement(i); i-=1;
end
