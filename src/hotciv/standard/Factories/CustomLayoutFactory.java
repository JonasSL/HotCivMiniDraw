package hotciv.standard.Factories;

import hotciv.standard.*;
import hotciv.standard.AlphaStrategies.*;
import hotciv.standard.DeltaStrategies.CustomLayoutStrategy;

/**
 * Created by jonaslarsen on 30/11/2015.
 */
public class CustomLayoutFactory implements HotCivFactory {
    String[] layout;
    public CustomLayoutFactory(String[] layout) {
        this.layout = layout;
    }

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
        return new CustomLayoutStrategy(layout);
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
