package hotciv.standard.Factories;

import hotciv.standard.*;
import hotciv.standard.AlphaStrategies.AlphaUnitStrategy;
import hotciv.standard.BetaStrategies.BetaAgeStrategy;
import hotciv.standard.DeltaStrategies.DeltaLayoutStrategy;
import hotciv.standard.EpsilonStrategies.EpsilonAttackingStrategy;
import hotciv.standard.EpsilonStrategies.EpsilonWinningStrategy;
import hotciv.standard.EpsilonStrategies.TestDice;
import hotciv.standard.GammaStrategies.GammaActionStrategy;

public class SemiFactory implements HotCivFactory {
    @Override
    public WinningStrategy getWinningStrategy() {
        return new EpsilonWinningStrategy();
    }

    @Override
    public AgeStrategy getAgeStrategy() {
        return new BetaAgeStrategy();
    }

    @Override
    public LayoutStrategy getLayoutStrategy() {
        return new DeltaLayoutStrategy();
    }

    @Override
    public AttackingStrategy getAttackingStrategy() {
        return new EpsilonAttackingStrategy(new TestDice(5));
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
