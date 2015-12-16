package hotciv.standard.Factories;

import hotciv.standard.*;
import hotciv.standard.AlphaStrategies.AlphaActionStrategy;
import hotciv.standard.AlphaStrategies.AlphaAttackingStrategy;
import hotciv.standard.AlphaStrategies.AlphaLayoutStrategy;
import hotciv.standard.AlphaStrategies.AlphaUnitStrategy;
import hotciv.standard.BetaStrategies.BetaAgeStrategy;
import hotciv.standard.BetaStrategies.BetaWinningStrategy;

/**
 * Created by jonaslarsen on 30/11/2015.
 */
public class BetaFactory implements HotCivFactory {
    @Override
    public WinningStrategy getWinningStrategy() {
        return new BetaWinningStrategy();
    }

    @Override
    public AgeStrategy getAgeStrategy() {
        return new BetaAgeStrategy();
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
        return new AlphaActionStrategy();
    }

    @Override
    public UnitStrategy getUnitStrategy() {
        return new AlphaUnitStrategy();
    }
}
