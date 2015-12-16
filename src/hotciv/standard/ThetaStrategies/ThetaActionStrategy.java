package hotciv.standard.ThetaStrategies;

import hotciv.framework.Position;
import hotciv.framework.ThetaGameConstants;
import hotciv.standard.ActionStrategy;
import hotciv.standard.GameImpl;
import hotciv.standard.GammaStrategies.GammaActionStrategy;
import hotciv.standard.UnitImpl;

public class ThetaActionStrategy implements ActionStrategy {
    GammaActionStrategy gammaActionStrategy = new GammaActionStrategy();

    @Override
    public void performAction(Position pos, GameImpl game) {
        UnitImpl u = (UnitImpl) game.getUnitAt(pos);

        if (u.getTypeString().equals(ThetaGameConstants.CHARIOT)) {
            if (u.getFortified()) {
                u.setFortified(false);
            } else {
                u.setFortified(true);
            }
        } else {
            gammaActionStrategy.performAction(pos,game);
        }

    }
}


