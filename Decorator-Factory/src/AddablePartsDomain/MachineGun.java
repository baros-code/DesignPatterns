package AddablePartsDomain;
import MainDomain.Pointable;


public class MachineGun extends AddablePart {

    public MachineGun(Pointable p) {
        super(1,3,p);
    }

    public String toString() {
        return super.toString() + "Machine Gun " + pointString() + ")";
    }

    
}