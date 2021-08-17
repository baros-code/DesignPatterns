package Factories;

import PlaneDomain.Multirole;
import PlaneDomain.Plane;

public class MultiroleFactory extends PlaneFactory {


    public MultiroleFactory(int engineType) {
        super(engineType);
    }
    
    @Override
    public Plane createBattleProduct()  {
        return new Multirole(getEngine());
    }


}