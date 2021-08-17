package Business;

import java.util.Map;
import java.util.Scanner;

import MainDomain.Pointable;



/**
 * Class that contains all the menu display and input methods.
 */
public class MenuContainer {
	
    private static int limit;

    /**
     * Method that gets the input as string and returns a number for corresponding string.
     * @param message
     * @return int
     */
    private static int getInteger(String message) {
        String input = "";
        do {
            System.out.println(message);
            Scanner sc = new Scanner(System.in);
            input=sc.nextLine();
        } while (!integerCheck(input));             //ask until input is a valid integer
        return Integer.parseInt(input);
    }


    /**
     * Method that checks if the given string is an integer.
     * @param value
     * @return boolean
     */
    private static boolean integerCheck(String value){
        try{
            int input=Integer.parseInt(value);
            if (limit == -1) { 					// for checking non-existing vehicle id in game engine
                return true;
            }
            if ( input >= limit || input <= 0 ) {
                throw new NumberFormatException();
            }
        }
        catch(NumberFormatException e){
            System.out.println("Invalid input! ");
            return false;
        }
        return true;
    }
    
    /**
     * Method to display main menu and ask an input.
     * @return int, that is the choice
     */
    public static int mainMenu() {
        limit=6;                            //limit to define index bound.
        String s="";
        s += "[1] PLAYER OPERATIONS \n";
        s += "[2] RUN SIMULATION \n";
        s +="[3] RESET ITEMS OF PLAYERS \n";
        s +="[4] VIEW ALL PLAYERS AND THEIR BATTLE VEHICLES \n";
        s +="[5] EXIT \n";
        String m="Please select the operation you want to execute: ";
        System.out.println(s);

        return getInput(m);
    }


    public static void showWinner(int playerId) {
        if (playerId == -1)  { // means no player is a winner
            System.out.println("Multiple players have maximum point. No winner!");
        } else {
            System.out.println("Player " + playerId + " is winner!");

        }
        System.out.println();
    }


    private static int getInput(String message) {
        int input=getInteger(message);
        return input;
    }


    public static void printSuccessful(String s) {
        System.out.println(s + " operation is Successful!");
    }

    /**
     * Method that prints the given number of players and returns the selected player index.
     * @param numberOfPlayers, integer that indicates how many players should be printed.
     * @return integer, that is the selected player index.
     */
    public static int chooseUser(int numberOfPlayers) {
        limit=numberOfPlayers + 1;
        String s="";
        for (int i=1;i<= numberOfPlayers;i++) {
            s += "[" + i + "]" + " Player " + i +"\n" ;
        }
        String m="Please select the player:";
        System.out.println(s);
        return getInput(m);
    }


    /**
     * Method that displays same type (Plane/Ship) of products that a player has
     * @param pointables, a map that has key-value pairs as productId and product
     * @param userId, an integer that identifies the player
     * @param pointableType, a string that defines which type of products should be printed.
     */

    public static void printSpecificPointablesOfPlayer(Map<Integer,Pointable> pointables, int userId,String pointableType) {
        System.out.println(pointableType + "  Vehicles Of Player " + userId + " :");
        if (pointables.isEmpty()) {
            System.out.println("There is no " + pointableType + " vehicle.");
        } else {
            for (Map.Entry<Integer, Pointable> p : pointables.entrySet()) {
                System.out.println(pointableType + "  id : " + p.getKey() + " --- " + p.getValue());
            }
        }

    }



    public static int playerOperationsMenu(int playerId) {
        limit=4;
        String s="";
        s += "[1] ADD VEHICLE TO PLAYER " + playerId + "\n";
        s += "[2] ADD ADDABLE PART TO PLAYER " + playerId + "\n";
        s +="[3] GO BACK TO MAIN MENU \n";
        String m="Please select the operation you want to execute: ";
        System.out.println(s);
        return getInput(m);
    }

    public static int chooseVehicle() {
        limit=3;
        String s="";
        s += "[1] SHIP \n";
        s += "[2] PLANE \n";
        String m="Please select the vehicle: ";
        System.out.println(s);
        return getInput(m);

    }

    public static int getIdOfVehicle(String vehicleName) {	///listele
        limit=-1;   //reseting limit
        String m="Please enter the " + vehicleName +"  id you want to add a part";
        return getInteger(m);
    }




    public static int chooseEngine() {
        limit=3;
        String s="";
        s += "[1]  TURBOJET \n";
        s += "[2]  PULSEJET \n";
        String m="Please select the engine type you want to add:";
        System.out.println(s);
        return getInput(m);
    }




    public static int choosePlaneType() {
        limit=4;
        String s="";
        s += "[1] FIGHTER \n";
        s += "[2] BOMBER \n";
        s += "[3] MULTIROLE \n";
        String m="Please select the plane type you want to add: ";
        System.out.println(s);
        return getInput(m);
    }



    public static int chooseShipType() {
        limit=4;
        String s="";
        s += "[1] CRUISER \n";
        s += "[2] DESTROYER \n";
        s += "[3] FRIGATE \n";
        String m="Please select the ship type you want to add: ";
        System.out.println(s);
        return getInput(m);
    }

    public static int addablePartPlaneMenu() {
        limit=5;
        String s="";
        s += "[1] ROCKET \n";
        s += "[2] MISSILE \n";
        s +="[3] MACHINE GUN \n";
        s += "[4] BOMB \n";
        String m="Please select the part you want to add: ";
        System.out.println(s);
        return getInput(m);

    }

    public static int addablePartShipMenu() {
        limit=4;
        String s="";
        s += "[1] ROCKET \n";
        s += "[2] TORPEDO \n";
        s +="[3] CANNON \n";
        String m="Please select the part you want to add: ";
        System.out.println(s);
        return getInput(m);
    }


    

}