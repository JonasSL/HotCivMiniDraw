package hotciv.patterns.fractal;

import hotciv.framework.Game;
import hotciv.framework.GameConstants;
import hotciv.framework.Position;
import hotciv.standard.Factories.ThirdPartyFactory;
import hotciv.standard.GameImpl;

/**
 * Created by jonaslarsen on 11/12/2015.
 */
public class DriverFractal {

    public static void main(String[] args) {

        Game game = new GameImpl(new ThirdPartyFactory());
        System.out.println("This is a demontration of third party fractal pattern.");
        for(int i = 0; i< GameConstants.WORLDSIZE; i++) {
            String line = "";
            for(int j = 0; j< GameConstants.WORLDSIZE; j++) {
                line += game.getTileAt(new Position(i,j)).getTypeString() + ", ";
            }
            System.out.println(line);
        }
    }
}
