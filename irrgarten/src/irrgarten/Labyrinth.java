package irrgarten;
import java.util.ArrayList;

/**
 * Clase Labyrinth.
 * 
 * Esta clase representa un laberinto en el que pueden moverse jugadores y monstruos. El laberinto 
 * está implementado como un tablero bidimensional de caracteres que indica el estado de cada 
 * casilla, junto con dos tablas adicionales que almacenan la posición de monstruos y jugadores. 
 * 
 * - 'X' representa un obstáculo (bloque).
 * - '-' indica una casilla vacía.
 * - 'M' representa un monstruo.
 * - 'C' indica una casilla donde están presentes tanto un monstruo como un jugador (combate).
 * - 'E' representa la casilla de salida.
 * 
 * Las casillas del laberinto están representadas por arrays bidimensionales que almacenan 
 * tanto las posiciones de los monstruos como de los jugadores.
 * 
 * @author Miguel Ángel Luque Gómez
 * @author Sergio Calvo González
 */

public class Labyrinth {
 private static final char BLOCK_CHAR = 'X';   // Representa un obstáculo
   private static final char EMPTY_CHAR = '-';   // Casilla vacía
   private static final char MONSTER_CHAR = 'M'; // Casilla con un monstruo
   private static final char COMBAT_CHAR = 'C';  // Casilla con un combate
   private static final char EXIT_CHAR = 'E';    // Casilla de salida

   private static final int ROW = 0;             // Constante para acceder a la fila de una posición
   private static final int COL = 1;             // Constante para acceder a la columna de una posición

   private int nRows;        // Número de filas del laberinto
   private int nCols;        // Número de columnas del laberinto
   private int exitRow;      // Fila donde se encuentra la salida
   private int exitCol;      // Columna donde se encuentra la salida

   private char labyrinth[][];         // Matriz que almacena el estado del laberinto
   private Monster monsters[][];       // Matriz que almacena las posiciones de los monstruos
   private Player players[][];         // Matriz que almacena las posiciones de los jugadores

    /**
    * Constructor de la clase Labyrinth.
    * 
    * Inicializa el laberinto con las dimensiones dadas y posiciona la casilla de salida.
    * 
    * @param nrows Número de filas del laberinto
    * @param ncols Número de columnas del laberinto
    * @param erow  Fila donde se encuentra la salida
    * @param ecol  Columna donde se encuentra la salida
    */
   
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
    
   /**
    * Comprueba si hay un ganador. Un jugador gana si está en la casilla de salida.
    * 
    * @return true si hay un jugador en la casilla de salida, false en caso contrario.
    */
   
   public boolean haveAWinner(){
       return players[exitRow][exitCol]!=null;
   }
    
   /**
    * Devuelve una representación en forma de cadena del estado actual del laberinto.
    * 
    * @return String que representa el estado del laberinto.
    */
   
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

   /**
    * Añade un monstruo en una posición del laberinto, si esta está vacía.
    * 
    * @param row     Fila en la que añadir el monstruo.
    * @param col     Columna en la que añadir el monstruo.
    * @param monster Monstruo a añadir.
    */
   
   public void addMonster(int row, int col, Monster monster){
       if(posOK(row,col))
           if(emptyPos(row,col)){
               monsters[row][col]=monster;
               labyrinth[row][col]=MONSTER_CHAR;
           }
   }

   /**
 * Verifica si una posición en el laberinto está dentro de los límites.
 * 
 * @param row Fila a verificar.
 * @param col Columna a verificar.
 * @return true si la posición está dentro de los límites del laberinto, false en caso contrario.
 */
   
   public boolean posOK(int row, int col){
      return(row>=0 && row <nRows && col>=0 && col<nCols); 
   }
   
 /**
 * Verifica si una posición en el laberinto está vacía.
 * 
 * @param row Fila a verificar.
 * @param col Columna a verificar.
 * @return true si la posición está vacía, false en caso contrario.
 */
   
   public boolean emptyPos(int row, int col){
       return(labyrinth[row][col]==EMPTY_CHAR);
   }
   
 /**
 * Verifica si una posición en el laberinto contiene un monstruo.
 * 
 * @param row Fila a verificar.
 * @param col Columna a verificar.
 * @return true si la posición contiene un monstruo, false en caso contrario.
 */
   
   public boolean monsterPos(int row, int col){
       return(labyrinth[row][col]==MONSTER_CHAR);
   }
   
/**
 * Verifica si una posición en el laberinto es la casilla de salida.
 * 
 * @param row Fila a verificar.
 * @param col Columna a verificar.
 * @return true si la posición es la casilla de salida, false en caso contrario.
 */
   
   public boolean exitPos(int row, int col){
       return(labyrinth[row][col]==EXIT_CHAR);
   }
   
 /**
 * Verifica si una posición en el laberinto contiene un combate, es decir, un monstruo y un jugador.
 * 
 * @param row Fila a verificar.
 * @param col Columna a verificar.
 * @return true si la posición contiene un combate, false en caso contrario.
 */
   
   public boolean combatPos(int row, int col){
       return(labyrinth[row][col]==COMBAT_CHAR);
   }
   
 /**
 * Verifica si un jugador puede moverse a una posición en el laberinto.
 * Un jugador puede moverse si la posición está dentro de los límites y 
 * está vacía, contiene un monstruo o es la salida.
 * 
 * @param row Fila a verificar.
 * @param col Columna a verificar.
 * @return true si el jugador puede moverse a la posición, false en caso contrario.
 */
   
   public boolean canStepOn(int row, int col){
       return(posOK(row,col) && (emptyPos(row,col) || 
              monsterPos(row,col) || exitPos(row,col))); 
   }
   
 /**
 * Actualiza el estado de una posición antigua en el laberinto.
 * Si la posición contiene un combate, la actualiza para que contenga solo un monstruo.
 * Si no, la marca como vacía.
 * 
 * @param row Fila a actualizar.
 * @param col Columna a actualizar.
 */
   
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
   
 /**
 * Calcula la nueva posición al moverse en una dirección desde una posición dada.
 * 
 * @param row       Fila de la posición inicial.
 * @param col       Columna de la posición inicial.
 * @param direction Dirección en la que se desea mover.
 * @return Un array de dos enteros, donde el primer valor es la nueva fila y el segundo la nueva columna.
 */
   
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
 
 /**
 * Genera una posición aleatoria en el laberinto que esté vacía.
 * Utiliza un dado para generar una fila y columna aleatoria, y repite hasta encontrar una posición vacía.
 * 
 * @return Un array de dos enteros, donde el primer valor es la fila y el segundo la columna de una posición vacía.
 */

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

