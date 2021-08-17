package ShipDomain;

public class Destroyer extends Ship {

    public Destroyer() {
            super(20,40);
    }

    public String toString() {
        return "Destroyer " + super.toString();
    }
    
}