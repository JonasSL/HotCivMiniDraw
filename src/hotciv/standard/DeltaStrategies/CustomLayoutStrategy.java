package hotciv.standard.DeltaStrategies;

import hotciv.framework.*;
import hotciv.standard.GameImpl;
import hotciv.standard.LayoutStrategy;
import hotciv.standard.TileImpl;

public class CustomLayoutStrategy implements LayoutStrategy {
    private String[] layout;

    public CustomLayoutStrategy(String[] layout) {
        this.layout = layout;
    }

    @Override
    public void generateWorld(GameImpl game) {
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
                Position p = new Position(r,c);
                TileImpl t = (TileImpl) game.getTileAt(p);
                t.setTypeString(type);
            }
        }
    }
}
