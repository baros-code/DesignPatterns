package Factories;
import MainDomain.Pointable;
import ShipDomain.Ship;


public abstract class ShipFactory implements BattleFactory {

    public  abstract Ship createBattleProduct();
    
}

