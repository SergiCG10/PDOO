package irrgarten;

/**
 *
 * @author Mangel
 */
public class GameState {
    private String labyrinth;
    private String players;
    private String monsters;
    private int currentPlayer; 
    private boolean winner;
    private String log;
    
    public GameState(String lab, String pl, String monst, int cp, boolean w, String l){
        labyrinth=lab;
        players=pl;
        monsters=monst;
        currentPlayer=cp;
        winner=w;
        log=l;
    }
    
    public String getLabyrinth(){
        return labyrinth;
    }
    public String getPlayers(){
        return players;
    }
    public String getMonsters(){
        return monsters;
    }
    public int getCurrentPlayer(){
        return currentPlayer;
    }
    public boolean getWinner(){
        return winner;
    }
    public String getLog(){
        return log;
    }
}
    

