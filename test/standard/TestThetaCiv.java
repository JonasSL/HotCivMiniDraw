package hotciv.standard;

import hotciv.framework.*;
import hotciv.standard.Factories.ThetaFactory;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.*;

public class TestThetaCiv {
    private GameImpl game;

    /**
     * Fixture for alphaciv testing.
     */
    @Before
    public void setUp() {
        game = new GameImpl(new ThetaFactory());
    }

    @Test
    public void citiesCanProduceChariots() {
        game.changeProductionInCityAt(new Position(1,1), ThetaGameConstants.CHARIOT);

        assertNull("city tile is empty", game.getUnitAt(new Position(1,1)));

        game.endTheRoundBy(4);

        assertThat("there is a chariot on the city tile", game.getUnitAt(new Position(1,1)).getTypeString(), is(ThetaGameConstants.CHARIOT));
    }

    @Test
    public void chariotHas3Attack() {
        game.changeProductionInCityAt(new Position(1,1), ThetaGameConstants.CHARIOT);
        game.endTheRoundBy(4);
        //There is now a chariot at (1,1)
        assertThat("chariot has 3 attack", game.getUnitAt(new Position(1,1)).getAttackingStrength(), is(3));
    }

    @Test
    public void chariotHas1Defense() {
        game.changeProductionInCityAt(new Position(1,1), ThetaGameConstants.CHARIOT);
        game.endTheRoundBy(4);
        //There is now a chariot at (1,1)
        assertThat("chariot has 1 defense", game.getUnitAt(new Position(1,1)).getDefensiveStrength(), is(1));
    }

    @Test
    public void chariotCanFortify() {
        game.changeProductionInCityAt(new Position(1,1), ThetaGameConstants.CHARIOT);
        game.endTheRoundBy(4);
        //There is now a chariot at (1,1)
        game.performUnitActionAt(new Position(1,1));
        assertThat("chariot has 2 defense", game.getUnitAt(new Position(1,1)).getDefensiveStrength(), is(2));
    }

    @Test
    public void chariotCost20() {
        CityImpl c = (CityImpl) game.getCityAt(new Position(1,1));
        assertThat("city value is 0", c.getProductionValue(), is(0));
        game.changeProductionInCityAt(new Position(1,1), ThetaGameConstants.CHARIOT);
        game.endTheRoundBy(4);

        //6*4 = 24 .... 24-20 = 4
        assertThat("city value is 4", c.getProductionValue(), is(4));
    }

    @Test
    public void chariotCanTravel1() {
        game.changeProductionInCityAt(new Position(1,1), ThetaGameConstants.CHARIOT);
        game.endTheRoundBy(4);

        boolean moveNotAllowed = game.moveUnit(new Position(1,1), new Position(12,12));
        boolean moveAllowed = game.moveUnit(new Position(1,1), new Position(1,2));
        assertFalse("move should be illegal", moveNotAllowed);
        assertTrue("move should be legal", moveAllowed);
    }
}
