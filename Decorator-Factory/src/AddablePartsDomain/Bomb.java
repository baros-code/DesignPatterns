package AddablePartsDomain;
import MainDomain.Pointable;


public class Bomb extends AddablePart {

    public Bomb(Pointable p) {
        super(0,10,p);
    }

    public String toString() {
            return   super.toString() + "Bomb " + pointString() + ")";
    }

    
}