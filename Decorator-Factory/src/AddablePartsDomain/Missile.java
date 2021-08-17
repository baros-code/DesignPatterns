package AddablePartsDomain;

import MainDomain.Pointable;

public class Missile extends AddablePart {

        public Missile(Pointable p) {
            super(3,6,p);
        }

        public String toString() {
            return super.toString() + "Missile " + pointString() + ")";
    }

    
}