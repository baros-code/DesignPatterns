package Domain;

public abstract class Obstacle {
	public enum  Gap{UP,DOWN}  //there is a gap above or below.
	private Gap gapStatus;

	public Obstacle(Gap gapStatus) {
		this.gapStatus = gapStatus;
	}
	
	/**
	 * Method returns gapStatus attribute
	 * @return : String
	 */
	public Gap getGapStatus(){
		return gapStatus;
	}

	
}
