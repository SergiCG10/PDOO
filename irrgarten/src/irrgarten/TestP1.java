
package irrgarten;

/**
 * @author Miguel Ángel Luque Gómez
 * @correo e.mangelluqg@go.ugr.es
 * 
 * @author Sergio Calvo González
 * @author e.sergiocg10@go.ugr.es
 * 
 */
public class TestP1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Dice dice=new Dice();
        Weapon w1=new Weapon(1.4f,5);
        Weapon w2=new Weapon(2.7f,3);
        Shield s1=new Shield(1.5f,4);
        Shield s2=new Shield(2f,2);
        boolean descartar = false;
        
        /*
        GameCharacter gm = GameCharacter.MONSTER;
        System.out.print(gm);*/
        
        //COMPROBAMOS LA CLASE WEAPON
        /*System.out.println("El estado del arma es: "+w1.toString());
        while(!descartar){
            System.out.println("El jugador hace "+w1.attack()+" puntos de daño");
            System.out.println("El estado del arma es: "+w1.toString());
            descartar = w1.discard();
            System.out.println("El arma se descarta: "+descartar );
            
        }*/
        
        //COMPROBAMOS LA CLASE SHIELD
        /*descartar = false;
        System.out.println("El estado del escudo es: "+s2.toString());
        while(!descartar){
            System.out.println("El jugador tiene "+s2.protect() +" puntos de defensa");
            System.out.println("El estado del escudo es: "+s2.toString());
            descartar = s2.discard();
            System.out.println("El escudo se descarta: "+descartar );
            
        }*/
        
        
        //COMPROBAMOS LA CLASE DICE
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
        /*for(int i=0;i<100;i++){
            System.out.println("iteracion"+i+"\n");
            System.out.println("Poder del escudo: "+dice.shieldPower());
        }*/
        /*for(int i=0;i<100;i++){
            System.out.println("iteracion"+i+"\n");
            System.out.println("Poder del arma: "+dice.weaponPower());
        }*/
        /*for(int i=0;i<100;i++){
            System.out.println("iteracion"+i+"\n");
            System.out.println("intensidad: "+dice.intensity(10));
        }*/
    }
    
}
