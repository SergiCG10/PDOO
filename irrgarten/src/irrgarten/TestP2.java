
package irrgarten;


public class TestP2 {
    static Game gm = new Game(4);
    static Labyrinth lab=new Labyrinth(5,5,4,4);
    
    public static void tP2(){
        System.out.println("Monstramos las características del juego: \n" + gm.toString());
        System.out.println(lab.toString());
        
        
    }
}
