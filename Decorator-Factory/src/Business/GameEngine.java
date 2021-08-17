package Business;
import Factories.*;
import MainDomain.Player;
import MainDomain.Pointable;

import java.util.*;


/**
 * Class that runs the simulation and controls it.
 */

public class GameEngine {

    private int currentPlayerId;
    private PlayerController pController;
    private LimitationController lController;
    private int currentPointableId;

    public GameEngine(int numberOfUser) {
        pController = new PlayerController(numberOfUser);
        lController = new LimitationController(5, 2, 3); // assign the limits to the controller
        currentPlayerId = 0;
    }



    public void init() throws Exception {

        while (true) { //isGameOver
            pController.resetPointsOfAllPlayers(); // when program starts, all points shoul be reset.
            mainMenuOperation();
        }

    }

    /**
     * Method that displays the main menu and calls the corresponding method according to player input.
     * @throws Exception
     */

    public void mainMenuOperation() throws Exception {
        int mainSelect=MenuContainer.mainMenu();
        switch (mainSelect) {
            case 1: // select the player
                getPlayerFromInput();
                playerOperations();
                break;
            case 2:// run the similation
                pController.setPointsOfAllPlayersAccordingToRange(); //resetPoints(); // reset points according to range
                System.out.println(pController);
                pController.printPointsOfAllPlayers();
                printWinner();
                break;
            case 3:
                resetItems(); // reset items
                System.out.println("All items have been resetted.");
                break;
            case 4: // show players and their vehicles
                System.out.println(pController);
                break;
            case 5:
            	System.out.println("Terminating...");
            	Thread.sleep(1000);
            	System.out.println("Terminated.");
                System.exit(0); // finish the game
                break;
            default:
                break;
        }

    }



    /**
     * Method that displays the player operations menu and calls the corresponding method according to player input.
     * Method loops until player chooses to go to Main Menu.
     * @throws Exception, Exception can be LimitationException, InvalidIdException,...
     */
    public void playerOperations() throws Exception {
        boolean isContinue=true;
        while (isContinue) {
            try {
                viewPlayer();
                int choice = MenuContainer.playerOperationsMenu(currentPlayerId);
                switch (choice) {
                    case 1: // add vehicle
                        int vehicleType = MenuContainer.chooseVehicle();
                        if (vehicleType == 1) {
                            addShip();
                        } else if (vehicleType == 2) {
                            addPlane();
                        }
                        break;
                    case 2: // add part to vehicle
                    	vehicleType = MenuContainer.chooseVehicle();
                    	vehicleExistenceChecker(vehicleType);
                    	if (vehicleType == 1) {
                            MenuContainer.printSpecificPointablesOfPlayer(getSpecificPointableOfUser("Ship"),currentPlayerId,"Ship");
                            addPartToShip();
                        } else if (vehicleType == 2) {
                            MenuContainer.printSpecificPointablesOfPlayer(getSpecificPointableOfUser("Plane"),currentPlayerId,"Plane");
                            addPartToPlane();
                        }
                        break;
                    case 3:                     //GO BACK TO MAIN MENU, BREAK THE LOOP
                        isContinue=false;
                        break;
                }
               if (isContinue) {
                MenuContainer.printSuccessful("ADD");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("Add Operation is unsuccessful");
                System.out.println();
            }

        }
    }

    /**
     * Method that displays the current player by calling it's toString method.
     */
    public void viewPlayer() {
        Player p=pController.findPlayer(currentPlayerId);
        System.out.println(p);

    }

    /**
     * Method that calls the create method of the given factory.
     * @param f, a factory that is responsible for creation of the product.
     */
    public Pointable createPointableObject(BattleFactory f)  {
        return f.createBattleProduct();
    }


    /**
     * Method that sets the current player according to the user input
     */
    public void getPlayerFromInput() { // exception  for -1
        currentPlayerId =  MenuContainer.chooseUser(pController.getNumberOfPlayers());
    }



    /**
     * Method that displays the planes with their ids, asks player to add an addable part to a plane and
     * adds the selected addable part to selected plane.
     * @throws InvalidIdException, if player enters an id that does not correspond to any plane id that he/she has.
     */
    public void addPartToPlane() throws InvalidIdException {
            currentPointableId = MenuContainer.getIdOfVehicle("Plane");
            addAddableChecker("Plane", currentPointableId);
            Pointable p = pController.getPointableProduct(currentPlayerId, currentPointableId);
            int getType = MenuContainer.addablePartPlaneMenu();
            Pointable aPart = createPointableObject(new PlaneAddablePartFactory(p, getType));
            pController.remove(currentPlayerId, currentPointableId);
            pController.addPointableObjectToPlayer(aPart, currentPlayerId);
    }

    /**
     * Method that removes all the items that all the players have.
     */
    public void resetItems() {
        pController.removeAllProductsOfPlayer();
    }

    
    /**
     * Method that displays the plane types and engine types,
     * adds the selected plane and engine type to the player's battle side. 
     * @throws LimitationException, if the add operations is not allowed.
     */
    public void addPlane() throws LimitationException {
        int planeType=MenuContainer.choosePlaneType();  //-1
        int engineType = MenuContainer.chooseEngine();  //-1
        Pointable p=null;
        if (planeType == 1) {
            addVehicleChecker("Plane","Fighter");
            p=createPointableObject(new FighterPlaneFactory(engineType));
        } else if (planeType == 2) {
            addVehicleChecker("Plane","Bomber");
            p=createPointableObject(new BomberPlaneFactory(engineType));
        } else if (planeType == 3) {
            addVehicleChecker("Plane","Multirole");
            p=createPointableObject(new MultiroleFactory(engineType));
        }
        pController.addPointableObjectToPlayer(p, currentPlayerId);
    }




    /**
     * Method that checks whether the given product (Plane/Ship) and its type (Fighter,Cruiser,...) is allowed to be added.
     * @throws LimitationException, if add operation is not allowed.
     */
    private void addVehicleChecker(String type,String subType) throws LimitationException {
        List<Pointable> products = getAsList(getSpecificPointableOfUser(type));
        List<Pointable> allPointables=getAsList(pController.getAllProductsOfUser(currentPlayerId));
        lController.checkAllConditions(allPointables, products,subType);
    }

    /**
     * Method that checks whether the given product exists
     * @throws InvalidIdException, if the product with given id does not exist.
     */
    private boolean addAddableChecker(String type,int id) throws InvalidIdException {
        if(!isIdValid(id, getSpecificPointableOfUser(type))){
            throw new InvalidIdException("\nThere is no " + type + " vehicle with that ID.");

        }
        return true;
    }


     /**
     * Method that displays the ship types and
     * adds the selected ship to the player's battle side. 
     * @throws LimitationException, if the add operations is not allowed.
     */
    public void addShip() throws LimitationException {
        int shipType=MenuContainer.chooseShipType();
        Pointable s=null;
        if (shipType == 1) {
            addVehicleChecker("Ship","Cruiser");
            s=createPointableObject(new CruiserShipFactory());
        } else if (shipType == 2) {
            addVehicleChecker("Ship","Destroyer");
            s=createPointableObject(new DestroyerShipFactory());
        } else if (shipType == 3) {
            addVehicleChecker("Ship","Frigate");
            s=createPointableObject(new FrigateShipFactory());
        } 
        pController.addPointableObjectToPlayer(s, currentPlayerId);
    }


    /**
     * Method that displays the ships with their ids, asks player to add an addable part to a ship and
     * adds the selected addable part to selected ship.
     * @throws LimitationException, if player enters an id that does not correspond to any ship id that he/she has.
     */
    public void addPartToShip() throws LimitationException {   //type check to prevent choosing plane
        currentPointableId=MenuContainer.getIdOfVehicle("Ship");
        addAddableChecker("Ship", currentPointableId);
        Pointable ship=pController.getPointableProduct(currentPlayerId, currentPointableId);
        int getType=MenuContainer.addablePartShipMenu();    //-1
        Pointable aPart=createPointableObject(new ShipAddablePartFactory(ship,getType)); // ship and apart is added
        pController.remove(currentPlayerId,currentPointableId);
        pController.addPointableObjectToPlayer(aPart, currentPlayerId);
    }

    /**
     * Method that displays the winner of the simulation.
     */
    public void printWinner() {
        List<Player> winnerList=pController.getHasMaxPoint();
        if (winnerList.size() == 1) {
            int winnerId=winnerList.get(0).getPlayerId(); // get id of the winner
            MenuContainer.showWinner(winnerId);
        }  else {
            MenuContainer.showWinner(-1);
        }

    }

    /**
     * Method that returns a map that contains only given specific type of products of the current player.
     * @param pointableType, String that defines which type of products should be returned.
     * @return objects, that contains key-value pairs of the type-specific products. key->product id, value-> product
     */
    public Map<Integer,Pointable> getSpecificPointableOfUser(String pointableType) {
        Map<Integer,Pointable> objects=new HashMap<Integer,Pointable>();
        Map<Integer,Pointable> objectsOfUser=pController.getAllProductsOfUser(currentPlayerId);
        for (Map.Entry<Integer,Pointable>  p:objectsOfUser.entrySet()) {
            Pointable currentP=p.getValue();
            if ( currentP.getType().equals(pointableType )) {
                objects.put(p.getKey(),p.getValue());
            }
        }
        return objects;
    }


    /**
     * Method that transforms the given map into a list.
     */
    public List<Pointable>  getAsList(Map<Integer,Pointable> pointablesMap) {
        List<Pointable>  listP=new ArrayList<Pointable>();
        for (Map.Entry<Integer,Pointable>  p:pointablesMap.entrySet()) {
            Pointable currentP=p.getValue();
            listP.add(currentP);
        }
        return listP;

    }

    /**
     * Method that checks whether the given id occurs in the given Map
     * @param id
     * @param pointables
     * @return boolean, true if map contains the given id.
     */
    private boolean isIdValid(int id,Map<Integer,Pointable> pointables) {
        for (Map.Entry<Integer,Pointable>  p : pointables.entrySet()) {
            int currentId=p.getKey();
            if (currentId == id) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Method that checks any vehicle exists of given type.
     * @param vehicleType
     */
    private void vehicleExistenceChecker(int vehicleType) {
    	String type ="";
    	if(vehicleType == 1) type = "Ship"; 
    	else if(vehicleType == 2) type = "Plane";
    	List<Pointable> list = getAsList(getSpecificPointableOfUser(type));
    	if(list.isEmpty()) 
    		throw new IllegalArgumentException("Player has no "+type+".\nAdd part operation is not allowed!\n");
    }
}


