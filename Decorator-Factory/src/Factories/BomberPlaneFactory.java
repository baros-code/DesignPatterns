package Factories;

import PlaneDomain.Bomber;
import PlaneDomain.Plane;

public class BomberPlaneFactory extends PlaneFactory {


    public BomberPlaneFactory(int engineType) {
            super(engineType);
    }

    @Override
    public Plane createBattleProduct()  {
        return new Bomber(getEngine());
    }



}