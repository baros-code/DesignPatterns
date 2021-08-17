package Domain;

import java.util.ArrayList;
import java.util.List;


public class Chest<T extends Presentation.ICollectable> {

	private List<T> collectedItems;

	public Chest() {
		collectedItems = new ArrayList<T>();
	}

	public void addItem(T c) {
		
		collectedItems.add(c);
	}
	
	public List<T> getCollectedItems(){
		return collectedItems;
	}

	
}
