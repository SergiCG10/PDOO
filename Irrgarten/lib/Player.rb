# Clase Player

# Clase Player. Representación de la clase player con sus métodos y variables 
# 
# @author Miguel Ángel Luque Gómez
# correo e.mangelluqg@go.ugr.es
  
# @author Sergio Calvo González
# correo e.sergiocg10@go.ugr.es
 
require_relative 'Weapon'
require_relative 'Shield'

class Player
	
	@@INVALID_POS=-1
	@@MAX_WEAPONS=2
	@@MAX_SHIELDS=3
	@@INITIAL_HEALTH=3
	@@HITS2LOSE=3
	@weapons
	@shields
	
     
     # Constructor por parámetro de la clase Player.
     # 
     # @param nmb Número del jugador
     # @param howsmart Cómo de inteligente es
     # @param howstrong Cómo de fuerte es    
     #
	def initialize(nmb, howsmart, howstrong)
		@name="Player#"+nmb
		@number=nmb
		@intelligence=howsmart
		@strength=howstrong
		@health=@@INITIAL_HEALTH
		@row=@@INVALID_POS
		@col=@@INVALID_POS
		@consecutiveHits=0
		@weapons=Array.new
		@shields=Array.new
	end
	
	def resurrect
		@weapons.clear
		@shields.clear
		@health=@@INITIAL_HEALTH
		#resetHits
	end
	
	attr_reader :row
	attr_reader :col
	attr_reader :number
	attr_accessor :weapons
	attr_accessor :shields
	
	def setPos(r,c)
		@row=r
		@col=c
	end
	
	def dead
		@health <=0
	end
	
	def attack
		return @strength+sumWeapons
	end
	
	def defend(receivedAttack)
		return manageHit(receivedAttack)
	end
	
	def toString
		info ="\n"+@name+"\n"
		info +="\nIntelligence: " + @intelligence.to_s
		info +="\nStregth: " + @strength.to_s
		info +="\nHealth: " + @health.to_s
		info +="\nPosition: (" + @row.to_s + ", " + @col.to_s + ")"
		info +="\n\nWeapons:\n"
		weapons.each do |weapon| 
			info+=weapon.to_s
			info+="\n"
		end
		info +="\n\nShields:\n"
		shields.each do |shield| 
			info+=shield.to_s
			info+="\n"
		end
		return info
	end
	
	def newWeapon
		return Weapon.new(Dice.weaponPower, Dice.usesLeft)
	end
	
	def newShield
		return Shield.new(Dice.shieldPower, Dice.usesLeft)
	end
	
	def sumWeapons
		totalAttack=0
		weapons.each do |weapon|
			totalAttack+=weapon.attack
		end
		return totalAttack
	end
	
	def sumShields
		totalDefense=0
		shields.each do |shield|
			totalDefense+=shield.protect
		end
		return totalDefense
	end
	
	def defensiveEnergy
		return @intelligence + sumShields
	end
	
	#Implementación P3
	def manageHit(receivedAttack)
		return false
	end
	
	def resetHits
		@consecutiveHits=0
	end
	
	def gotWounded
		@health-=1
	end
	
	def incConsecutiveHits
		@consecutiveHits+=1
	end
		
end
p=Player.new("1", 3,2)
w=Weapon.new(2,3)
s=Shield.new(1,2)
w1=p.newWeapon
w2=p.newWeapon
s1=p.newShield
s2=p.newShield
s3=p.newShield
p.shields.push(s1)
p.shields.push(s2)
p.shields.push(s3)
puts p.toString
puts "\nsuma defensa escudos"
sum=p.sumShields
puts sum

