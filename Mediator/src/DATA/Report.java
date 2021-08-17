package DATA;

public class Report {
	private String level;
	private String runTrackType;
	private String reason;
	private int perimeter;
	private int currencyAmount;
	private	int diamondAmount;
	private int totalScore;

	public Report(String level, String runTrackType, String reason, int perimeter, int currencyAmount,
			int diamondAmount, int totalScore) {
		this.level = level;
		this.runTrackType = runTrackType;
		this.reason = reason;
		this.perimeter = perimeter;
		this.currencyAmount = currencyAmount;
		this.diamondAmount = diamondAmount;
		this.totalScore = totalScore;
	}
	
	public String toString() {
		return ("Level of the game: "+ level+"\n")+
		("Type of run track: "+ runTrackType+"\n")+
		("Perimeter of run track: "+ perimeter+"\n")+
		("The reason of the game end: "+ reason+"\n")+
		("Total number of collected currencies: "+currencyAmount+"\n")+
		("Total number of collected diamonds: "+diamondAmount+"\n")+
		("The total score: "+ totalScore);	
	}
	
	public String getLevel() {
		return level;
	}
	public String getRunTrackType() {
		return runTrackType;
	}
	public String getReason() {
		return reason;
	}
	public int getPerimeter() {
		return perimeter;
	}
	public int getCurrencyAmount() {
		return currencyAmount;
	}
	public int getDiamondAmount() {
		return diamondAmount;
	}
	public int getTotalScore() {
		return totalScore;
	}
	
	
	
}
