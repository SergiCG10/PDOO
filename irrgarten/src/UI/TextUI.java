
package UI;

/**
 *
 * @author Mangel
 */
import irrgarten.Directions;
import irrgarten.GameState;
import java.util.Scanner;


public class TextUI {
    
    private static Scanner in = new Scanner(System.in);
    
    private char readChar() {
        String s = in.nextLine();     
        return s.charAt(0);
    }
    

    public Directions nextMove() {
        System.out.print("Where? ");
        
        Directions direction = Directions.DOWN;
        boolean gotInput = false;
        
        while (!gotInput) {
            char c = readChar();
            switch(c) {
                case 'w':
                    System.out.print(" UP\n");
                    direction = Directions.UP;
                    gotInput = true;
                    break;
                case 's':
                    System.out.print(" DOWN\n");
                    direction = Directions.DOWN;
                    gotInput = true;
                    break;
                case 'd':
                    System.out.print("RIGHT\n");
                    direction = Directions.RIGHT;
                    gotInput = true;
                    break;
                case 'a':
                    System.out.print(" LEFT\n");
                    direction = Directions.LEFT;
                    gotInput = true;    
                    break;
            }
        }    
        return direction;
    }
    
    public void showGame(GameState gameState) {   
        System.out.print("Jugadores:\n"+gameState.getPlayers());
        System.out.print("Monstruos:\n"+gameState.getMonsters());
        System.out.print("Jugador actual:"+ (gameState.getCurrentPlayer() + 1));
        System.out.print("\nEstado del laberinto\n"+gameState.getLabyrinth());
        System.out.print("\nWinner:"+gameState.getWinner());
        if(!gameState.getWinner())
            System.out.print("\nLog:"+gameState.getLog());
    }
    
}