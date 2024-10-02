package irrgarten;

/**
 * Clase GameState. Implementación de la clase GameState con sus métodos y variables.
 * 
 * @author Miguel Ángel Luque Gómez
 * correo e.mangelluqg@go.ugr.es
 * 
 * @author Sergio Calvo González
 * correo e.sergiocg10@go.ugr.es
 * 
 */
public class GameState {
    private String labyrinth;   //Laberinto del juego
    private String players;     //Jugadores del juego
    private String monsters;    //Monstruos del juego
    private int currentPlayer;  //Índice del jugador actual
    private boolean winner;     //Variable booleana que muestra si hay o no ganador
    private String log;         //Variable log, donde guardar eventos anteriores
    
    /**
     * Constructor por parámetro de la clase GameState.
     * @param lab   representación del laberinto
     * @param pl    representación de los jugadores
     * @param monst representación de los monstruos
     * @param cp    índice del jugador actual
     * @param w     variable que muestra si hay o no ganador
     * @param l     variable donde guardar eventos del anterior turno
     */
    public GameState(String lab, String pl, String monst, int cp, boolean w, String l){
        labyrinth=lab;
        players=pl;
        monsters=monst;
        currentPlayer=cp;
        winner=w;
        log=l;
    }
    
    /**
     * Funcion getLabyrinth. Función getter de la variable labyrinth de la clase
     * @return Representación del laberinto
     */
    public String getLabyrinth(){
        return labyrinth;
    }
    
    /**
     * Funcion getPlayers. Función getter de la variable players de la clase
     * @return Representación de los jugadores
     */
    public String getPlayers(){
        return players;
    }
    
    /**
     * Funcion getMonsters. Función getter de la variable monsters de la clase
     * @return Representación de los monstruos
     */
    public String getMonsters(){
        return monsters;
    }
    
    /**
     * Funcion getCurrentPlayer. Función getter de la variable currentPlayer de la clase
     * @return índice del jugador actual
     */
    public int getCurrentPlayer(){
        return currentPlayer;
    }
    
    /**
     * Funcion getWinner. Función getter de la variable winner de la clase
     * @return Variable winner, que representa si hay ganador (true) o si no (false)
     */
    public boolean getWinner(){
        return winner;
    }
    
    /**
     * Funcion getLog. Función getter de la variable log de la clase
     * @return Variable donde almacenamos los eventos anteriores
     */
    public String getLog(){
        return log;
    }
}
    

