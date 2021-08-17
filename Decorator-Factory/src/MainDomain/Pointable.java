package MainDomain;
import java.util.Random;


public abstract class Pointable {
    private int point;
    private int startPointRange;
    private int endPointRange;
    private String type;


    public Pointable(int startRange,int endRange) {
        this.startPointRange = startRange;
        this.endPointRange=endRange;
        point=-1; // point is not assigned
    }


    public String getType() {
        return type;
    }


    public void setType(String type) {
        this.type = type;
    }

    public void resetPoint() { // for resetting the point
        setPoint(-1);
    }

    public int getStartRange() {
        return startPointRange;
    }

    public int getEndRange() {
        return endPointRange;
    }


    public int getPoint() {
        return point;
    }


    public void setPoint(int newPoint) {
        this.point=newPoint;
    }

    public  void setPointAccordingToRange(){
        Random rand=new Random();
        int newPoint=rand.nextInt((getEndRange() - getStartRange()) + 1) + getStartRange();
        setPoint(newPoint);
    }

    public String toString() {
        if (pointIsAssigned()) {
            return "[" + point + "]";
        } else {
            return "[" + startPointRange + "-" + endPointRange + "]";
        }

    }

    private boolean pointIsAssigned() {
        return (point != -1);
    }


}