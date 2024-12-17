package irrgarten;
import UI.*;
import Controller.Controller;

/**
 * @author Miguel Ángel Luque Gómez
 * correo e.mangelluqg@go.ugr.es
 * 
 * @author Sergio Calvo González
 * correo e.sergiocg10@go.ugr.es
 * 
 */

public class Irrgarten {

    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //TestP1.tP1();
        //TestP2.tP2();       
        Game game=new Game(4);
        GraphicUI view=new GraphicUI();
        Controller controller=new Controller(game, view);
        controller.play();
    }
    
}
