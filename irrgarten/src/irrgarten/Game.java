
package irrgarten;

import java.util.ArrayList;
/**
 * Clase Game. Esta clase contiene la implementación de la variable game y sus 
 * funciones básicas para la utilización de la clase
 * 
 * @author Miguel Ángel Luque Gómez
 * correo: e.mangelluqg@go.ugr.es
 * 
 * @author Sergio Calvo González
 * correo: e.sergiocg10@go.ugr.es
 */
public class Game {
    private static int MAX_ROUNDS = 10; //Número de rondas máximas que se van a jugar
    private int currentPlayerIndex;     //Indice del jugador actual
    private String log;                 //Variable donde almacenaremos los sucesos dentro de la ronda actual
    
    private ArrayList<Player> players = new ArrayList<>();      //Variable donde almacenaremos la información de los jugadores
    private ArrayList<Monster> monsters = new ArrayList<>();    //Variable donde almacenaremos la información de los monstruos
    private Player currentPlayer;                               //Variable que almacena los valores del jugador actual
    private Labyrinth labyrinth;                                //Variable donde almacenaremos la información de todo el laberinto
    
    /**
     * Constructor por parámetros de la clase Game.
     * Construye una variable Game con nPlayers como número de jugadores
     * Los valores de inteligencia y fuerza se atribuyen de forma aleatoria
     * 
     * @param nplayers Número de jugadores que jugarán al juego
     */
    Game(int nplayers){
        for(int i =0; i < nplayers; i++){
            // Al valor de 1 en ascii (49), le sumamos i para obtener el valor de jugador en forma de char
            char c = (char)(i + '1'); 
            Player p = new Player(c ,Dice.randomIntelligence(), Dice.randomStrength() );
            players.add(p);
        }
            currentPlayerIndex = 0;
            log = "";
    }
    
    /**
     * Funcion finished. Indica si el juego ha terminado o no. (Hay un ganador)
     *  
     * @return True si hay ganador, false si no
     */
    public boolean finished(){
        return labyrinth.haveAWinner();
    }
    
    //Practica 3
    public boolean nextStep(Directions preferredDirection){
        log="";
        boolean dead=currentPlayer.dead();
        if(!dead){
            Directions direction=this.actualDirection(preferredDirection);
            if(direction != preferredDirection)
                this.logPlayerNoOrders();
            Monster monster=labyrinth.putPlayer(direction, currentPlayer);
            
            if(monster==null)
                this.logNoMonster();
            else{
                GameCharacter winner=this.combat(monster);
                this.manageReward(winner);
            }  
        }
        else{
            this.manageResurrection();
        }
        boolean endGame=this.finished();
        
        if(!endGame)
            this.nextPlayer();
        return endGame;
    }
    
    /**
     * Funcion getGameState. Almacena los valores del juego actual y los devuelve.
     *  
     * @return Variable GameState que contiene el laberinto, todos los jugadores, todos los monstruos
     *          el índice de jugador actual, si el juego ha terminado, y el log de la ronda.
     */
    public GameState getGameState(){
        String  allPlayers = "";
        String allMonsters = "";
        
        for(int i = 0; i < players.size(); i++){
            allPlayers += players.get(i).toString();
        }
        
        for(int i = 0; i < monsters.size(); i++){
            allMonsters += monsters.get(i).toString();
        }
        return new GameState( labyrinth.toString(), allPlayers, allMonsters, currentPlayerIndex, this.finished(), log);
    }
    
    
    private void configureLabyrinth(){
        int nRows = 7;
        int nCols = 7;
        labyrinth = new Labyrinth(nRows, nCols, nRows -1, nCols -1);
        
        
    }
    
    /**
     * Funcion nextPlayer. La funcion almacena los cambios en el jugador actual
     * y pasa al siguiente jugador. 
     */
    private void nextPlayer(){
        players.set(currentPlayerIndex, currentPlayer);//Intercambiamos la variable de jugador actual por el jugador correspondiente en el vector
        
        if(currentPlayerIndex + 1 == players.size()){
            currentPlayerIndex = 0;
        }else{
            currentPlayerIndex++;
        }
        currentPlayer = players.get(currentPlayerIndex);
    }
    
    //Practica 3
    /**
     * Funcion actualDirection. Devuelve el resultado  del ultimo step 
     * @param preferredDirection
     * @return 
     */
    private Directions actualDirection(Directions preferredDirection)
    {
        int row=currentPlayer.getRow();
        int col=currentPlayer.getCol();
        ArrayList<Directions> validMoves=labyrinth.validMoves(row, col);
        Directions dir=currentPlayer.move(preferredDirection, validMoves);
        return dir;
    }
    
    //Practica 3
    /**
     * Funcion Combat. Devuelve el gameCharacter ganador de la batalla
     * @param monster
     * @return 
     */
    private GameCharacter combat(Monster monster)
    {
        int rounds=0;
        GameCharacter winner=GameCharacter.PLAYER;
        float playerAttack=currentPlayer.attack();
        boolean lose=monster.defend(playerAttack);
        
        for(; rounds<MAX_ROUNDS && !lose ;rounds++)
        {
            winner=GameCharacter.MONSTER;
            float monsterAttack=monster.attack();
            lose=currentPlayer.defend(monsterAttack);
            if(!lose){
                playerAttack=currentPlayer.attack();
                winner=GameCharacter.PLAYER;
                lose=monster.defend(playerAttack);
            }
        }
        this.logRounds(rounds, MAX_ROUNDS);
        return winner;
    }
    
    //Practica 3
    private void manageReward(GameCharacter winner){
        if(winner==GameCharacter.PLAYER){
            currentPlayer.receiveReward();
            this.logPlayerWon();
        }
        else
            this.logMonsterWon();
    }
    
    //Practica 3
    private void manageResurrection(){
        boolean resurrect=Dice.resurrectPlayer();
        if(resurrect)
            this.logResurrected();
        else
            this.logPlayerSkipTurn();
    }
    
    /**
     * Funcion logPlayerWon. Concatena a la variable log que ha ganado el combate el jugador
     */
    private void logPlayerWon(){
        log += "El jugador ha ganado el combate\n";
    }
    
    /**
     * Funcion logMonsterWon. Concatena a la variable log que ha ganado el combate el monstruo
     */
    private void logMonsterWon(){
        log += "El monstruo ha ganado el combate\n";
    }
    
    /**
     * Funcion logResurrected. Concatena a la variable log que el jugador ha resucitado
     */
    private void logResurrected(){
        log += "El jugador ha resucitado\n";
    }
    
    /**
     * Funcion logPlayerWon. Concatena a la variable log que el jugador ha perdido su turno
     */
    private void logPlayerSkipTurn(){
        log += "El jugador ha perdido turno por estar muerto\n";
    }
    
    /**
     * Funcion logPlayerNoOrders. Concatena a la variable log que 
     * el jugador no pudo seguir las instrucciones 
     */
    private void logPlayerNoOrders(){
        log += "El jugador no ha podido seguir las instrucciones\n";
    }
    
    /**
     * Funcion logNoMonster. Concatena a la variable log que el jugador se ha movido
     * a una celda sin monstruo o no ha podido moverse.
     */
    private void logNoMonster(){
        log += "El jugador se ha movido a una celda vacía o no ha podido moverse\n";
    }
    
    /**
     * Funcion logRounds. Concatena a la variable log que han pasado x rondas de MAX_ROUNDS
     */
    private void logRounds(int rounds, int max){
        log += "Se han producido " + rounds + " de " + max + "\n";
    }
}
