package hotciv.standard.AlphaStrategies;

import hotciv.framework.*;
import hotciv.standard.CityImpl;
import hotciv.standard.GameImpl;
import hotciv.standard.UnitImpl;
import hotciv.standard.UnitStrategy;

/**
 * Created by jonaslarsen on 01/12/2015.
 */
public class AlphaUnitStrategy implements UnitStrategy {
    @Override
    public int getAttackingStrength(UnitImpl unit) {
        if (unit.getTypeString().equals(GameConstants.ARCHER)) {
            return 2;
        } else if (unit.getTypeString().equals(GameConstants.LEGION)) {
            return 4;
        } else {
            return 0;
        }
    }

    @Override
    public int getDefensiveStrength(UnitImpl unit) {
        if (unit.getTypeString().equals(GameConstants.ARCHER)) {
            if (unit.getFortified()) {
                return 6;
            } else {
                return 3;
            }
        } else if (unit.getTypeString().equals(GameConstants.LEGION)) {
            return 2;
        } else {
            return 3;
        }
    }

    @Override
    public void produceUnits(GameImpl game) {
        for(CityImpl c: game.getCities()) {
            if(c.getProduction().equals(GameConstants.ARCHER) && c.getProductionValue() >= 10) {
                c.setProductionValue(c.getProductionValue() - 10);
                game.placeUnitAroundCityAt(c.getPosition(),GameConstants.ARCHER);

            } else if (c.getProduction().equals(GameConstants.LEGION) && c.getProductionValue() >= 15) {
                c.setProductionValue(c.getProductionValue() - 15);
                game.placeUnitAroundCityAt(c.getPosition(),GameConstants.LEGION);

            } else if (c.getProduction().equals(GameConstants.SETTLER) && c.getProductionValue() >= 30) {
                c.setProductionValue(c.getProductionValue() - 30);
                game.placeUnitAroundCityAt(c.getPosition(),GameConstants.SETTLER);
            }
        }
    }
}
