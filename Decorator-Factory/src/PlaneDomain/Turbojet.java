package PlaneDomain;


public class Turbojet extends Engine {

    public Turbojet() {
        super(5,7);
    }

    public String toString() {
        return "TurboJet " + super.toString();
    }
    
}