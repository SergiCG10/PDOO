package irrgarten;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
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

   private static String magic_str = "LABYRINTHFILE";
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
       return(monsters[row][col] != null);
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
    //P3
    void addBlock(Orientation orientation, int startRow,int startCol, int length){

        int incRow;
        int incCol;
        if(orientation == Orientation.VERTICAL){
            incRow = 1;
            incCol = 0;
        }else{
            incRow = 0;
            incCol = 1;
        }
        int row = startRow;
        int col = startCol;
        while( (posOK(row, col) && emptyPos(row, col)) && length > 0){
            labyrinth[row][col] = BLOCK_CHAR;
            length -= 1;
            row +=incRow;
            col +=incCol;
        }
    }
    
    //P3
    public Monster putPlayer2D(int oldRow, int oldCol, int row, int col, Player player)
    {
        Monster output=null;
        if(this.canStepOn(row, col)){
            if(this.posOK(oldRow, oldCol)){
                Player p=players[oldRow][oldCol];
                if(p==player){
                    this.updateOldPos(oldRow, oldCol);
                    players[oldRow][oldCol]=null;
                }
            }
        
            boolean monsterPos=this.monsterPos(row, col);
            if(monsterPos){
                labyrinth[row][col]=COMBAT_CHAR;
                output=monsters[row][col];
            }
            else{
                char number=player.getNumber();
                labyrinth[row][col]=number;
            }
            
            players[row][col]=player;
            player.setPos(row, col);
            
        }
        return output;
        
    }
    
    //P3
    public Monster putPlayer(Directions direction, Player player)
    {
        int oldRow=player.getRow();
        int oldCol=player.getCol();
        int [] newPos=this.dir2Pos(oldRow, oldCol, direction);
        Monster monster=this.putPlayer2D(oldRow, oldCol, newPos[ROW], newPos[COL], player);
        return monster;
    }
    
    public void spreadPlayers(Player [] players)
    {
        for(int i=0;i<players.length;i++){
            Player p=players[i];
            int [] pos=this.randomEmptyPos();
            Monster monster=this.putPlayer2D(-1, -1, pos[ROW], pos[COL], p);
        }
    }
    
    ////Funciones adicionales para crear, cargar y guardar laberintos////
    
    public void createLabyrinth(){
        String saveRoute = "../labyrinths/labyrinth";
        
        try {
            File createdLabyrinth = new File(saveRoute);            
            if (createdLabyrinth.createNewFile()) {
                System.out.println("Fichero "+ createdLabyrinth.getName() +" creado");
            }            
            else {
                boolean exist = true;
                int i = 1;
                String newName = saveRoute + i;

                while(exist){
                    createdLabyrinth = new File(newName);
                    exist = createdLabyrinth.exists();
                    i++;
                    newName = saveRoute + i;
                }
                saveRoute = newName;
            }
        }catch(IOException error){
            System.out.println("Error al crear el fichero");
        }
        
        boolean getOut = false;
        int xPos, yPos;
        int eleccion;
        int largo;
        Scanner read = new Scanner(System.in);
        Orientation or = Orientation.HORIZONTAL;
        boolean linea = true;
        
        System.out.println("Introduzca el tamaño del laberinto (filas columnas)");
        nRows = read.nextInt();
        nCols = read.nextInt();
        while(nRows <= 0 && nCols <= 0){
            System.out.println("Introduzca un tamaño adecuado del laberinto (filas columnas)");
            nRows = read.nextInt();
            nCols = read.nextInt();
        }
        labyrinth=new char[nRows][nCols];
        monsters=new Monster[nRows][nCols];
        players=new Player[nRows][nCols];
        
        for(int i=0;i<nRows;i++){
           for(int j=0;j<nCols;j++){
               labyrinth[i][j]=EMPTY_CHAR;
           }
        }
        
        System.out.println("Introduzca la casilla de salida (filas columnas");
        exitRow = read.nextInt();
        exitCol = read.nextInt();
        
        labyrinth[exitRow][exitCol] = EXIT_CHAR;
        while(!getOut){
            System.out.println("Introduzca que posición desea de modificar");
            xPos = read.nextInt();
            yPos = read.nextInt();
            System.out.println("¿En que orientación desea rellenar? Ponga el valor correspondiente a su elección\nNo desea rellenar: 0\nHorizontal: 1\nVertical: 2\n");
            eleccion = read.nextInt();
            switch(eleccion){
                case 0: 
                    linea = false;
                    break;
                case 1:
                    or = Orientation.HORIZONTAL;
                    break;
                case 2:
                    or = Orientation.VERTICAL;
                    break;
            }
            
            if(linea){
                System.out.println("Introduzca como de largo desea que sea la linea a cambiar");
                largo = read.nextInt();
            }else{
                largo = 1;
            }
            linea = true;
            
            this.addBlock(or, xPos, yPos, largo);
            System.out.println("El laberinto queda así:");
            System.out.println(this.toRealRepresentation() + "\n\n");
            System.out.println("¿Desea salir? Introduzca 0 para confirmar");
            eleccion = read.nextInt();
            getOut = eleccion == 0;
        }
        saveLabyrinth(saveRoute);
    }
    
    public void loadLabyrinth(String route){
        File fichero = new File(route);
        try{
            Scanner scan = new Scanner(fichero);
            
            String mstr = scan.nextLine();
            
            if(mstr.equals(magic_str)){
                nRows = scan.nextInt();
                nCols = scan.nextInt();
                labyrinth=new char[nRows][nCols];
                players=new Player[nRows][nCols];
                monsters=new Monster[nRows][nCols]; 
                scan.nextLine();
                String lab = scan.nextLine();
                
                for(int i =0; i < this.nRows; i++){
                    for(int j = 0; j < this.nCols; j++){
                        labyrinth[i][j] = lab.charAt(i*nRows + j);
                    }
                }
                
            }else{
                System.out.print("Error el archivo no es un laberinto");
            }
            scan.close();
            
        }catch(Exception e){
            System.out.print("Error al abrir el archivo");
            e.printStackTrace();
        }
    }
            
    public void saveLabyrinth(String route){
        File fichero = new File(route);
        try{
            FileWriter writer = new FileWriter(fichero);
            
            writer.write(magic_str +  "\n");
            writer.write(this.nRows + " " + this.nCols + "\n");
            for(int i =0; i < this.nRows; i++){
                for(int j = 0; j < this.nCols; j++){
                    writer.write(labyrinth[i][j]);
                }
            }
            writer.close();
        }catch(Exception e){
            System.out.print("Error al abrir el archivo");
        }
        
        
    }
    
    public String toRealRepresentation(){
        labyrinth[1][1] = MONSTER_CHAR;
        String laberinto = "";
        for(int i =0; i < nCols + 2; i++){
            laberinto += " ■";
        }
        laberinto += "\n";
        for(int i =0; i < nRows; i++){
            laberinto += " ■";
            for(int j =0; j <nCols; j++){
                if(labyrinth[i][j] == BLOCK_CHAR ){
                    laberinto += " ■";
                }else if(labyrinth[i][j] == EMPTY_CHAR){
                    laberinto += " □";
                }else if(labyrinth[i][j] == MONSTER_CHAR){
                    laberinto += " M";
                }else if(labyrinth[i][j]== COMBAT_CHAR){
                    laberinto += " ⚔";
                }else{
                    laberinto += " ✪";
                }
            }
            laberinto += " ■\n";
        }
        for(int i =0; i < nCols + 2; i++){
            laberinto += " ■";
        }
        laberinto += "\n";
        return laberinto;
    }

    //P3
public ArrayList<Directions> validMoves(int row, int col)
{
    ArrayList<Directions> dir=new ArrayList<>();
    if(canStepOn(row+1,col))
        dir.add(Directions.DOWN);
    if(canStepOn(row-1,col))
        dir.add(Directions.UP);
    if(canStepOn(row,col+1))
        dir.add(Directions.RIGHT);
    if(canStepOn(row,col-1))
        dir.add(Directions.LEFT);
    return dir;
}

}

