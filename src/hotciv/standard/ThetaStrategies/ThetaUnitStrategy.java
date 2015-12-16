package hotciv.standard.ThetaStrategies;

import hotciv.framework.ThetaGameConstants;
import hotciv.standard.AlphaStrategies.AlphaUnitStrategy;
import hotciv.standard.CityImpl;
import hotciv.standard.GameImpl;
import hotciv.standard.UnitImpl;
import hotciv.standard.UnitStrategy;


public class ThetaUnitStrategy implements UnitStrategy {

    AlphaUnitStrategy alphaUnitStrategy = new AlphaUnitStrategy();
    @Override
    public int getAttackingStrength(UnitImpl unit) {
        if (unit.getTypeString().equals(ThetaGameConstants.CHARIOT)) {
            return 3;
        } else {
            return alphaUnitStrategy.getAttackingStrength(unit);
        }
    }

    @Override
    public int getDefensiveStrength(UnitImpl unit) {
        if (unit.getTypeString().equals(ThetaGameConstants.CHARIOT)) {
            if (unit.getFortified()) {
                return 2;
            } else {
                return 1;
            }
        } else {
            return alphaUnitStrategy.getDefensiveStrength(unit);
        }
    }

    @Override
    public void produceUnits(GameImpl game) {
        for(CityImpl c: game.getCities()) {
            if(c.getProduction().equals(ThetaGameConstants.CHARIOT) && c.getProductionValue() >= 20) {
                c.setProductionValue(c.getProductionValue() - 20);
                game.placeUnitAroundCityAt(c.getPosition(),ThetaGameConstants.CHARIOT);
            }
        }

        alphaUnitStrategy.produceUnits(game);
    }
}
