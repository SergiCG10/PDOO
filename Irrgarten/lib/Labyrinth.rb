require_relative 'Dice'
require_relative 'Player'
require_relative 'Directions'
require_relative 'Orientation'
# Clase Labyrinth. Implementación de la clase Labyrinth, de sus métodos y variables.
# 
# @author Miguel Ángel Luque Gómez
# correo e.mangelluqg@go.ugr.es
# 
# @author Sergio Calvo González
# correo e.sergiocg10@go.ugr.es
#

class Labyrinth
     @@BLOCK_CHAR = 'X';
     @@EMPTY_CHAR = '-';
     @@MONSTER_CHAR = 'M';
     @@COMBAT_CHAR = 'C';
     @@EXIT_CHAR = 'E';
     @@ROW = 0;
     @@COL = 1;
     
       # Inicializa el laberinto con las dimensiones especificadas y crea las estructuras para almacenar los monstruos, jugadores y el estado de cada casilla.
     def initialize(rows, cols, eRow, eCol)
     	@nRows = rows;
     	@nCols = cols;
     	@exitRow = eRow;
     	@exitCol = eCol;
     	
     	@players = Array.new(@nRows){Array.new(@nCols, nil)};
     	@monsters = Array.new(@nRows){Array.new(@nCols, nil)};
     	@labyrinth = Array.new(@nRows){Array.new(@nCols, @@EMPTY_CHAR)};
     	
     	@labyrinth[@exitRow][@exitCol] = @@EXIT_CHAR
     end
     
     # Esparce de forma aleatoria los Player por el laberinto
     # @param players Numero de player a colocar
    def spreadPlayers(players)
     	players.each do |p|
     		pos = randomEmptyPos
     		putPlayer2D(-1, -1, pos[@@ROW], pos[@@COL], p)
     	end
     end
     
     # Verifica si hay un jugador en la casilla de salida, indicando si hay un ganador.
     # @return [Boolean] true si algún jugador está en la casilla de salida, false en caso contrario.
     def haveAWinner
     	return @players[@exitRow][@exitCol] != nil;
     end
     
     # Genera una representación en cadena del estado completo del laberinto.
     # @return [String] Representación del laberinto.
     def to_s
     	laberinto = "";
     	@nRows.times do |i|
     		@nCols.times do |j|
    			laberinto+= "[" + @labyrinth[i][j] + "]" 			
       		end
       		laberinto+="\n";
     	end
     	
     	return laberinto;
     end 
     
     # Añade un monstruo en la posición especificada, si la posición está dentro del laberinto y está vacía.
     # @param row [Integer] Fila de la posición.
     # @param col [Integer] Columna de la posición.
     # @param monster [Monster] El monstruo a añadir.
     def addMonster(row, col, monster)
     	if(posOK(row,col))
           if(emptyPos(row,col))
               @monsters[row][col] = monster;
               @labyrinth[row][col]= @@MONSTER_CHAR
               monster.setPos(row, col)
           else
               pos= randomEmptyPos();
               monster.setPos(pos[@@ROW], pos[@@COL]);
               @monsters[pos[@@ROW]][pos[@@COL]]=monster;
               @labyrinth[pos[@@ROW]][pos[@@COL]]=@@MONSTER_CHAR;
           
           end
       	end
     	
     	
     end
     
      # Mueve al jugador en la dirección indicada, calculando la nueva posición
      #
      # @param direction [Directions] Dirección en la que se desea mover al jugador.
      # @param player [Player] Jugador que se va a mover.
      # @return [Monster, nil] Devuelve el monstruo en la nueva posición si hay un combate; de lo contrario, devuelve nil.
      #
      # El método realiza los siguientes pasos:
      # - Obtiene las coordenadas actuales del jugador (fila y columna).
      # - Calcula la nueva posición en base a la dirección dada usando `dir2Pos`.
      # - Llama a `putPlayer2D` para realizar el movimiento y gestionar la posición anterior y la interacción en la nueva posición.
      # - Retorna el monstruo encontrado en la nueva posición si ocurre un combate; de lo contrario, retorna nil.
     def putPlayer(direction, player)
     	oldRow = player.getRow
     	oldCol = player.getCol
     	newPos = dir2Pos(oldRow, oldCol, direction)
     	m = putPlayer2D(oldRow, oldCol, newPos[@@ROW], newPos[@@COL], player)
     	return m 	
     end
     
     # Añade una serie de bloques a partir de la posicion especificada en una orientación.
     # @param length Numero de bloques que se colocan
     # @param orientation Orientacion en que se colocan los bloques
     def addBlock(orientation, startRow, startCol, length)
     	
     	if orientation == Orientation::VERTICAL
 			incRow = 1
 			incCol = 0
     	else
 			incRow = 0
 			incCol = 1
     	end
     	
     	row = startRow
     	col = startCol
     	
     	while ((posOK(row, col) && emptyPos(row, col))) && (length > 0) do 
 			@labyrinth[row][col] = @@BLOCK_CHAR
 			length -= 1
 			row += incRow
 			col += incCol
     	end
     end
     
     # Devuelve un array con las direcciones posibles que se puede ir desde la posicion actual
     def validMoves(row, col)
     	output = []
     	
     	if canStepOn(row +1, col)
     		output.push(Directions::DOWN)
     	end
     	
     	if canStepOn(row -1, col)
     		output.push(Directions::UP)
     	end
     	
     	if canStepOn(row, col+1)
     		output.push(Directions::RIGHT)
     	end
     	
     	if canStepOn(row, col-1)
     		output.push(Directions::LEFT)
     	end
     
     	return output
     end
     
     # Devuelve el numero de filas del laberinto
     def getRows
     	return @nRows
     end
     
     #Devuelve el numero de columnas del laberinto
     def getCols
     	return @nCols
     end
     #establece en el laberinto el nuevo fuzzyplayer
     #@param player FuzzyPlayer que se va a incluir
     def convertToFuzzy(player)
        players[player.row][player.col]=player
     end
     
private 

     # Verifica si la posición especificada está dentro de los límites del laberinto.
     # @param row [Integer] Fila de la posición.
     # @param col [Integer] Columna de la posición.
     # @return [Boolean] true si la posición está dentro del laberinto, false en caso contrario.
     def posOK(row, col)
     	return  (row < @nRows && row >= 0) && (col < @nCols && col >= 0)
     end
     
     # Verifica si la posición especificada está vacía.
     # @param row [Integer] Fila de la posición.
     # @param col [Integer] Columna de la posición.
     # @return [Boolean] true si la posición está vacía, false en caso contrario
     def emptyPos(row, col)
     	return (@labyrinth[row][col] == @@EMPTY_CHAR)
     end
     
     # Verifica si la posición especificada contiene exclusivamente un monstruo.
     # @param row [Integer] Fila de la posición.
     # @param col [Integer] Columna de la posición.
     # @return [Boolean] true si la posición contiene un monstruo, false en caso contrario.
     def monsterPos(row, col)
     	return (@labyrinth[row][col] == @@MONSTER_CHAR)
     end
     
     # Verifica si la posición especificada es la casilla de salida.
     # @param row [Integer] Fila de la posición.
     # @param col [Integer] Columna de la posición.
     # @return [Boolean] true si la posición es la casilla de salida, false en caso contrario.
     def exitPos(row, col)
     	return row == @exitRow && col == @exitCol
     end
     
     # Verifica si la posición especificada contiene tanto un monstruo como un jugador (estado de combate).
     # @param row [Integer] Fila de la posición.
     # @param col [Integer] Columna de la posición.
     # @return [Boolean] true si la posición contiene un combate, false en caso contrario.
     def combatPos(row, col)
     	return @labyrinth[row][col] == @@COMBAT_CHAR
     end
     
     # Indica si la posición es válida para que un jugador se mueva a ella (vacía, con un monstruo o salida).
     # @param row [Integer] Fila de la posición.
     # @param col [Integer] Columna de la posición.
     # @return [Boolean] true si es una posición válida para moverse, false en caso contrario.
     def canStepOn(row, col)
     	return posOK(row, col) && (emptyPos(row, col) || exitPos(row, col) || monsterPos(row, col) ) 
     end
     
     # Actualiza el estado de la posición anterior después de que un jugador se haya movido, marcándola como vacía o como solo ocupada por un monstruo.
     # @param row [Integer] Fila de la posición.
     # @param col [Integer] Columna de la posición.
     def updateOldPos(row, col)
     	if( posOK(row, col) )
     		if combatPos(row, col)
     			@labyrinth[row][col] = @@MONSTER_CHAR;
     		else
     			@labyrinth[row][col] = @@EMPTY_CHAR
     		end
     	end
     end
     
     # Calcula la nueva posición a la que se llegaría si se avanza en la dirección especificada desde la posición actual.
     # @param row [Integer] Fila actual.
     # @param col [Integer] Columna actual.
     # @param direction [Directions] Dirección en la que se avanza.
     # @return [Array<Integer>] Coordenadas [fila, columna] de la nueva posición
     def dir2Pos(row, col, direction)
     	pos =Array.new(2);
     	pos[@@ROW]=row
     	pos[@@COL]=col
     	if(direction == Directions::LEFT)
     		pos[@@COL] -= 1;
     	elsif(direction == Directions::RIGHT)
     		pos[@@COL] += 1;
     	elsif(direction == Directions::UP)
 			pos[@@ROW] -= 1;
     	else
     		pos[@@ROW] += 1;
     	end
     	return pos
     end
     
     # Genera una posición aleatoria vacía dentro del laberinto.
     # @return [Array<Integer>] Coordenadas [fila, columna] de una posición vacía.
     # @raise [RuntimeError] Si no hay posiciones vacías (podría causar un bucle infinito).
     def randomEmptyPos
     	pos = Array.new(2);
        pos[@@ROW]=Dice.randomPos(@nRows);
        pos[@@COL]=Dice.randomPos(@nCols);
        while !emptyPos(pos[@@ROW],pos[@@COL]) do 
            pos[@@ROW]=Dice.randomPos(@nRows);
            pos[@@COL]=Dice.randomPos(@nCols);  
        end
        return pos;
     end
     
      # Mueve un jugador de una posición antigua a una nueva en el laberinto.
      #
      # @param oldRow [Integer] Fila de la posición anterior del jugador.
      # @param oldCol [Integer] Columna de la posición anterior del jugador.
      # @param row [Integer] Fila de la nueva posición a la que se mueve el jugador.
      # @param col [Integer] Columna de la nueva posición a la que se mueve el jugador.
      # @param player [Player] Jugador que se está moviendo.
      # @return [Monster, nil] Devuelve el monstruo en la nueva posición si hay un combate; de lo contrario, devuelve nil.
      #
      # El método realiza los siguientes pasos:
      # - Verifica si el jugador puede moverse a la nueva posición con `canStepOn`.
      # - Comprueba si la posición antigua es válida y contiene al jugador, actualizando su estado en el laberinto y marcando
      #   la posición como vacía.
      # - Si la nueva posición contiene un monstruo (`monsterPos`), marca la casilla en estado de combate y devuelve el monstruo encontrado.
      # - Si no hay monstruo en la nueva posición, establece el número del jugador en el laberinto.
      # - Actualiza la posición actual del jugador en el laberinto y en sus atributos.
     def putPlayer2D(oldRow, oldCol, row, col, player)
     	output = nil
     	if canStepOn(row, col)
     		if posOK(oldRow, oldCol)
     			p = @players[oldRow][oldCol]
     			if p == player
     				updateOldPos(oldRow, oldCol)
     				@players[oldRow][oldCol] = nil
     			end
     		end
     		
     		monsterPos = monsterPos(row, col)
     		if monsterPos
     			@labyrinth[row][col] = @@COMBAT_CHAR
     			output = @monsters[row][col]
     		else
     			number = player.getNumber
     			@labyrinth[row][col] = number
     		end
     		@players[row][col] = player
     		player.setPos(row, col)
     	end
     	return output
     end

public     
     #Muestra una representación mas realista del laberinto y de los caracteres que lo componen
     def toRealRepresentation
     	laberinto = ""
     	(@nCols +2).times do |i|
            laberinto += " ■";
        end
        laberinto += "\n";
        @nRows.times do |i|
            laberinto += " ■";
             @nCols.times do |j|
                if(@labyrinth[i][j] == @@BLOCK_CHAR )
                    laberinto += " ■";
                elsif(@labyrinth[i][j] == @@EMPTY_CHAR)
                    laberinto += " □";
                elsif(@labyrinth[i][j] == @@MONSTER_CHAR)
                    laberinto += " M";
                elsif(@labyrinth[i][j]== @@COMBAT_CHAR)
                    laberinto += " ⚔";
                elsif (@labyrinth[i][j]== @@EXIT_CHAR)
                    laberinto += " ✪";
                else
                    laberinto += " "+@labyrinth[i][j];
                end
            end
            laberinto += " ■\n";
        end
        (@nCols +2).times do |i|
            laberinto += " ■";
        end
        laberinto += "\n";
     	return laberinto
     end
     #private :posOK , :emptyPos, :monsterPos, :exitPos, :combatPos, :canStepOn, :updateOldPos, :dir2Pos, :randomEmptyPos
end
