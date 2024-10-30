
package irrgarten;


public class TestP2 {
    static Game gm = new Game(4);
    static Labyrinth lab=new Labyrinth(5,5,4,4), lab2=new Labyrinth(10,10,9, 9);
    
    public static void tP2(){
        //System.out.println(lab.toString());
        
        lab2.loadLabyrinth("../labyrinths/lab.txt");
        System.out.print(lab2.toRealRepresentation() );
    }
}
