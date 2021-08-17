package PlaneDomain;

public class Multirole extends Plane {

            public Multirole(Engine e) {
                super(10,25,e);

            }

            public String toString() {
                return "Multirole " + super.toString();
            }
    
}