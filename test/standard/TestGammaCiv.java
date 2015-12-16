package hotciv.standard;

import hotciv.framework.*;

import hotciv.standard.Factories.GammaFactory;
import org.junit.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;


public class TestGammaCiv {
    GameImpl game;

    @Before
    public void setup(){
        game = new GameImpl(new GammaFactory());
    }

    @Test
    public void settlerCanMakeACity() {
        //settler is located at (4,3)
        Unit u = game.getUnitAt(new Position(4,3));
        assertThat("there is a setter", u.getTypeString(), is(GameConstants.SETTLER));

        game.performUnitActionAt(new Position(4,3));

        City c = game.getCityAt(new Position(4,3));
        assertTrue("there is a city at (4,3)", c instanceof City);
    }

    @Test
    public void settlerCantCreateACityOnTopOfAnother() {
        //settler is located at (4,3) and red city at (1,1)
        Unit u = game.getUnitAt(new Position(4,3));
        assertThat("there is a settler", u.getTypeString(), is(GameConstants.SETTLER));
        game.moveUnit(new Position(4,3), new Position(3,2));
        game.endTheRoundBy(1);
        game.moveUnit(new Position(3,2),new Position(2,1));
        game.endTheRoundBy(1);
        game.moveUnit(new Position(2,1), new Position(1,1));

        assertThat("settler is now at (1,1)", game.getUnitAt(new Position(1,1)).getTypeString(), is(GameConstants.SETTLER));

        game.performUnitActionAt(new Position(1,1));

        Unit u2 = game.getUnitAt(new Position(1,1));
        assertTrue("there is a unit at (1,1)", u2 instanceof Unit);
    }

    @Test
    public void archerCanFortify() {
        //there is an archer at (2,0)
        int defenseBefore = game.getUnitAt(new Position(2,0)).getDefensiveStrength();

        assertThat("there is an archer at (2,0)", game.getUnitAt(new Position(2,0)).getTypeString(), is(GameConstants.ARCHER));
        game.performUnitActionAt(new Position(2,0));

        Unit u = game.getUnitAt(new Position(2,0));
        assertThat("archer have doubled its defense", u.getDefensiveStrength(), is(defenseBefore*2));
    }

    @Test
    public void fortifiedArchersCantMove() {
        assertThat("there is an archer at (2,0)", game.getUnitAt(new Position(2,0)).getTypeString(), is(GameConstants.ARCHER));
        game.performUnitActionAt(new Position(2,0));

        boolean moveNotAllowed = game.moveUnit(new Position(2,0),new Position(2,1));
        assertFalse("move is not allowed", moveNotAllowed);

    }

    @Test
    public void archersCanUnfortify() {
        //there is an archer at (2,0)
        assertThat("there is an archer at (2,0)", game.getUnitAt(new Position(2,0)).getTypeString(), is(GameConstants.ARCHER));
        game.performUnitActionAt(new Position(2,0));

        Unit u = game.getUnitAt(new Position(2,0));
        assertThat("archers is now fortified", u.getDefensiveStrength(), is(6));
        game.performUnitActionAt(new Position(2,0));
        assertThat("archers is now unfortified", u.getDefensiveStrength(), is(3));
    }

    @Test
    public void unfortifiedArchersCanMove() {
        //there is an archer at (2,0)
        game.performUnitActionAt(new Position(2,0));
        game.performUnitActionAt(new Position(2,0));
        boolean moveAllowed = game.moveUnit(new Position(2,0),new Position(2,1));
        assertTrue("move is allowed", moveAllowed);

    }

}
