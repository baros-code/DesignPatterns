package Test;

import Business.GameEngine;

/**
 * @author Baran ATEÞ 240201019
 * @author Kemal Selçuk KAPLAN 240201022
 * @author Yusuf Alptuð AYDIN 260201065
 * @author Mert ÇALIÞ 220103004+
 **/

public class BattleGameSimulation {


        public static void main(String[] args) throws Exception {
            GameEngine e=new GameEngine(2);	//According to the argument, simulation can run with multiple players.
            e.init();
        }
    
}