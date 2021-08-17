package AddablePartsDomain;

import MainDomain.Pointable;

public class Torpedo extends AddablePart {

    public Torpedo( Pointable p) {
        super(3, 5, p);
    }

    public String toString() {
        return   super.toString() + "Torpedo " + pointString() + ")";
    }
}
