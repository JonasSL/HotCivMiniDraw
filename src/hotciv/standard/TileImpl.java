package hotciv.standard;

import hotciv.framework.*;

/**
 * Created by jonaslarsen on 04/11/2015.
 */
public class TileImpl implements Tile {
    private String type;
    private Position position;

    public TileImpl(String t, Position pos) {
        type = t;
        position = pos;
    }

    @Override
    public String getTypeString() {
        return type;
    }

    public Position getPosition() {
        return position;
    }

    public void setTypeString(String t) {
        type = t;
    }
}
