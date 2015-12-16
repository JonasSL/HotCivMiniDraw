package hotciv.standard;

import hotciv.framework.*;
import hotciv.standard.Factories.ZetaFactory;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.*;

public class TestZetaCiv {
    private GameImpl game;

    /**
     * Fixture for alphaciv testing.
     */
    @Before
    public void setUp() {
        game = new GameImpl(new ZetaFactory());
    }

    @Test
    public void winnerHasConqueredAllCitiesWhenTheRoundsAreLowerThan20() {
        //blue legion at (3,2)
        //red city at (1,1)
        game.endOfTurn();
        game.moveUnit(new Position(3,2), new Position(3,1));
        game.endTheRoundBy(1);
        game.moveUnit(new Position(3,1), new Position(2,1));
        game.endTheRoundBy(1);
        game.moveUnit(new Position(2,1), new Position(1,1));

        City c = game.getCityAt(new Position(1,1));
        assertThat("city at (4,1) has become red", c.getOwner(), is(Player.BLUE));
        assertThat("red should win because it has all cities", game.getWinner(), is(Player.BLUE));
    }

    @Test
    public void noWinnerAfter20RoundsAndCitiesHaveSameOwner() {

        //
        game.endTheRoundBy(50);

        CityImpl c = (CityImpl) game.getCityAt(new Position(1,1));
        c.setOwner(Player.BLUE);
        assertThat("city at (4,1) has become red", c.getOwner(), is(Player.BLUE));
        assertThat("red should win because it has all cities", game.getWinner(), is(nullValue()));
    }

    @Test
    public void redShouldNOTWinAfter3WinAttacksWhenRoundsAreLowerThan20() {
        game.addUnitToGame(GameConstants.LEGION, Player.RED,new Position(10,10));
        game.addUnitToGame(GameConstants.LEGION, Player.RED,new Position(11,10));
        game.addUnitToGame(GameConstants.LEGION, Player.RED,new Position(12,10));
        game.addUnitToGame(GameConstants.LEGION, Player.BLUE,new Position(10,11));
        game.addUnitToGame(GameConstants.LEGION, Player.BLUE,new Position(11,11));
        game.addUnitToGame(GameConstants.LEGION, Player.BLUE,new Position(12,11));

        game.moveUnit(new Position(10,10), new Position(10,11));
        game.moveUnit(new Position(11,10), new Position(11,11));
        game.moveUnit(new Position(12,10), new Position(12,11));

        assertThat("no winner", game.getWinner(), is(nullValue()));
    }

    @Test
    public void redShouldWinAfter3WinAttacksWhenRoundsAreHigherThan20() {
        game.endTheRoundBy(50);
        game.addUnitToGame(GameConstants.LEGION, Player.RED,new Position(10,10));
        game.addUnitToGame(GameConstants.LEGION, Player.RED,new Position(11,10));
        game.addUnitToGame(GameConstants.LEGION, Player.RED,new Position(12,10));
        game.addUnitToGame(GameConstants.LEGION, Player.BLUE,new Position(10,11));
        game.addUnitToGame(GameConstants.LEGION, Player.BLUE,new Position(11,11));
        game.addUnitToGame(GameConstants.LEGION, Player.BLUE,new Position(12,11));

        game.moveUnit(new Position(10,10), new Position(10,11));
        game.moveUnit(new Position(11,10), new Position(11,11));
        game.moveUnit(new Position(12,10), new Position(12,11));

        assertThat("red has now won", game.getWinner(), is(Player.RED));
    }

    @Test
    public void epsilonCounterDoesNotStartBeforeRound20() {
        game.addUnitToGame(GameConstants.LEGION, Player.RED,new Position(10,10));
        game.addUnitToGame(GameConstants.LEGION, Player.RED,new Position(11,10));
        game.addUnitToGame(GameConstants.LEGION, Player.RED,new Position(12,10));
        game.addUnitToGame(GameConstants.LEGION, Player.BLUE,new Position(10,11));
        game.addUnitToGame(GameConstants.LEGION, Player.BLUE,new Position(11,11));
        game.addUnitToGame(GameConstants.LEGION, Player.BLUE,new Position(12,11));

        game.moveUnit(new Position(10,10), new Position(10,11));
        game.moveUnit(new Position(11,10), new Position(11,11));
        game.moveUnit(new Position(12,10), new Position(12,11));

        game.endTheRoundBy(50);

        assertThat("red has now won", game.getWinner(), is(nullValue()));
    }
}
