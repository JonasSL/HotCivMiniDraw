package hotciv.standard.GammaStrategies;

import hotciv.framework.*;
import hotciv.standard.ActionStrategy;
import hotciv.standard.GameImpl;
import hotciv.standard.UnitImpl;

public class GammaActionStrategy implements ActionStrategy {
    @Override
    public void performAction(Position pos, GameImpl game) {
        UnitImpl u = (UnitImpl) game.getUnitAt(pos);

        if (u.getTypeString().equals(GameConstants.SETTLER)) {
            if (game.getCityAt(pos) == null) {
                game.removeUnitAt(pos);
                game.addCityToGame(pos,u.getOwner());
            }
        } else if (u.getTypeString().equals(GameConstants.ARCHER)) {
            if (u.getFortified()) {
                u.setFortified(false);
            } else {
                u.setFortified(true);
            }

        }
    }
}
