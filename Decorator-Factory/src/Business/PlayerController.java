package Business;

import java.util.*;
import java.util.List;

import MainDomain.Player;
import MainDomain.Pointable;



/**
 * Controller that creates players and operates player operations.
 * It can support more than 2 players.
 */
public class PlayerController { // creator
   
	private List<Player> players;       //Contains every player in the simulation.
    private int assignId;               //integer that uniquely identifies each player.



    /**
     * The Constructor
     */
    public PlayerController(int numberOfPlayers) {
        players=new ArrayList<>();
        assignId=0;
        createPlayer(numberOfPlayers);
    }



    /**
     * Method that creates players as much as given integer.
     * @param numberOfPlayers
     */
    private void createPlayer(int numberOfPlayers) {
       for(int i=0;i<numberOfPlayers;i++) {
           Player p=new Player();
           addPlayer(p);
       }
    }

    public int getNumberOfPlayers() {
        return players.size();
    }

    /**
     * Method that adds the given product to player's battleSide.
     * @param p is a product that will be added to the battleSide of the given player.
     * @param playerId is an integer that identifies which player will have the add operations
     */
    public void addPointableObjectToPlayer(Pointable p,int playerId) { // exception will be added for limitation
        Player player=findPlayer(playerId);
        player.addElement(p);
        
    }

    /**
     * Method that adds given player to the list and sets players id.
     * @param p
     */
    public void addPlayer(Player p) {
        players.add(p);
        p.setPlayerId(++assignId);
    }


    /**
     * Method that returns player with a given id number.
     * @param playerId
     * @return Player
     */
    public Player findPlayer(int playerId) {
        for (Player p:players) {
            if (p.getPlayerId() == playerId) {
                return p;
            }
        }
        return null;
    }

    public boolean isEmpty() { // for precondition
        return players.isEmpty();
    }
    public boolean hasProduct(int playerId) {
    	return !findPlayer(playerId).isEmpty();
    }

    /**
     * Method for getting the maximum point achieved among all players.
     * @return integer, that is the maximum point of the simulation.
     */
    public int getMaxPoint() { // returns the total maximum point
        Player p=findPlayer(1); // get first player
        int maxPoint=p.getTotalPoint();
        for (Player currentP:players) {
            int currentPoint=currentP.getTotalPoint();
            if (currentPoint > maxPoint) {
                maxPoint=currentPoint;
            }
        }
        return maxPoint;
    }

    /**
     * Method for getting the player that has maximum point.
     * @return List<Player>, that is the player(s) that has the maximum point.
     */
    public List<Player> getHasMaxPoint() { // returns  player(s) that has max point.
        List<Player> pMaxPoint=new ArrayList<Player>();
        for (Player p: players) {
            if (p.getTotalPoint() == getMaxPoint()) {
                pMaxPoint.add(p);
            }
        }
        return pMaxPoint;
    }


    /**
     * Method for getting the all players as string.
     * @return String, that contains all players separated.
     */
    public String toString() {
        String returnString="";
        for (Player p:players) {
            returnString += p;
            returnString +="--------------\n";
        }
        return returnString;
    }


    /**
     * Method to randomly set the product(s) points of each player.
     */
    public void setPointsOfAllPlayersAccordingToRange() {

        for (Player p:players) {
            p.setPointsRandom();
        }

    }

    public void removeAllProductsOfPlayer() {
        for (Player p:players) {
            p.removeAll();
        }
    }

    /**
     * Method that removes product with a given product id of the player whose id is given.
     * @param playerId
     * @param productId
     */
    public void remove(int playerId,int productId) {
        Player p=findPlayer(playerId);
        p.remove(productId);
    }

    /**
     * Prints total points of all Users with their playerId
     */

    public void printPointsOfAllPlayers() { // prints total points of all Users
        String s="";
        for (Player p:players) {
            s +=   "Total Points Of Player " + p.getPlayerId() + " :" +  p.getTotalPoint() + " \n";
        }
        System.out.println(s);
    }

    /**
     * Method for getting the desired product of desired player.
     * @param productId
     * @param playerId
     * @return Pointable object,that is the product of the given player and has the given productId
     */
    public Pointable getPointableProduct(int playerId,int productId) {
        Player p = findPlayer(playerId);
        if (p != null) {
            return p.findElementById(productId);
        }
        else
            return null;
    }

    /**
     * Method for getting the battleSide of the given player.
     * @param playerId
     * @return Map<Integer,Pointable>,that is the products of the given user. key -> productId, value-> Product
     */
    public Map<Integer,Pointable> getAllProductsOfUser(int playerId) {
        Player p=findPlayer(playerId);
       return p.getBattleVehicles();

    }

    public void resetPointsOfAllPlayers() {
        for(Player p:players) {
            p.resetPointsOfVehicles();
        }
    }
}
