package Test;

import Business.GameEngine;

/**
 * @author Baran ATE� 240201019
 * @author Kemal Sel�uk KAPLAN 240201022
 * @author Yusuf Alptu� AYDIN 260201065
 * @author Mert �ALI� 220103004+
 **/

public class BattleGameSimulation {


        public static void main(String[] args) throws Exception {
            GameEngine e=new GameEngine(2);	//According to the argument, simulation can run with multiple players.
            e.init();
        }
    
}