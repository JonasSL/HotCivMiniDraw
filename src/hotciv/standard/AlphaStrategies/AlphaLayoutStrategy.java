package hotciv.standard.AlphaStrategies;

import hotciv.framework.*;
import hotciv.standard.GameImpl;
import hotciv.standard.LayoutStrategy;
import hotciv.standard.TileImpl;

/**
 * Created by jonaslarsen on 23/11/2015.
 */
public class AlphaLayoutStrategy implements LayoutStrategy {

    public void generateWorld(GameImpl game) {

        TileImpl t10 = (TileImpl)game.getTileAt(new Position(1,0));
        t10.setTypeString(GameConstants.OCEANS);

        TileImpl t11 = (TileImpl)game.getTileAt(new Position(0,1));
        t11.setTypeString(GameConstants.HILLS);

        TileImpl t22 = (TileImpl)game.getTileAt(new Position(2,2));
        t22.setTypeString(GameConstants.MOUNTAINS);

        //setup game units
        game.addUnitToGame(GameConstants.LEGION, Player.BLUE,new Position(3,2));
        game.addUnitToGame(GameConstants.ARCHER,Player.RED,new Position(2,0));
        game.addUnitToGame(GameConstants.SETTLER,Player.RED,new Position(4,3));

        //setup cities
        game.addCityToGame(new Position(1,1), Player.RED);
        game.addCityToGame(new Position(4,1), Player.BLUE);
    }
}
