package irrgarten;
import java.util.ArrayList;
/**
 * Clase Labyrinth. Esta clase contiene la implementación de la variable labyrinth y sus 
 * funciones básicas para la utilización de la clase
 * 
 * @author Miguel Ángel Luque Gómez
 * correo: e.mangelluqg@go.ugr.es
 * 
 * @author Sergio Calvo González
 * correo: e.sergiocg10@go.ugr.es
 */  
public class Labyrinth {
   private static final char BLOCK_CHAR='X';
   private static final char EMPTY_CHAR='-';
   private static final char MONSTER_CHAR='M';
   private static final char COMBAT_CHAR='C';
   private static final char EXIT_CHAR='E';
   private static final int ROW=0;
   private static final int COL=1;
   private int nRows;
   private int nCols;
   private int exitRow;
   private int exitCol;
   private char labyrinth[][];
   private Monster monsters[][];
   private Player players[][];
   
   public Labyrinth(int nrows, int ncols, int erow, int ecol){
       nRows=nrows;
       nCols=ncols;
       exitRow=erow;
       exitCol=ecol;
       labyrinth=new char[nRows][nCols];
       monsters=new Monster[nRows][nCols];
       players=new Player[nRows][nCols];
       for(int i=0;i<nRows;i++){
           for(int j=0;j<nCols;j++){
               labyrinth[i][j]=EMPTY_CHAR;
           }
       }
       labyrinth[erow][ecol]=EXIT_CHAR;
   }
   public boolean haveAWinner(){
       return players[exitRow][exitCol]!=null;
   }
   public String toString(){
       String state="";
       for(int i=0;i<nRows;i++){
           for(int j=0;j<nCols;j++){
               state+="["+labyrinth[i][j]+"]";
           }
           state+="\n";
       }
   return state;
   }
   public void addMonster(int row, int col, Monster monster){
       if(posOK(row,col))
           if(emptyPos(row,col)){
               monsters[row][col]=monster;
               labyrinth[row][col]=MONSTER_CHAR;
           }
   }
   public boolean posOK(int row, int col){
      return(row>=0 && row <nRows && col>=0 && col<nCols); 
   }
   public boolean emptyPos(int row, int col){
       return(labyrinth[row][col]==EMPTY_CHAR);
   }
   public boolean monsterPos(int row, int col){
       return(labyrinth[row][col]==MONSTER_CHAR);
   }
   public boolean exitPos(int row, int col){
       return(labyrinth[row][col]==EXIT_CHAR);
   }
   public boolean combatPos(int row, int col){
       return(labyrinth[row][col]==COMBAT_CHAR);
   }
   public boolean canStepOn(int row, int col){
       return(posOK(row,col) && (emptyPos(row,col) || 
              monsterPos(row,col) || exitPos(row,col))); 
   }
   public void updateOldPos(int row, int col){
       if(posOK(row,col)){
           if(labyrinth[row][col]== COMBAT_CHAR){
               labyrinth[row][col]=MONSTER_CHAR;
           }
           else{
               labyrinth[row][col]=EMPTY_CHAR;
           }
       }
   }
   public int[] dir2Pos(int row, int col, Directions direction){
       int pos[]=new int[2];
       if(Directions.UP==direction){
           pos[ROW]=row-1;
           pos[COL]=col;
       }
       else if(Directions.DOWN==direction){
           pos[ROW]=row+1;
           pos[COL]=col;
       }
       else if(Directions.RIGHT==direction){
           pos[ROW]=row;
           pos[COL]=col+1;
       }
       else{
           pos[ROW]=row;
           pos[COL]=col-1;
       }
       return pos;
   }

    public int[] randomEmptyPos(){
        int pos[]=new int[2];
        pos[ROW]=Dice.randomPos(nRows);
        pos[COL]=Dice.randomPos(nCols);
        while(!emptyPos(pos[ROW],pos[COL])){
            pos[ROW]=Dice.randomPos(nRows);
            pos[COL]=Dice.randomPos(nCols);  
        }
        return pos;
    }

}

