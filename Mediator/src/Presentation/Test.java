package Presentation;
import Business.GameEngine;

/**
 * @author : Baran Ates 240201019
 * @author : Kemal Selcuk Kaplan 240201022
 * @author : Mert Calis 220103004+
 * @author : Yusuf Alptug Aydin 260201065
 */

public class Test {

    public static void main(String [] args) {
        GameEngine gameEngine = new GameEngine();
        
        try {
        	System.out.println("***Running Hero Game***\n");
            System.out.println("Game finished.\n\nReport:\n"+gameEngine.start());
        }
        
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
    
    }

    
}