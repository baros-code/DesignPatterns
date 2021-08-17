package Factories;

import ShipDomain.Frigate;
import ShipDomain.Ship;

public class FrigateShipFactory extends ShipFactory {

    @Override
    public Ship createBattleProduct() {
        return new Frigate();
    }
    
}