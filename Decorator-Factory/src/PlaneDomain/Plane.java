package PlaneDomain;
import MainDomain.Pointable;


public abstract class Plane extends Pointable {
        Engine engine;
        
    

    public Plane(int startPointRange,int endPointRange,Engine engine) {
        super(startPointRange,endPointRange);
        this.engine=engine;
        setType("Plane");

        

        
    }

    public void resetPoint() {
        super.resetPoint();
        engine.resetPoint();
    }

    public Engine getEngine() {
        return engine;
    }

    public int getPoint() {
        return super.getPoint() + engine.getPoint();
    }




    public  void setPointAccordingToRange()
    {
        super.setPointAccordingToRange();
         engine.setPointAccordingToRange();

    }

    public String toString() {
           return "Plane " + super.toString() + " with " + engine.toString();
    }

}
