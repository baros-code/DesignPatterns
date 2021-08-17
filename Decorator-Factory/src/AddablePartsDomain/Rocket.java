package AddablePartsDomain;

import MainDomain.Pointable;


public class Rocket extends AddablePart {


    public Rocket(Pointable p) {
        super(2, 8, p);
    }

    public String toString() {
        return super.toString() + "Rocket " + pointString() + ")";


    }

}