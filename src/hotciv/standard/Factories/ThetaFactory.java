package hotciv.standard.Factories;

import hotciv.standard.*;
import hotciv.standard.AlphaStrategies.AlphaAgeStrategy;
import hotciv.standard.AlphaStrategies.AlphaAttackingStrategy;
import hotciv.standard.AlphaStrategies.AlphaLayoutStrategy;
import hotciv.standard.AlphaStrategies.AlphaWinningStrategy;
import hotciv.standard.ThetaStrategies.ThetaActionStrategy;
import hotciv.standard.ThetaStrategies.ThetaUnitStrategy;

/**
 * Created by jonaslarsen on 01/12/2015.
 */
public class ThetaFactory implements HotCivFactory {
    @Override
    public WinningStrategy getWinningStrategy() {
        return new AlphaWinningStrategy();
    }

    @Override
    public AgeStrategy getAgeStrategy() {
        return new AlphaAgeStrategy();
    }

    @Override
    public LayoutStrategy getLayoutStrategy() {
        return new AlphaLayoutStrategy();
    }

    @Override
    public AttackingStrategy getAttackingStrategy() {
        return new AlphaAttackingStrategy();
    }

    @Override
    public ActionStrategy getActionStrategy() {
        return new ThetaActionStrategy();
    }

    @Override
    public UnitStrategy getUnitStrategy() {
        return new ThetaUnitStrategy();
    }
}
