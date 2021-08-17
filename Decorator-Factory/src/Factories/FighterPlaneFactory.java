package Factories;

import PlaneDomain.Fighter;
import PlaneDomain.Plane;

public class FighterPlaneFactory extends PlaneFactory {


        public FighterPlaneFactory(int engineType) {
            super(engineType);

        }

        public Plane createBattleProduct()  {
            return new Fighter(getEngine());
            
        }


    
}