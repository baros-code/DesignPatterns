package ShipDomain;
import MainDomain.Pointable;



public abstract class Ship extends Pointable {

   

    public Ship(int startPointRange,int endPointRange) {
            super(startPointRange,endPointRange);
            setType("Ship");
    }


    public String toString() {
        return "Ship " + super.toString();
    }


    
}