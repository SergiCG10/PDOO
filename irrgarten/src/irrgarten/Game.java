/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
    //public boolean nextStep(Directions preferredDirection)
    
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
        return new GameState( labyrinth.toSring(), allPlayers, allMonsters, currentPlayerIndex, this.finished(), log);
    }
    
    //Cómo configuramos el laberinto?
    //private void configureLabyrinth()
    
    /**
     * Funcion nextPlayer. La funcion almacena los cambios en el jugador actual
     * y pasa al siguiente jugador. 
     */
    private void nextPlayer(){
        //Actualizar los dos atributos del jugador current?
        players.set(currentPlayerIndex, currentPlayer);
        
        if(currentPlayerIndex + 1 == players.size()){
            currentPlayerIndex = 0;
        }else{
            currentPlayerIndex++;
        }
        currentPlayer = players.get(currentPlayerIndex);
    }
    
    //Practica 3
    //private Directions actualDirection(Directions preferredDirection)
    
    //Practica 3
    //private GameCharacter combat(Monster monster)
    
    //Practica 3
    //private void manageReward(GameCharacter winner)
    
    //Practica 3
    //private void manageResurrection()
    
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
