require_relative 'Dice'
require_relative 'Player'
require_relative 'Directions'
require_relative 'Orientations'
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
     
     def spreadPlayers(players)
     	players.each do |p|
     		pos = randomEmptyPos
     		putPlayer2D(-1, -1, pos[@@ROW], pos[@@COL], p)
     	end
     end
     
     def haveAWinner
     	return @players[@exitRow][@exitCol] != nil;
     end
     
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
     
     def putPlayer(direction, player)
     	oldRow = player.getRow
     	oldCol = player.getCol
     	newPos = dir2Pos(oldRow, oldCol, direction)
     	m = putPlayer2D(oldRow, oldCol, newPos[@@ROW], newPos[@@COL], player)
     	return m 	
     end
     
     def addBlock(orientation, startRow, startCol, length)
     	
     	if orientation == Orientations::VERTICAL
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
     
     def getRows
     	return @nRows
     end
     
     def getCols
     	return @nCols
     end
     
     private 
     
     def posOK(row, col)
     	return  (row < @nRows && row >= 0) && (col < @nCols && col >= 0)
     end
     
     def emptyPos(row, col)
     	return (@labyrinth[row][col] == @@EMPTY_CHAR)
     end
     
     def monsterPos(row, col)
     	return (@labyrinth[row][col] == @@MONSTER_CHAR)
     end
     
     def exitPos(row, col)
     	return row == @exitRow && col == @exitCol
     end
     
     def combatPos(row, col)
     	return @labyrinth[row][col] == @@COMBAT_CHAR
     end
     
     def canStepOn(row, col)
     	return posOK(row, col) && (emptyPos(row, col) || exitPos(row, col) || monsterPos(row, col) ) 
     end
     
     def updateOldPos(row, col)
     	if( posOK(row, col) )
     		if combatPos(row, col)
     			@labyrinth[row][col] = @@MONSTER_CHAR;
     		else
     			@labyrinth[row][col] = @@EMPTY_CHAR
     		end
     	end
     end
     
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
