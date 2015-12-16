package hotciv.standard;

import hotciv.framework.*;

import hotciv.standard.Factories.BetaFactory;
import org.junit.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class TestBetaCiv {
    GameImpl game;

    @Before
    public void setup(){
        game = new GameImpl(new BetaFactory());
    }

    @Test
    public void winnerHasConqueredAllCities() {
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
    public void noWinnerIfCitiesHaveDifferentOwners() {
        //we know that we have two cities with different owners
        assertThat("no winner yet", game.getWinner(), is(nullValue()));
    }

    @Test
    public void afterOneRound100YearsHavePassed() {
        assertThat("the starting year is -4000", game.getAge(), is(-4000));
        game.endTheRoundBy(1);
        assertThat("100 years have passed", game.getAge(), is(-3900));
    }

    @Test
    public void shouldBeYear1BCAfter40Rounds() {
        assertThat("the starting year is -4000", game.getAge(), is(-4000));
        game.endTheRoundBy(40);
        assertThat("1BC after 40 rounds", game.getAge(), is(-1));
    }

    @Test
    public void shouldBeYear1ADAfter41Rounds() {
        assertThat("the starting year is -4000", game.getAge(), is(-4000));
        game.endTheRoundBy(41);
        assertThat("1AD after 41 rounds", game.getAge(), is(1));
    }


    @Test
    public void shouldBeYear50ADAfter42Rounds() {
        assertThat("the starting year is -4000", game.getAge(), is(-4000));
        game.endTheRoundBy(42);
        assertThat("50AD after 42 rounds", game.getAge(), is(50));
    }

    @Test
    public void shouldBeYear100ADAfter43Rounds() {
        assertThat("the starting year is -4000", game.getAge(), is(-4000));
        game.endTheRoundBy(43);
        assertThat("100AD after 43 rounds", game.getAge(), is(100));
    }

    @Test
    public void shouldBeYear1775ADfter77Rounds() {
        assertThat("the starting year is -4000", game.getAge(), is(-4000));
        game.endTheRoundBy(77);
        assertThat("1775AD after 77 rounds", game.getAge(), is(1775));
    }

    @Test
    public void shouldBeYear1905ADAfter83Rounds() {
        assertThat("the starting year is -4000", game.getAge(), is(-4000));
        game.endTheRoundBy(83);
        assertThat("1905AD after 83 rounds", game.getAge(), is(1905));
    }


    @Test
    public void shouldBeYear1971ADAfter97Rounds() {
        assertThat("the starting year is -4000", game.getAge(), is(-4000));
        game.endTheRoundBy(97);
        assertThat("1971AD after 97 rounds", game.getAge(), is(1971));
    }
}
