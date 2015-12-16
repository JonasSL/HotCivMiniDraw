package hotciv.standard.Factories;

import hotciv.standard.*;
import hotciv.standard.AlphaStrategies.AlphaActionStrategy;
import hotciv.standard.AlphaStrategies.AlphaAgeStrategy;
import hotciv.standard.AlphaStrategies.AlphaLayoutStrategy;
import hotciv.standard.AlphaStrategies.AlphaUnitStrategy;
import hotciv.standard.EpsilonStrategies.Dice;
import hotciv.standard.EpsilonStrategies.EpsilonAttackingStrategy;
import hotciv.standard.EpsilonStrategies.EpsilonWinningStrategy;

public class EpsilonFactory implements HotCivFactory {
    Dice dice;
    EpsilonAttackingStrategy atk;

    public EpsilonFactory(Dice dice) {
        this.dice = dice;
        atk = new EpsilonAttackingStrategy(dice);
    }

    @Override
    public WinningStrategy getWinningStrategy() {
        return new EpsilonWinningStrategy();
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
        return atk;
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
