package hotciv.standard.EpsilonStrategies;

import hotciv.framework.*;
import hotciv.standard.*;

public class EpsilonAttackingStrategy implements AttackingStrategy {
    Dice dice;
    public EpsilonAttackingStrategy(Dice d) {
        dice = d;
    }
    @Override
    public void battleBetweenUnitsAt(Position from, Position to, int distance, GameImpl game) {
        if (getTotalCombinedAttack(from, game) > getTotalCombinedDefense(to,game)) {
            //remove defending unit from the game
            game.removeUnitAt(to);
            //move the attacking unit to the new position
            UnitImpl u = (UnitImpl) game.getUnitAt(from);
            u.setMoveCount(u.getMoveCount()-distance);
            u.setPosition(to);
        } else {
            game.removeUnitAt(from);
        }
    }

    public int getTotalCombinedAttack(Position p, GameImpl game) {
        int terrainFactor = Utility.getTerrainFactor(game, p);
        int friendlySupport = Utility.getFriendlySupport(game, p, game.getUnitAt(p).getOwner());
        int totalAttack = (game.getUnitAt(p).getAttackingStrength() + friendlySupport) * terrainFactor;

        return totalAttack * dice.getValue();
    }

    public int getTotalCombinedDefense(Position p, GameImpl game) {
        int terrainFactor = Utility.getTerrainFactor(game, p);
        int friendlySupport = Utility.getFriendlySupport(game, p, game.getUnitAt(p).getOwner());
        int totalDefense = (game.getUnitAt(p).getDefensiveStrength() + friendlySupport) * terrainFactor;

        return totalDefense * dice.getValue();
    }
}
