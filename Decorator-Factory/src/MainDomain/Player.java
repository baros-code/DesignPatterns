package MainDomain;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Player {

    private Map<Integer,Pointable> battleVehicles;
    private int assignId; // to assign unique  id to pointable object
    private int playerId;



    public Player() {
       this(0);
    }

    public Player(int playerId) {
        battleVehicles =  new HashMap<>();
        this.playerId=playerId;
        assignId = 1; // initialize vehicle id
    }


    public Map<Integer,Pointable> getBattleVehicles() {
       return battleVehicles;

    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public int getNumberOfVehicles() {
        return battleVehicles.size() + 1;

    }

    public void remove(int pointableId) { // remove Pointable Object
        battleVehicles.remove(pointableId);
        assignId = pointableId;
    }



    public void addElement(Pointable p) {
        battleVehicles.put(assignId, p);
        assignId= getNumberOfVehicles();
    }

    public Pointable findElementById(int id) {
        return battleVehicles.get(id);
    }


    public void removeAll() {
        battleVehicles.clear();
        assignId =1;
    }


    public int getTotalPoint() {
        int totalPoint = 0;
        Iterator iterator = battleVehicles.entrySet().iterator();
        while(iterator.hasNext()) {
            Map.Entry currentEntry = (Map.Entry) iterator.next();
            Pointable currentElement = (Pointable) currentEntry.getValue();
            totalPoint += currentElement.getPoint();
        }
        return totalPoint;

    
    }

    public void resetPointsOfVehicles() { // new function
        Iterator iterator = battleVehicles.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry currentEntry = (Map.Entry) iterator.next();
            Pointable currentElement = (Pointable) currentEntry.getValue();
            currentElement.resetPoint();
        }
    }

    public boolean isEmpty() {
        return battleVehicles.isEmpty();
    }

    public void setPointsRandom() {
        Iterator iterator = battleVehicles.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry currentEntry = (Map.Entry) iterator.next();
            Pointable currentElement = (Pointable) currentEntry.getValue();
            currentElement.setPointAccordingToRange();
        }

    }

    public int getPlayerId() {
        return playerId;
    }

    public String toString() {
        String s="\n";
         s+=" Player " + getPlayerId() +  "\n";
        s +="Battle Vehicles : \n";
        if (isEmpty()) {
            s+="There is no battle product. \n";
            return s;
        }
        Iterator iterator = battleVehicles.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry currentEntry = (Map.Entry) iterator.next();
            Pointable currentElement = (Pointable) currentEntry.getValue();
            int id= (int) currentEntry.getKey();
            s+= "Vehicle id : " + id  + " --- " +  currentElement +"\n";
            
        }
        return s;
    }



}