package Business;

import Domain.Currency;
import Domain.Diamond;
import Domain.Magnet;
import Domain.Chest;
import Presentation.IMoveable;

public class Hero implements IMoveable {
	
	enum lifeStatus {AlIVE,DEAD}
	private String name;
	private Chest <Currency> chest;
	private int currentPosition;
	private Magnet magnet;
	private int point;
	private lifeStatus lifeStatus;



	public Hero(String name) {
		this.name = name;
		currentPosition = 0;
		point = 0;
		magnet=null;
		chest=new Chest <Currency>();
		this.lifeStatus= lifeStatus.AlIVE;
	}

	public boolean hasMagnet() {
		return (magnet != null);
	}


	public void addPoint(int point) {
		this.point+=point;
	}

	public boolean isAlive() {
		return (this.lifeStatus == lifeStatus.AlIVE);
	}

	public void setLifeStatus(lifeStatus l) {
		lifeStatus=l;

	}

	public void collectCurrency(Currency c) {
		if(c != null) { //precondition
			chest.addItem(c);
			// this.point += c.getPoint()*this.multiple;
			addPoint(c.getPoint());
		} 
		else throw new IllegalArgumentException();
	}
	/**
	 * Business.Hero runs and get 1 points for each run
	 */
	public void run() {
		currentPosition++;
		addPoint(1);
	}
	/**
	 * Business.Hero makes jump move and get two points for each jump
	 */
	public void jump() {
		
		currentPosition++;
		addPoint(2);
	}
	/**
	 * Business.Hero makes slide move and get two points for each slide
	 */
	public void slide() {
		
		currentPosition++;
		addPoint(2);
	}
	
	/**
	 * Activates Heroe's Domain.Magnet
	 */
	public void getMagnet(Magnet magnet) {
		this.magnet = magnet;
		this.magnet.activate();
	}


	public int countDiamonds(){
		int counter = 0;
		Currency diamond=new Diamond();
		for (Currency c : chest.getCollectedItems()) {
			if(c.getClass() .equals(diamond.getClass()))
				counter++;
		}
		return counter;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Chest <Currency> getChest() {
		return chest;
	}

	public int getPosition() {
		return currentPosition;
	}

	public void setPosition(int position) {
		this.currentPosition = position;
	}


	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public Magnet getMagnet(){
		return magnet;
	}


}
