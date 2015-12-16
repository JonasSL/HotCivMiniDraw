package hotciv.standard.Factories;


import hotciv.standard.*;

/**
 * Created by jonaslarsen on 30/11/2015.
 */
public interface HotCivFactory {

    WinningStrategy getWinningStrategy();
    AgeStrategy getAgeStrategy();
    LayoutStrategy getLayoutStrategy();
    AttackingStrategy getAttackingStrategy();
    ActionStrategy getActionStrategy();
    UnitStrategy getUnitStrategy();
}
