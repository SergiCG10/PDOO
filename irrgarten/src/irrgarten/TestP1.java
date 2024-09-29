
package irrgarten;

/**
 *
 * @author Mangel
 */
public class TestP1 {
    public void main()
    {
        Dice dice=new Dice();
        Weapon w1=new Weapon(1.4f,5);
        Weapon w2=new Weapon(2.7f,3);
        Shield s1=new Shield(1.5f,4);
        Shield s2=new Shield(2f,2);
        
        /*for(int i=0;i<100;i++){
            System.out.println("iteracion"+i+"\n");
            System.out.println("El jugador recibe "+dice.healthReward()+" puntos de salud");            
        }*/
        /*for(int i=0;i<100;i++){
            System.out.println("iteracion"+i+"\n");
            System.out.println("El jugador recibe "+dice.weaponsReward()+" armas");            
        }*/
        /*for(int i=0;i<100;i++){
            System.out.println("iteracion"+i+"\n");
            System.out.println("El jugador recibe "+dice.shieldsReward()+" escudos");            
        }*/
        /*for(int i=0;i<100;i++){
            System.out.println("iteracion"+i+"\n");
            System.out.println("El jugador empieza en la posicion "+dice.randomPos(50));
        }*/
        /*for(int i=0;i<100;i++){
            System.out.println("iteracion"+i+"\n");
            System.out.println("Es el turno del jugador "+dice.whoStarts(4));
        }*/
        /*for(int i=0;i<100;i++){
            System.out.println("iteracion"+i+"\n");
            System.out.println("Inteligencia asignada: "+dice.randomIntelligence());
        }*/
        /*for(int i=0;i<100;i++){
            System.out.println("iteracion"+i+"\n");
            System.out.println("Fuerza asignada: "+dice.randomStrength());
        }*/
        /*for(int i=0;i<100;i++){
            System.out.println("iteracion"+i+"\n");
            System.out.println("Usos restantes: "+dice.usesLeft());
        }*/
        /*for(int i=0;i<100;i++){
            System.out.println("iteracion"+i+"\n");
            System.out.println("Objeto descartado: "+dice.discardElement(dice.usesLeft()));
        }*/
        /*for(int i=0;i<100;i++){
            System.out.println("iteracion"+i+"\n");
            System.out.println("Resucita el jugador?: "+dice.resurrectPlayer());
        }*/
        for(int i=0;i<100;i++){
            System.out.println("iteracion"+i+"\n");
            System.out.println("Poder del arma: "+dice.shieldPower());
            /*dice.intensity(10);
            dice.weaponPower();
            */
        }
    }
}
