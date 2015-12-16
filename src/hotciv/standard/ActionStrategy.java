package hotciv.standard;

import hotciv.framework.Position;

public interface ActionStrategy {
    void performAction(Position pos, GameImpl game);
}
