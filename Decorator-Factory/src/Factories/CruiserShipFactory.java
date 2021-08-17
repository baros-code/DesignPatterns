package Factories;
import ShipDomain.Cruiser;
import ShipDomain.Ship;
public class CruiserShipFactory extends ShipFactory{


   

    public Ship createBattleProduct() {
            return new Cruiser();
    }
    
}