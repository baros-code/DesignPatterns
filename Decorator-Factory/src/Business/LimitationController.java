package Business;

import java.util.List;

import MainDomain.Pointable;

/**
 * Controller that defines and controls the limitations of the simulation.
 */

public class LimitationController {
    private int maximumCount;               //identifies the maximum product count that a battle side can have.
    private int maximumSameTypeCount;       //identifies the maximum count of the products that has the same type that a battle side can have.
    private int maximumEachTypeCount;       //identifies the maximum count of Planes/Ships





    public LimitationController(int maximumCount, int maximumSameTypeCount, int maximumEachTypeCount) {
        this.maximumCount = maximumCount;
        this.maximumSameTypeCount = maximumSameTypeCount;
        this.maximumEachTypeCount = maximumEachTypeCount;
    }


    /**
     * Method that checks whether all limitations are followed.
     * @param allPointables, List that has all the products.
     * @param specificPointables, List that only contains the same type(Only Plane or only Ship,...) products.
     * @param vehicleType, string that corresponds to vehicle type such as Fighter,Bomber,Cruiser,...
     * @return boolean, true if limitations are followed.
     * @throws LimitationException if conditions are not satisfied(invalid operation)
     */

    public void checkAllConditions(List<Pointable> allPointables,List<Pointable> specificPointables,String vehicleType) throws LimitationException {
        if (allPointables.isEmpty() || specificPointables.isEmpty()) {
            return;
        }
        checkMaximum(allPointables);
        checkMaximumSameType(specificPointables,vehicleType);
        checkMaximumEachType(specificPointables);
    }


    /**
     * Method that checks whether the maximum number of vehicles have been reached.
     * @return boolean, true if not reached to maximum.
     */
    private void checkMaximum(List<Pointable> pointables) throws LimitationException {  //send all pointables
        if (pointables.size() >= maximumCount) {
            throw new LimitationException("Maximum number of vehicles have been reached!");
        }
    }


    /**
     * Method that checks whether the maximum number of vehicles that has the same type have been reached.
     * @return boolean, true if not reached.
     * @param pointables, List that contains products
     * @param vehicleSubType, String that defines the vehicle's specific type such as Fighter,Bomber,Cruiser,...
     */
    private void checkMaximumSameType(List<Pointable> pointables ,String vehicleSubType) throws LimitationException { 
        boolean isValid;
        isValid = checkMaximumSubType(pointables,vehicleSubType);
        if(!isValid) throw new LimitationException("Maximum number of "+ vehicleSubType + " " + pointables.get(0).getType() 
        											+ " have been reached!");
    }


    /**
     * Method that checks whether there is maximum number of Plane/Ship
     * @param pointables, List that contains products
     * @return boolean, true if not maximum.
     */
    private  void checkMaximumEachType(List<Pointable> pointables) throws LimitationException { 
        if (pointables.size() >= maximumEachTypeCount) {
            throw new LimitationException("There should be a maximum of "+maximumEachTypeCount+" vehicles for each "+ pointables.get(0).getType());
        }
    }


    /**
     * Method that counts the occurrence of the given product type and compares with the limit.
     * @param pointables, List that contains products
     * @param subType, String that defines the vehicle's specific type such as Fighter,Bomber,Cruiser,...
     * @return boolean, true if the occurrence is not equal or bigger than the limit
     */
    private boolean checkMaximumSubType(List<Pointable> pointables, String subType) {
        int currentCount=0;
        for (Pointable p:pointables) {
            String currentSubType=p.getClass().getSimpleName();
            if (currentSubType.equals(subType)) {
                currentCount++;
            }
        }
        return !(currentCount >= maximumSameTypeCount);
    }


	public void setMaximumCount(int maximumCount) {
		this.maximumCount = maximumCount;
	}


	public void setMaximumSameTypeCount(int maximumSameTypeCount) {
		this.maximumSameTypeCount = maximumSameTypeCount;
	}


	public void setMaximumEachTypeCount(int maximumEachTypeCount) {
		this.maximumEachTypeCount = maximumEachTypeCount;
	}
    
}
