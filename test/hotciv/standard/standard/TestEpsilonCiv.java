package hotciv.standard.standard;

import hotciv.framework.*;
import hotciv.standard.EpsilonStrategies.EpsilonAttackingStrategy;
import hotciv.standard.EpsilonStrategies.TestDice;
import hotciv.standard.Factories.EpsilonFactory;
import hotciv.standard.Factories.HotCivFactory;
import hotciv.standard.GameImpl;
import hotciv.standard.TileImpl;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.*;


public class TestEpsilonCiv {
    GameImpl game;
    EpsilonAttackingStrategy attackingStrategy;

    @Before
    public void setup(){
        HotCivFactory factory = new EpsilonFactory(new TestDice(5));
        game = new GameImpl(factory);
        attackingStrategy = (EpsilonAttackingStrategy) factory.getAttackingStrategy();
    }

    @Test
    public void redShouldWinAfter3WinAttacks() {
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
    public void blueShouldWinAfter3WinAttacks() {
        game.addUnitToGame(GameConstants.LEGION, Player.RED,new Position(10,10));
        game.addUnitToGame(GameConstants.LEGION, Player.RED,new Position(11,10));
        game.addUnitToGame(GameConstants.LEGION, Player.RED,new Position(12,10));
        game.addUnitToGame(GameConstants.LEGION, Player.BLUE,new Position(10,11));
        game.addUnitToGame(GameConstants.LEGION, Player.BLUE,new Position(11,11));
        game.addUnitToGame(GameConstants.LEGION, Player.BLUE,new Position(12,11));

        game.endOfTurn();

        game.moveUnit(new Position(10,11), new Position(10,10));
        game.moveUnit(new Position(11,11), new Position(11,10));
        game.moveUnit(new Position(12,11), new Position(12,10));

        assertThat("Blue has now won", game.getWinner(), is(Player.BLUE));
    }

    @Test
    public void redUnitShouldHaveCombinedAttackingStregthOf18() {
        game.addUnitToGame(GameConstants.LEGION,Player.RED, new Position(10,10));
        game.addCityToGame(new Position(10,10), Player.RED);

        //two adjectent units
        game.addUnitToGame(GameConstants.ARCHER,Player.RED, new Position(10,9));
        game.addUnitToGame(GameConstants.ARCHER,Player.RED, new Position(9,10));

        assertThat("combined attack is 18", attackingStrategy.getTotalCombinedAttack(new Position(10,10), game), is(18*5));
    }

    @Test
    public void blueUnitshouldHaveCombinedDefensiveStrengthOf12() {
        game.addUnitToGame(GameConstants.LEGION, Player.BLUE, new Position(10,10));
        TileImpl t = (TileImpl) game.getTileAt(new Position(10,10));
        t.setTypeString(GameConstants.FOREST);

        game.addUnitToGame(GameConstants.ARCHER, Player.BLUE, new Position(9,10));
        game.addUnitToGame(GameConstants.ARCHER, Player.BLUE, new Position(10,11));
        game.addUnitToGame(GameConstants.ARCHER, Player.BLUE, new Position(10,9));
        game.addUnitToGame(GameConstants.ARCHER, Player.BLUE, new Position(11,10));

        assertThat("combined defense is 12", attackingStrategy.getTotalCombinedDefense(new Position(10,10),game), is(12*5));
    }

    @Test
    public void defendingUnitKillsAttacking() {
        game.addUnitToGame(GameConstants.LEGION, Player.BLUE, new Position(10,10));
        game.addUnitToGame(GameConstants.ARCHER, Player.BLUE, new Position(9,10));
        game.addUnitToGame(GameConstants.ARCHER, Player.BLUE, new Position(10,11));
        game.addUnitToGame(GameConstants.ARCHER, Player.BLUE, new Position(10,9));
        game.addUnitToGame(GameConstants.ARCHER, Player.BLUE, new Position(11,10));

        game.addUnitToGame(GameConstants.LEGION,Player.RED, new Position(11,11));

        game.moveUnit(new Position(11,11), new Position(10,10));
        assertThat("there is no red unit at 11,11", game.getUnitAt(new Position(11,11)), is(nullValue()));
        assertThat("there is a blue unit at 10,10", game.getUnitAt(new Position(10,10)).getOwner(), is(Player.BLUE));
    }

    @Test
    public void attackingUnitKillsDefensive() {
        game.addUnitToGame(GameConstants.LEGION, Player.BLUE, new Position(10,10));
        game.addUnitToGame(GameConstants.ARCHER, Player.BLUE, new Position(9,10));
        game.addUnitToGame(GameConstants.ARCHER, Player.BLUE, new Position(10,11));
        game.addUnitToGame(GameConstants.ARCHER, Player.BLUE, new Position(10,9));
        game.addUnitToGame(GameConstants.ARCHER, Player.BLUE, new Position(11,10));

        game.addUnitToGame(GameConstants.LEGION,Player.RED, new Position(11,11));

        game.endOfTurn();
        game.moveUnit(new Position(10,10), new Position(11,11));
        assertThat("there is no blue unit at 10,10", game.getUnitAt(new Position(10,10)), is(nullValue()));
        assertThat("there is a blue unit at 11,11", game.getUnitAt(new Position(11,11)).getOwner(), is(Player.BLUE));
    }
}
