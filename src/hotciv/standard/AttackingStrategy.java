package hotciv.standard;

import hotciv.framework.Position;

/**
 * Created by jonaslarsen on 24/11/2015.
 */
public interface AttackingStrategy {
    void battleBetweenUnitsAt(Position from, Position to, int distance, GameImpl game);
}
