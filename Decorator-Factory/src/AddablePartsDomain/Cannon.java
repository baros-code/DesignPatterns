package AddablePartsDomain;

import MainDomain.Pointable;

public class Cannon extends AddablePart {
    public Cannon(Pointable p) {
        super(3, 7, p);
    }

    public String toString() {
        return   super.toString() + "Cannon " + pointString() + ")";
    }
}
