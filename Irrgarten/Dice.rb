# Clase Dice

# Clase Dice. Representación de la clase dice con sus métodos y variables 
# 
# @author Miguel Ángel Luque Gómez
# correo e.mangelluqg@go.ugr.es
  
# @author Sergio Calvo González
# correo e.sergiocg10@go.ugr.es
 

class Dice
    @@MAX_USES = 5; 		#Número máximo de usos de un objeto
    @@MAX_INTELLIGENCE = 10.0;  #Número máximo de inteligencia para un personaje
    @@MAX_STRENGTH = 10.0;	#Número máximo de fuerza para un personaje
    @@RESURRECT_PROB = 0.3;	#Probabilidad de resucitar de un personaje
    @@WEAPONS_REWARD = 2;	#Número de armas máximas como recompensa
    @@SHIELDS_REWARD = 3;	#Número de escudos máximos como recompensa
    @@HEALTH_REWARD = 5;	#Número de vidas máximas como recompensa
    @@MAX_ATTACK =3;		#Número máximo de ataque para un arma
    @@MAX_SHIELD = 2;		#Número máximo de protección para un escudo
    
    @@generator = Random.new;	#Variable generadora de números aleatorios 

# Funcion randomPos. Devuelve un valor que muestra el valor de fila o 
# de columna donde empezará el jugador
# @param max Número de filas o de columnas del laberinto
# @return valor entero aletorio en el intervalo [0, max)

    def self.randomPos(max)
    	return @@generator.rand(max);
    end

# Funcion whoStars. Devuelve un número que muestra que jugador empieza.
# @param nplayers Número de jugadores en la partida
# @return Un número entero aleatorio en el intervalo [0, nplayers)

    def self.whoStarts(nPlayers)
    	return @@generator.rand(nPlayers);
    end
    
# Funcion randomIntelligence. Devuelve un valor aleatorio que mostrará 
# la inteligencia del personaje
# @return Un número aleatorio con coma flotante en el intervalo [0, MAX_INTELLIGENCE)

    def self.randomIntelligence
    	return @@generator.rand*@@MAX_INTELLIGENCE;
    end
    
# Funcion randomStrength. Devuelve en valor aleatorio que mostrará 
# la fuerza del personaje
# @return Un número aleatorio con coma flotante en el intervalo [0, MAX_STRENGTH)
    
    def self.randomStrength
    	return @@generator.rand*@@MAX_STRENGTH;
    end

# Funcion resurrectPlayer. Devuelve si el personaje debe de revivir.
# La probabilidad se calcula de forma que si un float aleatorio es 
# menor que la probabilidad de revivir, devuelve true, si no, false.
# @return True si el personaje revive, false si no. 

    def self.resurrectPlayer
    	return @@generator.rand < @@RESURRECT_PROB;
    end

# Funcion weaponsReward. Devuelve el número aleatorio de armas que recibe como recompensa
# @return Valor entero aleatorio en el intervalo [0, WEAPONS_REWARD]

    def self.weaponsReward
    	return @@generator.rand(@@WEAPONS_REWARD+1);
    end

# Funcion shieldsReward. Devuelve el número aleatorio de escudos que recibe como recompensa
# @return Valor entero aleatorio en el intervalo [0, SHIELDS_REWARD]

    def self.shieldsReward
    	return @@generator.rand(@@SHIELDS_REWARD+1);
    end

# Funcion healthReward. Devuelve el número aleatorio de vidas que recibe como recompensa
# @return Valor entero aleatorio en el intervalo [0, HEALTH_REWARD]

    def self.healthReward
    	return @@generator.rand(@@HEALTH_REWARD+1);
    end

# Funcion weaponPower. Devuelve un valor que representará el valor de daño de un arma
# @return Valor con coma flotante aleatorio en el intervalo [0, MAX_ATTACK)

    def self.weaponPower
    	return @@generator.rand * @@MAX_ATTACK;
    end

# Funcion weaponPower. Devuelve un valor que representará el valor de defensa de un escudo
# @return Valor con coma flotante aleatorio en el intervalo [0, MAX_SHIELD)

    def self.shieldPower
    	return @@generator.rand * @@MAX_SHIELD;
    end

# Funcion usesLeft. Devuelve el número aleatorio de usos restantes de un arma o escudo
# @return Número entero aleatorio en el intervalo [0, MAX_USES]

    def self.usesLeft
    	return @@generator.rand(@@MAX_USES+1);
    end

# Funcion intensity. Devuelve un valor aleatorio que muestra el grado de intensidad de una acción
# @param competence
# @return Valor  aleatorio con coma flotante en el intervalo [0, competence)

    def self.intensity(competence)
    	return @@generator.rand * competence;
    end

# Funcion discardElement. Muestra true si se debe de descartar o no un objeto. 
# Se calcula si se decarta de forma que :
# Si tiene el número máximo de usos, no se descartará, 
# Si tiene 0 se descartará de forma asegurada
# En cualquier otro caso, la probabilidad de que se descarte será 
# proporcionalmente inversa al número de usos que le quedan respecto 
# al número máximo de usos. 
# @param usesLeft Número de usos restantes
# @return True si se descarta, false si no

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

