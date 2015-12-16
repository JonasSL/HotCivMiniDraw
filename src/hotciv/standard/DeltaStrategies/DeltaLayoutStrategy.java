package hotciv.standard.DeltaStrategies;

import hotciv.framework.*;
import hotciv.standard.GameImpl;
import hotciv.standard.LayoutStrategy;
import hotciv.standard.TileImpl;

import java.util.ArrayList;

public class DeltaLayoutStrategy implements LayoutStrategy {

    @Override
    public void generateWorld(GameImpl game) {
        String[] layout =
                new String[] {
                        "...ooMooooo.....",
                        "..ohhoooofffoo..",
                        ".oooooMooo...oo.",
                        ".ooMMMoooo..oooo",
                        "...ofooohhoooo..",
                        ".ofoofooooohhoo.",
                        "...ooo..........",
                        ".ooooo.ooohooM..",
                        ".ooooo.oohooof..",
                        "offfoooo.offoooo",
                        "oooooooo...ooooo",
                        ".ooMMMoooo......",
                        "..ooooooffoooo..",
                        "....ooooooooo...",
                        "..ooohhoo.......",
                        ".....ooooooooo..",
                };
        String line;
        for (int r = 0; r < GameConstants.WORLDSIZE; r++ ) {
            line = layout[r];
            for ( int c = 0; c < GameConstants.WORLDSIZE; c++ ) {
                char tileChar = line.charAt(c);
                String type = "error";
                if ( tileChar == '.' ) { type = GameConstants.OCEANS; }
                if ( tileChar == 'o' ) { type = GameConstants.PLAINS; }
                if ( tileChar == 'M' ) { type = GameConstants.MOUNTAINS; }
                if ( tileChar == 'f' ) { type = GameConstants.FOREST; }
                if ( tileChar == 'h' ) { type = GameConstants.HILLS; }
                Position p = new Position(c,r);
                TileImpl t = (TileImpl) game.getTileAt(p);
                t.setTypeString(type);
            }
        }

        //setup cities
        game.addCityToGame(new Position(8,12), Player.RED);
        game.addCityToGame(new Position(4,5), Player.BLUE);

        //setup units
        game.addUnitToGame(GameConstants.LEGION,Player.BLUE,new Position(4,4));
        game.addUnitToGame(GameConstants.ARCHER,Player.RED,new Position(8,3));
        game.addUnitToGame(GameConstants.SETTLER,Player.RED,new Position(5,5));
    }
}
