package hotciv.standard.AlphaStrategies;

import hotciv.framework.Position;
import hotciv.standard.AttackingStrategy;
import hotciv.standard.GameImpl;
import hotciv.standard.UnitImpl;

/**
 * Created by jonaslarsen on 24/11/2015.
 */
public class AlphaAttackingStrategy implements AttackingStrategy {
    @Override
    public void battleBetweenUnitsAt(Position from, Position to, int distance, GameImpl game) {
        //remove defending unit from the game
        game.removeUnitAt(to);
        //move the attacking unit to the new position
        UnitImpl u = (UnitImpl) game.getUnitAt(from);
        u.setMoveCount(u.getMoveCount()-distance);
        u.setPosition(to);
    }
}
