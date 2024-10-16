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
    
    Game(int nplayers){
        for(int i =0; i < nplayers; i++){
            // Al valor de 1 en ascii (49), le sumamos i para obtener el valor de jugador en forma de char
            char c = (char)(i + '1'); 
            Player p = new Player(c ,Dice.randomIntelligence(), Dice.randomStrength() );
            players.add(p);
        }
    }
    //public boolean finished()
    
    //Practica 3
    //public boolean nextStep(Directions preferredDirection)
    
    public GameState getGameState(){
        //return new GameState( laberinto(string), jugadores(string), monstruos(string), currentPlayerIndex, this.finished(), log del anterior turno(string));
        return null;
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
    
    //private void logPlayerWon()
    
    //private void logMonsterWon()
    
    //private void logResurrected()
    
    //private void logPlayerSkipTurn()
    
    //private void logPlayerNoOrderns()
    
    //private void logNoMonster()
    
    //provate void logRounds(int rounds, int max)
}
