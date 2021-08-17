package Factories;
import AddablePartsDomain.Cannon;
import AddablePartsDomain.Rocket;
import AddablePartsDomain.Torpedo;
import MainDomain.Pointable;
import ShipDomain.Ship;

public class ShipAddablePartFactory extends AddablePartFactory {

    public ShipAddablePartFactory(Pointable s, int addablePartType) {
        super(s, addablePartType);
    }

    public Pointable createBattleProduct()  {
        int type=getAddablePartType();
        Pointable p=getP();
        if (type == 1) {
            return new Rocket(p);
        } 
         else if (type == 2) {
            return new Torpedo(p);
        }
        else  if (type == 3) {
            return  new Cannon(p);
        }
       return null;
    }

}