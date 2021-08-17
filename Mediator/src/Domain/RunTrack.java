package Domain;

import java.util.HashMap;
import java.util.Map;

    public abstract   class RunTrack {

    private class Location {    //  contains two objects: Obstacle and Currency
        Currency c;
        Obstacle o;

        private Location() {
            c = null;
            o = null;
        }

        private boolean hasCurrency() {
            return (c != null);
        }

        private boolean  hasObstacle() {
            return (o != null);
        }

        Currency removeCurrency() {
            Currency temp=c;
            c=null;
            return temp;
        }

        void setCurrency(Currency c) {
                this.c = c;

        }

        void setObstacle(Obstacle o) {
                this.o = o;


        }
        Obstacle getO() {
            return o;
        }
        Currency getC() {
            return c;
        }
    }

    private Map<Integer,Location> Road; // It has a location objects.
    private int perimeter;


    public RunTrack(int perimeter) {
        Road= new HashMap<Integer,Location>();
        this.perimeter=perimeter;

    }

    public int getPerimeter() { return perimeter;
    }


   private Location getLocation(int locationId) {
        return Road.get(locationId);
    }

    private boolean containsLocation(int locationId) {
        return (Road.containsKey(locationId));
    }

    private void addLocation(int locationId) {
        Road.put(locationId, new Location());

    }
    /**
     * Method adds given obstacle to given position
     * @param : int,Obstacle
     * @return : Obstacle 
     */
    public Obstacle addObstacleToLocation(int locationId,Obstacle o) {
        if (!containsLocation(locationId)) {
            addLocation(locationId);
        }          
        Location l=getLocation(locationId);
        if (l.hasObstacle()) {
            return null; // adding process is unsuccessful. location can only have one obstacle
        }
        l.setObstacle(o);
        return o;

    }
    /**
     * Method that removes currency at given location 
     * @param : int
     * @return : Currency
     */
    public Currency removeCurrencyFromLocation(int locationId) {
        if( containsLocation(locationId)) {
            Location l=getLocation(locationId);
            if (l.hasCurrency()) {
                Currency temp=l.removeCurrency();
                return temp;
        }
        }
        return null;   // currency or location does not exist.
        
    }
    /**
     * Method adds given currency to given position
     * @param : int,Currency
     * @return : Currency 
     */
    public Currency addCurrencyToLocation(int locationId,Currency c) {
        if (!containsLocation(locationId)) {
            addLocation(locationId);
        }    
        Location l=getLocation(locationId);
        if (l.hasCurrency()) {
            return null; // adding process is unsuccessful.location can only have one currency
        }
        l.setCurrency(c);
        return c;

    }
    

    public Currency getCurrencyFromLocation(int locationId) {
        if (containsLocation(locationId)) {
            Location l= getLocation(locationId);
                return l.getC();// it returns null if currency does not exist.


        }
        return null; // process is unsuccessful because location is not created (currency does not exist)

    }

    public Obstacle getObstacleFromLocation(int locationId) {
        if(containsLocation(locationId)) {
            Location l=getLocation(locationId);
            return l.getO(); // it returns null if obstacle does not exist.
        }
        return null; // process is unsuccessful because location is not created (obstacle does not exist)
    }



}   