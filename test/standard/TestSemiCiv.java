package hotciv.standard;

import hotciv.framework.*;
import hotciv.standard.EpsilonStrategies.EpsilonAttackingStrategy;
import hotciv.standard.Factories.SemiFactory;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class TestSemiCiv {
    private GameImpl game;
    EpsilonAttackingStrategy attackingStrategy;

    /**
     * Fixture for alphaciv testing.
     */
    @Before
    public void setUp() {
        SemiFactory factory = new SemiFactory();
        game = new GameImpl(factory);
        attackingStrategy = (EpsilonAttackingStrategy) factory.getAttackingStrategy();

    }

    //Test from betaCiv
    @Test
    public void shouldBeYear1ADAfter41Rounds() {
        assertThat("the starting year is -4000", game.getAge(), is(-4000));
        game.endTheRoundBy(41);
        assertThat("1AD after 41 rounds", game.getAge(), is(1));
    }

    //Test from gammaCiv
    @Test
    public void archerCanFortify() {
        //there is an archer at (8,3)
        int defenseBefore = game.getUnitAt(new Position(8,3)).getDefensiveStrength();

        assertThat("there is an archer at (8,3)", game.getUnitAt(new Position(8,3)).getTypeString(), is(GameConstants.ARCHER));
        game.performUnitActionAt(new Position(8,3));

        Unit u = game.getUnitAt(new Position(8,3));
        assertThat("archer have doubled its defense", u.getDefensiveStrength(), is(defenseBefore*2));
    }

    //Test from deltaCiv
    @Test
    public void someForestAreCorrectlyPlaced() {
        Tile t1 = game.getTileAt(new Position(9,1));
        Tile t2 = game.getTileAt(new Position(11,1));
        Tile t3 = game.getTileAt(new Position(1,9));
        Tile t4 = game.getTileAt(new Position(8,12));

        assertThat("tile is forest1", t1.getTypeString(), is(GameConstants.FOREST));
        assertThat("tile is forest2", t2.getTypeString(), is(GameConstants.FOREST));
        assertThat("tile is forest3", t3.getTypeString(), is(GameConstants.FOREST));
        assertThat("tile is forest4", t4.getTypeString(), is(GameConstants.FOREST));
    }

    //Test from epsilonCiv
    @Test
    public void blueShouldWinAfter3WinAttacks() {
        game.addUnitToGame(GameConstants.LEGION, Player.RED,new Position(1,7));
        game.addUnitToGame(GameConstants.LEGION, Player.RED,new Position(2,7));
        game.addUnitToGame(GameConstants.LEGION, Player.RED,new Position(3,7));
        game.addUnitToGame(GameConstants.LEGION, Player.BLUE,new Position(1,8));
        game.addUnitToGame(GameConstants.LEGION, Player.BLUE,new Position(2,8));
        game.addUnitToGame(GameConstants.LEGION, Player.BLUE,new Position(3,8));

        game.endOfTurn();

        boolean move1 = game.moveUnit(new Position(1,8), new Position(1,7));
        boolean move2 = game.moveUnit(new Position(2,8), new Position(2,7));
        boolean move3 = game.moveUnit(new Position(3,8), new Position(3,7));

        assertTrue("move1", move1);
        assertTrue("move2", move2);
        assertTrue("move3", move3);

        assertThat("Blue has now won", game.getWinner(), is(Player.BLUE));
    }

    //Test from epsilonCiv
    @Test
    public void redUnitShouldHaveCombinedAttackingStregthOf18() {
        game.addUnitToGame(GameConstants.LEGION,Player.RED, new Position(10,10));
        game.addCityToGame(new Position(10,10), Player.RED);

        //two adjectent units
        game.addUnitToGame(GameConstants.ARCHER,Player.RED, new Position(10,9));
        game.addUnitToGame(GameConstants.ARCHER,Player.RED, new Position(9,10));

        assertThat("combined attack is 18", attackingStrategy.getTotalCombinedAttack(new Position(10,10), game), is(18*5));
    }
}
