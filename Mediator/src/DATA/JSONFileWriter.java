package DATA;

import org.json.JSONObject;
import java.io.FileWriter;
import java.io.IOException;

public class JSONFileWriter {
	
	private JSONObject obj;

	//The constructor
	public JSONFileWriter() {
		obj = new JSONObject();
	}
	
	/**
	 * Method that creates json file and writes report to it.
	 * @param: String,String,String,int,int,int,int
	 */
	public void writeJSONFile(String level,String runTrackType,String reason, int perimeter,int currencyAmount,
			int diamondAmount,int totalScore) {

		obj.put("Level of the game: ", level);
		obj.put("Type of run track: ", runTrackType);
		obj.put("Perimeter of run track: ", perimeter);
		obj.put("The reason of the game end: ", reason);
		obj.put("Total number of collected currencies: ",currencyAmount);
		obj.put("Total number of collected diamonds: ",diamondAmount);
		obj.put("The total score: ", totalScore);	
		
		try {
			FileWriter fileWriter = new FileWriter("src\\DATA\\REPORT.json");
			fileWriter.write(obj.toString());
			fileWriter.flush();
			fileWriter.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	 
}
