package Factories;
import MainDomain.Pointable;
import PlaneDomain.Engine;
import PlaneDomain.Plane;
import PlaneDomain.Pulsejet;
import PlaneDomain.Turbojet;


public abstract class  PlaneFactory implements BattleFactory {
    private int engineType;

    public PlaneFactory(int engineType) {
            this.engineType=engineType;
    }

    public int getEngineType() {
            return engineType;
    }

    public void setEngineType(int engineType) {
        this.engineType=engineType;
    }



    public  abstract Plane createBattleProduct();


    public Engine getEngine()  {
        int type=getEngineType();
        
        if (type == 1 ) {
            return new Turbojet();
        } else if (type == 2) {
            return new Pulsejet();
        }
        return null;

    }

    
        
             
        
    
}

















