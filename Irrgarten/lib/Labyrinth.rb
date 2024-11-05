require_relative 'Dice'
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
     	
     	@labyrinth[@exitRow][@exitCol] = @@EXIT_CHAR;
     end
     
     #def spreadPlayers(players)
     	
     #end
     
     def haveAWinner
     	return @players[@exitRow][@exitCol] != nil;
     end
     
     def to_s
     	laberinto = "";
     	@nRows.times do |i|
     		@nCols.times do |j|
    			laberinto+=@labyrinth[i][j]; 			
       		end
       		laberinto+="\n";
     	end
     	
     	return laberinto;
     end 
     
     def addMonster(row, col, monster)
     	@monsters[row][col] = monster;
     end
     
     #def putPlayer(direction, player)
     	
     #end
     
     #def addBlock(orientation, startRow, startCol, length)
     
     #end
     
     #def validMoves(row, col)
     #end
     
     #private 
     
     def posOK(row, col)
     	return  (row < @nRows && row >= 0) && (col < @nCols && col >= 0)
     end
     
     def emptyPos(row, col)
     	return (@labyrinth[row][col] == @@EMPTY_CHAR)
     end
     
     def monsterPos(row, col)
     	return (@monsters[row][col] != nil)
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
     	pos = [ row, col];
     	
     	if(direction == Directions.left)
     		pos[@@COL] -= 1;
     	elsif(direction == Directions.right)
     		pos[@@COL] += 1;
     	elsif(direction == Directions.up)
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
     
     #def putPlayer2D(oldRow, oldPos, row, col, player)
     #end
     
     #private : posOK , :emptyPos, :monsterPos, :exitPos, :combatPos, :canStepOn, :updateOldPos, :dir2Pos, :randomEmptyPos
end

lab = Labyrinth.new(10,10,9,9)
puts lab.to_s
