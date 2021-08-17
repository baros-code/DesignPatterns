package Business;

import DATA.IncorrectQuitButtonException;
import DATA.JSONFileWriter;
import DATA.Report;
import DATA.UndefinedGapStatusException;
import Domain.*;
import Presentation.IMoveable;

import java.util.Random;
import java.util.Scanner;

public class GameEngine {
    enum Level {
        EASY(1),MODERATE(2),HARD(3),EXPERT(4);
        int value;
        Level(int value) {
            this.value=value;
        }
        int getValue() {
            return value;
        }
    }
    private Hero hero;
    private RunTrack runTrack;
    private Monster monster;
    private Level currentLevel;
    private JSONFileWriter reportWriter;
    private String endReason;
    private Report report;
    private int distanceTaken;

    public GameEngine() {
        distanceTaken=0;
        report = null;
        hero = null;
        runTrack = null;
        monster = null;
        currentLevel = null;
        reportWriter =null;
        endReason = "";
        initGame();
    }


    /**
     *  Random integer between 0 - 100 by including both 0 and 100
     * @param upperBound
     * @return integer
     */
    private int getRandomInt(int upperBound) {
        int number = getRandomInt(0,upperBound);
        return number;

    }

    /**
     *  Random integer between lowerBound and upperBound by including both
     * @param upperBound
     * @param lowerBound
     * @return integer
     */

    private int getRandomInt(int lowerBound, int upperBound) {
        Random rand = new Random();
        return rand.nextInt(upperBound+1 - lowerBound) + lowerBound;
    }

    /**
     * creating all objects to start the game
     */

    private void initGame() {
        initRunTrack();
        initObstacles();
        initCurrencies();
        initHero();
        initMonster();
        initLevel();
    }
    /**
     * creating random level
     */
    private void initLevel(){
        int choice = getRandomInt(3); 
        switch(choice){

            case 0:
                currentLevel = Level.EASY;
                break;

            case 1:
                currentLevel = Level.MODERATE;

                break;

            case 2:
                currentLevel = Level.HARD;
                break;

            case 3:
                currentLevel = Level.EXPERT;
                break;
            default:
                break;
        }
    }

    private void initHero(){
        this.hero = new Hero("Jack");
    }
    private void initMonster() {
        this.monster = new Monster();
    }

    private void initRunTrack() {
        int randomPerimeter = getRandomInt(1000,10000);
        int typeOfRunTrack = getRandomInt(1);
        if(typeOfRunTrack == 0)
            this.runTrack = new Jungle(randomPerimeter);
        else
            this.runTrack = new Mountain(randomPerimeter);


    }

    private void initCurrencies() {
        int firstCurLocation = getRandomInt(runTrack.getPerimeter()); // first random location of Currency
        int lengthOfRunTrack=runTrack.getPerimeter();
        int currentLocation=firstCurLocation;
        while (currentLocation < lengthOfRunTrack ) {
            int choice = getRandomInt(2);  // get random type Currency
            Currency c=createCurrency(choice);
            addCurrencyToRunTrack(c,currentLocation);
            currentLocation+=50;  // currencies will exist in every 50 metres.
        }
    }

    private void initObstacles() {
        int firstObsLocation=getRandomInt(runTrack.getPerimeter()); // first random location of obstacle
        int maxAmountOfObs=getRandomInt(runTrack.getPerimeter()/500); // max number of obstacles according to perimeter
        int numObs = 0;
        int lengthOfRunTrack=runTrack.getPerimeter();
        int currentLocation=firstObsLocation;
        while (currentLocation <= lengthOfRunTrack && numObs < maxAmountOfObs) {
            int choice=getRandomInt(3); // get random type Obstacle
            Obstacle currentObstacle=createObstacle(choice);
            addObstacleToRunTrack(currentObstacle,currentLocation);
            currentLocation+=500;
            numObs++;
        }
    }
   /**
     *  Creating Obstacle with random type according to choose.
     * @param choice
     * @return Obstacle
     */




    private Obstacle createObstacle(int choice) { // creating obstacle according to random type
        Obstacle obs=null;
        switch(choice) {

            case 0:
                obs=new Rock();
                break;
            case 1:
                obs=new FelledTree();
                break;
            case 2:
                obs=new Saw();
                break;
            case 3:
                obs=new Aqueduct();
                break;
            default:
                break;
        }
        return obs;
    }
    
    /**
     *  Creating Obstacle with random type according to choose.
     * @param choice
     * @return Currency
     * 
     * 
     */

    private Currency createCurrency(int choice) {
        Currency c=null;
        switch(choice) {

            case 0:
                c=new Coin();
                break;
            case 1:
                c=new Diamond();
                break;
            case 2:
                c=new MagneticCoin();
                break;
            default:
                break;
        }
        return c;
    }

    private void addCurrencyToRunTrack(Currency c,int location) {
        runTrack.addCurrencyToLocation(location,c);
    }

    private void addObstacleToRunTrack(Obstacle o,int location) {
        runTrack.addObstacleToLocation(location,o);
    }

     private boolean isLapped(IMoveable character) { // 
        return (hero.getPosition() == runTrack.getPerimeter());
    }
    /**
     * Method that checks objects are initialized or not.according
     * @throws : NullPointerException
     */
    private void checkInitializations() {
        if(hero == null || monster == null || runTrack == null)
            throw new NullPointerException();
    }

    /**
     * Starts the game, game continues until the hero dies.
     * @return String
     * @throws : UndefinedObstacle or NullPointer exceptions
     */
    public String start() throws Exception {
        checkInitializations();
        while ((hero.isAlive())) {
            int currentLocationId=hero.getPosition();
            int chanceOfHero = getLuckStatus();
            checkLapStatus();
            checkMagnetStatus();
            checkMonsterStatus(chanceOfHero);
            checkLocation(currentLocationId,chanceOfHero);
        }
        distanceTaken+=hero.getPosition();

        return generateReport()+"\n"+"Distance taken: " + distanceTaken;
    }

    /**
     * Hero collects currencies
     * @param c
     * @param location
     */

    private void collect(Currency c,int location){
        if(c.isMagnetic() && hero.hasMagnet() && hero.getMagnet().isActivated()){
            hero.collectCurrency(runTrack.removeCurrencyFromLocation(location));
        }
        else if(!(c.isMagnetic()))
            hero.collectCurrency(runTrack.removeCurrencyFromLocation(location));;

    }
    
    /**
     * It resets the position of the character
     * 
     */
     private void resetPosition(IMoveable character) {
        character.setPosition(0);
    }

    /**
     * asks the user if he wants to quit the game
     */
       private  void askExit() {
            if(exitButtonCheck()) {
                endReason = "Quit.";
                generateReport();
                generateJSONFile();
                finishTheGame();
            }
    }
    private void finishTheGame() {
        hero.setLifeStatus(Hero.lifeStatus.DEAD);
    }


    /**
     * If hero hasn't died , check if it's magnet is active
     */

    private void checkMagnetStatus() {
        if (!hero.isAlive()) { // precondition
            return;
        }
        if (hero.getPoint() >= 5000 && !(hero.hasMagnet())) {
            hero.getMagnet(new Magnet());
        }
    }
    
    /**
     * It calculates the current chance of the Hero.
     */
    private int getLuckStatus() {
        int rand = getRandomInt(1,100);   // 1-100 includes
        return rand;

    }

    /**
     * If hero hasn't died , checks if his/her position same with the Monster's
     * If their location intersects, according to random number Monster can eat the Hero
     */
    private void checkMonsterStatus(int chance) {      
        if (!hero.isAlive()) { // precondition
            return;
        }
        if (hero.getPosition() == monster.getPosition()) {
            if (chance <= 1) {
                monster.eat(hero);
                endReason = "Eaten by the Monster";
            } else {
                monster.run(); // monster is trying to catch
            }
        }
    }
    /**
     * Method checks obstacle situation
     * @param : Obstacle,int
     * @throws : UndefinedGapStatusException
     */
    private void checkObstacle  (Obstacle o,int chance) throws Exception {
        if (o == null) {
            hero.run();
            return;
        }
        if (chance < 5) {
            finishTheGame();
            endReason = "Hero stumbled";
        }
        else if(o.getGapStatus()==(Obstacle.Gap.UP)){
            hero.jump();
        }
        else if(o.getGapStatus() == (Obstacle.Gap.DOWN)){
            hero.slide();
        }
        else {
            throw new UndefinedGapStatusException();
        }

    }
    /**
     * Method checks currency situation
     * @param : Currency,int
     */
    private void checkCurrency(Currency c,int location) {       // ?
        if (!hero.isAlive() || (c == null)) { // precondition
            return;
        }
        collect(c,location);
    }

    private void checkLocation(int locationId,int chance) throws Exception {
        if (!hero.isAlive()) { // precondition
            return;
        }
        Obstacle o=runTrack.getObstacleFromLocation(locationId);
        Currency c=runTrack.getCurrencyFromLocation(locationId);
        checkObstacle(o,chance);
        checkCurrency(c,locationId);
    }

    /**
     * According to current level, it calculates total points that Hero has gained.
     */
    private int totalPoints() {
        return hero.getPoint()*(currentLevel.getValue());

    }
    /**
     * Method checks if the hero ended the lap or not, then makes the necessary settings 
     * and calls askExit method
     * the monster's location is calculated if occur every 1500 meters.
     */
    private void checkLapStatus() {
        if (isLapped(hero)) {
            resetPosition(hero);
            int locationOfMonster=monster.getPosition()-runTrack.getPerimeter(); // Special case to calculate the location of  the monster
            monster.setPosition(locationOfMonster);
            distanceTaken+=runTrack.getPerimeter();
            askExit();  // will ask the player to finish the game each lap.

        }

    }
    /**
     * Method checks user exit demand
     * @return boolean
     * @throws IncorrectQuitButtonException
     */
    private boolean exitButtonCheck() {
    	Scanner sc = new Scanner(System.in);
    	System.out.println("Lap has ended.\nDo you want to finish game: (Y/N)");
    	String input = sc.nextLine();
    	
    	if(!(input.equals("Y") || input.equals("y"))) {
    		return false;
    	}

    	System.out.println("Enter Q to finish game.");
    	try {
    		input = sc.nextLine();
        	if(input.equals("Q") || input.equals("q"))
                return true;
            else
                throw new IncorrectQuitButtonException();
    	}
    	catch(Exception e) {
            System.out.println(e.getMessage());
    	}
    	return false;
    }

    /**
     * It creates a report object that includes necessary information.
     * @return String
     */
    
    private String generateReport() {
        report = new Report(currentLevel.toString(), runTrack.getClass().getSimpleName(),
                endReason,runTrack.getPerimeter(), hero.getChest().getCollectedItems().size(),
                hero.countDiamonds(), totalPoints());

        return report.toString();
    }

    /**
     * Method that initializes reportWriter object and calls writeJSONFile method 
     * to create JSON file.
     */
    private void generateJSONFile(){
        reportWriter = new JSONFileWriter();
        reportWriter.writeJSONFile(report.getLevel(), report.getRunTrackType(),report.getReason(),
                                    report.getPerimeter(), report.getCurrencyAmount(),
                                    report.getDiamondAmount(), report.getTotalScore());
    }


}
