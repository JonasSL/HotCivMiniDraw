package hotciv.standard;

/**
 * Created by jonaslarsen on 01/12/2015.
 */
public interface UnitStrategy {
    int getAttackingStrength(UnitImpl unit);

    int getDefensiveStrength(UnitImpl unit);

    void produceUnits(GameImpl game);
}
