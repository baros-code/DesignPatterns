package PlaneDomain;

public class Fighter extends Plane {



            public Fighter(Engine e) {
                    super(10,12,e);

            }

            public String toString() {
                return "Fighter " + super.toString();
            }

}

