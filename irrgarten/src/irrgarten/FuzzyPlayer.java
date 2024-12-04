package irrgarten;

import java.util.ArrayList;

/**
 *
 * @author usuario
 */

//HAY QUE VER DONDE SE HACE REFERENCIA A PLAYER E IMPLEMENTAR PARA QUE PUEDA SER 
//SUSTITUIDO POR FUZZYPLAYER 
//(Clase game, array list de jugadores)
public class FuzzyPlayer extends Player{
    FuzzyPlayer(Player other){
        super(other);
    }
    
    @Override
    public Directions move(Directions direction, ArrayList<Directions>  validMoves ){
        Directions dir = super.move(direction, validMoves);
        Directions move = Dice.nextStep(dir, validMoves, this.getIntelligence());
        return move;
    }
    
    @Override
    public float attack(){       
        return Dice.intensity(this.getStrength()) + sumWeapons();
    } 
    
    @Override
    protected float defensiveEnergy(){
        return Dice.intensity(this.getIntelligence()) + sumShields();
    }
    
    @Override
    public String toString(){
        return "\nFuzzy" + super.toString();
    }
    
    
}
