package hotciv.standard.standard;

import hotciv.framework.*;
import hotciv.standard.Factories.CustomLayoutFactory;
import hotciv.standard.Factories.DeltaFactory;
import hotciv.standard.GameImpl;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;


public class TestDeltaCiv {
    GameImpl game;

    @Before
    public void setup(){
        game = new GameImpl(new DeltaFactory());
    }

    @Test
    public void citiesArePlacedCorrectly() {
        City c1 = game.getCityAt(new Position(8,12));
        City c2 = game.getCityAt(new Position(4,5));
        assertTrue("There is a city at (8,12)", c1 instanceof City);
        assertTrue("There is a city at (4,5)", c2 instanceof City);
    }

    @Test
    public void someOceansAreCorrectlyPlaced() {
        Tile t1 = game.getTileAt(new Position(15,15));
        Tile t2 = game.getTileAt(new Position(0,0));
        Tile t3 = game.getTileAt(new Position(0,15));
        Tile t4 = game.getTileAt(new Position(15,0));
        Tile t5 = game.getTileAt(new Position(10,10));

        assertThat("tile is ocean", t1.getTypeString(), is(GameConstants.OCEANS));
        assertThat("tile is ocean", t2.getTypeString(), is(GameConstants.OCEANS));
        assertThat("tile is ocean", t3.getTypeString(), is(GameConstants.OCEANS));
        assertThat("tile is ocean", t4.getTypeString(), is(GameConstants.OCEANS));
        assertThat("tile is ocean", t5.getTypeString(), is(GameConstants.OCEANS));
    }

    @Test
    public void someMountainsAreCorrectlyPlaced() {
        Tile t1 = game.getTileAt(new Position(5,0));
        Tile t2 = game.getTileAt(new Position(5,3));
        Tile t3 = game.getTileAt(new Position(13,7));
        Tile t4 = game.getTileAt(new Position(4,11));

        assertThat("tile is mountain1", t1.getTypeString(), is(GameConstants.MOUNTAINS));
        assertThat("tile is mountain2", t2.getTypeString(), is(GameConstants.MOUNTAINS));
        assertThat("tile is mountain3", t3.getTypeString(), is(GameConstants.MOUNTAINS));
        assertThat("tile is mountain4", t4.getTypeString(), is(GameConstants.MOUNTAINS));
    }

    @Test
    public void someHillsAreCorrectlyPlaced() {
        Tile t1 = game.getTileAt(new Position(4,1));
        Tile t2 = game.getTileAt(new Position(11,5));
        Tile t3 = game.getTileAt(new Position(10,7));
        Tile t4 = game.getTileAt(new Position(9,8));

        assertThat("tile is hill", t1.getTypeString(), is(GameConstants.HILLS));
        assertThat("tile is hil2", t2.getTypeString(), is(GameConstants.HILLS));
        assertThat("tile is hill3", t3.getTypeString(), is(GameConstants.HILLS));
        assertThat("tile is hill4", t4.getTypeString(), is(GameConstants.HILLS));
    }

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

    @Test
    public void unitsArePlacedCorrectly() {

        assertThat("there is a legion at (4,4)", game.getUnitAt(new Position(4,4)).getTypeString(), is(GameConstants.LEGION));
        assertThat("there is a settler at (5,5)", game.getUnitAt(new Position(5,5)).getTypeString(), is(GameConstants.SETTLER));
        assertThat("there is a legion at (8,3)", game.getUnitAt(new Position(8,3)).getTypeString(), is(GameConstants.ARCHER));
    }

    @Test
    public void customLayoutWorksCorrectly() {
        String[] layout =
                new String[] {
                        "MMMMMMMMMMMMMMMM",
                        "MMMMMMMMMMMMMMMM",
                        "MMMMMMMMMMMMMMMM",
                        "MMMMMMMMMMMMMMMM",
                        "MMMMMMMMMMMMMMMM",
                        "MMMMMMMMMMMMMMMM",
                        "MMMMMMMMMMMMMMMM",
                        "MMMMMMMMMMMMMMMM",
                        ".MMMMMMMMMMMMMMM",
                        "MMMMMMMMMMMMMMMM",
                        "MMMMMMMMMMMMMMMM",
                        "MMMMMMMMMMMMMMMM",
                        "MMMMMMMMMMMMMMMM",
                        "MMMMMMMMMMMMMMMM",
                        "MMMMMMMMMMMMMMMM",
                        "MMMMMMMMMMMMMMMM",
                };

        game = new GameImpl(new CustomLayoutFactory(layout));

        assertThat("there is a lonely ocean at (8,0)", game.getTileAt(new Position(8,0)).getTypeString(), is(GameConstants.OCEANS));
    }

}
