package hotciv.standard.Factories;

import hotciv.standard.*;
import hotciv.standard.AlphaStrategies.*;
import hotciv.standard.ZetaStrategies.ZetaWinningStrategy;

/**
 * Created by jonaslarsen on 30/11/2015.
 */
public class ZetaFactory implements HotCivFactory {
    @Override
    public WinningStrategy getWinningStrategy() {
        return new ZetaWinningStrategy();
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
        return new AlphaActionStrategy();
    }

    @Override
    public UnitStrategy getUnitStrategy() {
        return new AlphaUnitStrategy();
    }
}
