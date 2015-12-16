package hotciv.standard.Factories;

import hotciv.standard.*;
import hotciv.standard.AlphaStrategies.*;
import hotciv.standard.GammaStrategies.GammaActionStrategy;

/**
 * Created by jonaslarsen on 30/11/2015.
 */
public class GammaFactory implements HotCivFactory {
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
        return new GammaActionStrategy();
    }

    @Override
    public UnitStrategy getUnitStrategy() {
        return new AlphaUnitStrategy();
    }
}
