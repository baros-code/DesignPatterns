package Domain;

public abstract class Currency implements Presentation.ICollectable {
    private   int point;
    private boolean magneticStatus; // special case for checking magnetic coins
    
     public Currency(int point) {   
         this.point=point;
         magneticStatus=false;
     }

    /**
      * Method returns point of currency
      * @return : int
      */
    public  int getPoint() {
         return point;
    }

    /**
     * Method returns type of currency
     * @return : String
     */
    public String getType() {   
        return getClass().getSimpleName();
    }
    /**
     * Method returns magnetic status
     * @return : boolean
     */
    public boolean isMagnetic() {
        return magneticStatus;
    }

    public void setMagneticStatus(boolean m) {
        magneticStatus=m;
    }

}

