package Factories;

import AddablePartsDomain.Bomb;
import AddablePartsDomain.MachineGun;
import AddablePartsDomain.Missile;
import AddablePartsDomain.Rocket;
import MainDomain.Pointable;

public class PlaneAddablePartFactory extends AddablePartFactory {

    public PlaneAddablePartFactory(Pointable p, int addablePartType) {
        super(p, addablePartType);
    }

    public Pointable createBattleProduct()  {
        int type=getAddablePartType();
        Pointable p=getP();
       
        if (type == 1) {
            return new Rocket(p);
        } 
        else if (type == 2) {
            return new Missile(p);
        }
        else  if (type == 3) {
            return  new MachineGun(p);
        } 
        else if ( type == 4) {
            return new Bomb(p);
        }
        return null;
    }
    
}