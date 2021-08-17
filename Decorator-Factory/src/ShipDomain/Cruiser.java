package ShipDomain;

public class Cruiser extends Ship {

    public Cruiser() {
        super(15,30);
    }

    public String toString() {
        return "Cruiser " + super.toString();
    }

}