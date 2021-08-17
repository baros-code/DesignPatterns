package Business;

import Presentation.IMoveable;

public class Monster implements IMoveable {
	private int currentPosition;

	public Monster() {
		currentPosition = 1500;
	}
	
	public void eat(Hero h) {
		h.setLifeStatus(Hero.lifeStatus.DEAD);

	}

	@Override
	public void run() {
		currentPosition+=1500; // special case for monster


	}

	@Override
	public int getPosition() {
		return currentPosition;
	}

	@Override
	public void setPosition(int position) {
				currentPosition=position;
	}
}
