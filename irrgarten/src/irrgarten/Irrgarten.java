package irrgarten;

/**
 *
 * @author usuario
 */
public class Irrgarten {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Weapon w=new Weapon(2f,5);
        Shield s=new Shield(3f,4);
        System.out.print(w.toString());
        System.out.print(s.toString());
    }
    
}
