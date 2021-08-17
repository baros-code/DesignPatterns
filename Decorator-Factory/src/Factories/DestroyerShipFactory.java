package Factories;

import ShipDomain.Destroyer;
import ShipDomain.Ship;

public class DestroyerShipFactory extends ShipFactory{

    public Ship createBattleProduct() {
        return new Destroyer();
    }
    
}