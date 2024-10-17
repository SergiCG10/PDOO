/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

import java.util.ArrayList;
/**
 *
 * @author usuario
 */
public class Game {
    private static int MAX_ROUNDS = 10;
    private int currentPlayerIndex;
    private String log;
    
    private ArrayList<Player> players = new ArrayList<>();
    private ArrayList<Monster> monsters = new ArrayList<>();
    private Player currentPlayer;
    private Labyrinth labyrinth;
    
    Game(int nplayers){
        for(int i =0; i < nplayers; i++){
            // Al valor de 1 en ascii (49), le sumamos i para obtener el valor de jugador en forma de char
            char c = (char)(i + '1'); 
            Player p = new Player(c ,Dice.randomIntelligence(), Dice.randomStrength() );
            players.add(p);
        }
    }
    
    public boolean finished(){
        return labyrinth.haveAWinner();
    }
    
    //Practica 3
    //public boolean nextStep(Directions preferredDirection)
    
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
    
    //private void configureLabyrinth()
    
    //private void nextPlayer()
    
    //Practica 3
    //private Directions actualDirection(Directions preferredDirection)
    
    //Practica 3
    //private GameCharacter combat(Monster monster)
    
    //Practica 3
    //private void manageReward(GameCharacter winner)
    
    //Practica 3
    //private void manageResurrection()
    
    private void logPlayerWon(){
        log += "El jugador ha ganado el combate\n";
    }
    
    private void logMonsterWon(){
        log += "El monstruo ha ganado el combate\n";
    }
    
    private void logResurrected(){
        log += "El jugador ha resucitado\n";
    }
    
    private void logPlayerSkipTurn(){
        log += "El jugador ha perdido turno por estar muerto\n";
    }
    
    private void logPlayerNoOrderns(){
        log += "El jugador no ha podido seguir las instrucciones\n";
    }
    
    private void logNoMonster(){
        log += "El jugador se ha movido a una celda vacía o no ha podido moverse\n";
    }
    
    private void logRounds(int rounds, int max){
        log += "Se han producido " + rounds + " de " + max + "\n";
    }
}
