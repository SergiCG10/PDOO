
package irrgarten;

/**
 * @author Miguel Ángel Luque Gómez
 * correo e.mangelluqg@go.ugr.es
 * 
 * @author Sergio Calvo González
 * correo e.sergiocg10@go.ugr.es
 * 
 */
public class TestP1 {
      
    static Weapon w1=new Weapon(1.4f,5);
    static Weapon w2=new Weapon(2.7f,3);
    static Shield s1=new Shield(1.5f,4);
    static Shield s2=new Shield(2f,2);
    static boolean descartar = false;
    
    public static void tP1(){
        System.out.println("Comenzamos a comprobar el enumerado GameCharacter:");
        GameCharacter gm = GameCharacter.MONSTER;
        System.out.print(gm + "\n\n");

        //COMPROBAMOS LA CLASE WEAPON
        System.out.println("Comenzamos a comprobar la clase Weapon: \n\n");
        System.out.println("El estado del arma es: "+w1.toString());
        while(!descartar){
            System.out.println("El jugador hace "+w1.attack()+" puntos de daño");
            System.out.println("El estado del arma es: "+w1.toString());
            descartar = w1.discard();
            System.out.println("El arma se descarta: "+descartar );

        }

        //COMPROBAMOS LA CLASE SHIELD
        System.out.println("Comenzamos a comprobar la clase Shield: \n\n");
        descartar = false;
        System.out.println("El estado del escudo es: "+s2.toString());
        while(!descartar){
            System.out.println("El jugador tiene "+s2.protect() +" puntos de defensa");
            System.out.println("El estado del escudo es: "+s2.toString());
            descartar = s2.discard();
            System.out.println("El escudo se descarta: "+descartar );

        }


        //COMPROBAMOS LA CLASE DICE
        System.out.println("Comenzamos a comprobar la clase Dice: \n\n");
        for(int i=0;i<100;i++){
            System.out.println("iteracion"+i+"\n");
            System.out.println("El jugador recibe "+Dice.healthReward()+" puntos de salud");            
        }
        for(int i=0;i<100;i++){
            System.out.println("iteracion"+i+"\n");
            System.out.println("El jugador recibe "+Dice.weaponsReward()+" armas");            
        }
        for(int i=0;i<100;i++){
            System.out.println("iteracion"+i+"\n");
            System.out.println("El jugador recibe "+Dice.shieldsReward()+" escudos");            
        }
        for(int i=0;i<100;i++){
            System.out.println("iteracion"+i+"\n");
            System.out.println("El jugador empieza en la posicion "+Dice.randomPos(50));
        }
        for(int i=0;i<100;i++){
            System.out.println("iteracion"+i+"\n");
            System.out.println("Es el turno del jugador "+Dice.whoStarts(4));
        }
        for(int i=0;i<100;i++){
            System.out.println("iteracion"+i+"\n");
            System.out.println("Inteligencia asignada: "+Dice.randomIntelligence());
        }
        for(int i=0;i<100;i++){
            System.out.println("iteracion"+i+"\n");
            System.out.println("Fuerza asignada: "+Dice.randomStrength());
        }
        for(int i=0;i<100;i++){
            System.out.println("iteracion"+i+"\n");
            System.out.println("Usos restantes: "+Dice.usesLeft());
        }
        for(int i=0;i<100;i++){
            System.out.println("iteracion"+i+"\n");
            System.out.println("Objeto descartado: "+Dice.discardElement(Dice.usesLeft()));
        }
        for(int i=0;i<100;i++){
            System.out.println("iteracion"+i+"\n");
            System.out.println("Resucita el jugador?: "+Dice.resurrectPlayer());
        }
        for(int i=0;i<100;i++){
            System.out.println("iteracion"+i+"\n");
            System.out.println("Poder del escudo: "+Dice.shieldPower());
        }
        for(int i=0;i<100;i++){
            System.out.println("iteracion"+i+"\n");
            System.out.println("Poder del arma: "+Dice.weaponPower());
        }
        for(int i=0;i<100;i++){
            System.out.println("iteracion"+i+"\n");
            System.out.println("intensidad: "+Dice.intensity(10));
        }
    }
}
