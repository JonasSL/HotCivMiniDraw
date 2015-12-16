package hotciv.standard;

import hotciv.framework.*;

import hotciv.standard.Factories.AlphaFactory;
import org.junit.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

/**
 * Skeleton class for AlphaCiv test cases
 * <p/>
 * Updated Oct 2015 for using Hamcrest matchers
 * <p/>
 * This source code is from the book
 * "Flexible, Reliable Software:
 * Using Patterns and Agile Development"
 * published 2010 by CRC Press.
 * Author:
 * Henrik B Christensen
 * Department of Computer Science
 * Aarhus University
 * <p/>
 * Please visit http://www.baerbak.com/ for further information.
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
public class TestAlphaCiv {
    private GameImpl game;

    /**
     * Fixture for alphaciv testing.
     */
    @Before
    public void setUp() {
        game = new GameImpl(new AlphaFactory());
    }

    @Test
    public void redCannotMoveBlueUnits() {
        //we know a blue unit is located at (3,2)
        boolean moveAllowed = game.moveUnit(new Position(3,2), new Position(3,3));
        assertFalse("red cannot move blue units", moveAllowed);
    }

    @Test
    public void shouldHaveRedCityAt1_1() {
        City c = game.getCityAt(new Position(1, 1));
        assertThat("There should be a city at (1,1)",
                c, is(notNullValue()));

        Player p = c.getOwner();
        assertThat("The city should be owned by RED player",
                p, is(Player.RED));
    }

    @Test
    public void shouldHaveOceanAt1_0() {
        Tile t = game.getTileAt(new Position(1, 0));
        assertThat("There should be a ocean at (1,0)",
                t, is(notNullValue()));

        assertThat("There should be an ocean at (1,0)",
                t.getTypeString(), is(GameConstants.OCEANS));
    }

    @Test
    public void redShouldWinAt3000BC() {
        assertThat("time is 4000BC", game.getAge(), is(-4000));
        for(int i=0;i<20;i++) {
            game.endOfTurn();
        }
        assertThat("red should win at 3000BC", game.getWinner(), is(Player.RED));
    }

    @Test
    public void redCantWinBefore3000BC() {
        assertThat("time is 4000BC", game.getAge(), is(-4000));
        assertNull("red doesn't win at 4000BC", game.getWinner());
    }

    @Test
    public void redShouldBeFirst() {
        Player p = game.getPlayerInTurn();
        assertThat("Red should be first", p, is(Player.RED));
    }

    @Test
    public void blueAfterRed() {
        game.endOfTurn();
        Player p = game.getPlayerInTurn();
        assertThat("blue should be after red", p, is(Player.BLUE));
    }

    @Test
    public void citiesShouldBeSize1() {
        City c = game.getCityAt(new Position(1,1));
        int size = c.getSize();
        assertThat("cities should be size 1",size,is(1));
    }

    @Test
    public void shouldProduce6ProductionAtEndOfTurn() {
        CityImpl c = (CityImpl) game.getCityAt(new Position(1, 1));
        int productionAtTurn1 = c.getProductionValue();
        assertThat("production should be 0 at turn 1", productionAtTurn1, is(0));

        game.endOfTurn(); game.endOfTurn();

        int productionAtTurn2 = c.getProductionValue();
        assertThat("production should be 6 at turn 2", productionAtTurn2, is(6));
    }

    @Test
    public void redCanMoveRedUnits() {
        //we know a red unit is located at (2,0)
        boolean moveAllowed = game.moveUnit(new Position(2,0), new Position(2,1));
        assertTrue("Red can move red units", moveAllowed);
    }

    @Test
    public void mountainsExist() {
        Tile t = game.getTileAt(new Position(2,2));
        assertThat("there is a mountain at 2,2", t.getTypeString(), is(GameConstants.MOUNTAINS));
    }

    @Test
    public void archersHaveCorrectBattleMoves() {
        Unit u = game.getUnitAt(new Position(2,0));
        assertThat("archers should have 2 attack", u.getAttackingStrength(), is(2));
        assertThat("archers should have 3 defense", u.getDefensiveStrength(), is(3));
    }

    @Test
    public void legionsHaveCorrectBattleMoves() {
        Unit u = game.getUnitAt(new Position(3,2));
        assertThat("legions should have 4 attack", u.getAttackingStrength(), is(4));
        assertThat("legions should have 2 defense", u.getDefensiveStrength(), is(2));
    }

    @Test
    public void settlersHaveCorrectBattleMoves() {
        Unit u = game.getUnitAt(new Position(4,3));
        assertThat("settlers should have 0 attack", u.getAttackingStrength(), is(0));
        assertThat("settlers should have 3 defense", u.getDefensiveStrength(), is(3));
    }

    @Test
    public void unitCannotMove2() {
        //we know that a unit is located at (4,3)
        assertFalse("unit cannot move 2", game.moveUnit(new Position(4,3), new Position(4,5)));
    }

    @Test
    public void unitCannotStandOnMountain() {
        //we know that a blue unit is located at (3,2) and a mountain is located at (2,2)
        game.endOfTurn();
        boolean moveAllowed = game.moveUnit(new Position(3,2), new Position(2,2));
        assertFalse("unit cannot stand on mountain", moveAllowed);
    }

    @Test
    public void unitCannotStandOnOcean() {
        //we know that a red unit is located at (2,0)
        boolean moveAllowed = game.moveUnit(new Position(2,0), new Position(1,0));
        assertFalse("unit cannot stand on ocean", moveAllowed);
    }

    @Test
    public void returnNullForNoUnit() {
        Unit noUnit = game.getUnitAt(new Position(7,7));
        assertNull("unit doesn't exist", noUnit);
    }

    @Test
    public void returnNullForNoCity() {
        City noCity = game.getCityAt(new Position(7,7));
        assertNull("city doesn't exist", noCity);
    }

    @Test
    public void unitsCanIdentifyThemselves() {
        Unit u = game.getUnitAt(new Position(2,0));
        assertThat("archer can identify itself", u.getTypeString(), is(GameConstants.ARCHER));
    }

    @Test
    public void eachRoundIncreasesYearBy100() {
        assertThat("year should be -4000 in the beginning", game.getAge(), is(-4000));

        game.endOfTurn();
        assertThat("year should not have changed yet!", game.getAge(), is(-4000));
        game.endOfTurn();

        assertThat("year should be -3900 after 1 round", game.getAge(), is(-3900));
    }

    @Test
    public void unitsCanOnlyMoveAsLongAsTheirMoveCountEachRound() {
        Unit u = game.getUnitAt(new Position(2,0));
        boolean moveAllowed = game.moveUnit(new Position(2,0), new Position(2,1));
        assertTrue("movement is allowed", moveAllowed);

        boolean moveNotAllowed = game.moveUnit(new Position(2,1), new Position(2,0));
        assertFalse("movement is not allowed", moveNotAllowed);
    }

    @Test
    public void unitsCanMoveAgainAfterARoundIsFinished() {
        boolean moveAllowed = game.moveUnit(new Position(2,0), new Position(2,1));
        assertTrue("movement is allowed", moveAllowed);

        game.endOfTurn();
        game.endOfTurn();

        boolean moveAllowedAgain = game.moveUnit(new Position(2,1), new Position(2,0));
        assertTrue("movement is allowed again", moveAllowedAgain);
    }

    @Test
    public void citiesCanChangeTheirProduction() {
        CityImpl c = (CityImpl) game.getCityAt(new Position(4,1));
        assertThat("The city produces settler by default", c.getProduction(), is(GameConstants.SETTLER));
        game.changeProductionInCityAt(c.getPosition(), GameConstants.ARCHER);
        assertThat("The city produces archer", c.getProduction(), is(GameConstants.ARCHER));
    }

    @Test
    public void citiesCanChangeTheirWorkForceFocus() {
        CityImpl c = (CityImpl) game.getCityAt(new Position(4,1));
        assertThat("The city's workforce focus is food by default", c.getWorkforceFocus(), is(GameConstants.foodFocus));
        game.changeWorkForceFocusInCityAt(c.getPosition(), GameConstants.productionFocus);
        assertThat("The city's workforce focus is production", c.getWorkforceFocus(), is(GameConstants.productionFocus));
    }

    @Test
    public void noActionShouldBeAssociatedWithUnits() {
        //settler is located at (4,3)
        Unit u = game.getUnitAt(new Position(4,3));
        assertThat("there is a setter", u.getTypeString(), is(GameConstants.SETTLER));

        game.performUnitActionAt(new Position(4,3));

        City c = game.getCityAt(new Position(4,3));
        assertNull("there is no city",c);
    }

    @Test
    public void cityBalanceReducesWhenUnitIsProduced() {
        CityImpl c = (CityImpl) game.getCityAt(new Position(1,1));
        assertThat("productionValue is 0", c.getProductionValue(), is(0));

        game.changeProductionInCityAt(c.getPosition(), GameConstants.ARCHER);

        game.endOfTurn(); game.endOfTurn();
        assertThat("productionValue is 6", c.getProductionValue(), is(6));

        game.endOfTurn(); game.endOfTurn();
        assertThat("productionValue should be 2", c.getProductionValue(), is(2));
    }

    @Test
    public void unitGetPlacedWhenCityProducesUnitWhenNoUnitsAreInTheCity() {
        CityImpl c = (CityImpl) game.getCityAt(new Position(1,1));
        assertNull("no unit is placed on city tile", game.getUnitAt(c.getPosition()));

        game.changeProductionInCityAt(c.getPosition(), GameConstants.ARCHER);

        game.endTheRoundBy(2);

        assertThat("there is an archer unit on city tile", game.getUnitAt(c.getPosition()).getTypeString(), is(GameConstants.ARCHER));
    }

    @Test
    public void unitGetsPlacedCorrectlyAroundTheCityIfTheCityIsOccupied() {
        CityImpl c = (CityImpl) game.getCityAt(new Position(1,1));
        assertNull("no unit is placed on city tile", game.getUnitAt(c.getPosition()));

        game.changeProductionInCityAt(c.getPosition(), GameConstants.ARCHER);

        game.endTheRoundBy(2);

        assertThat("there is an archer unit on city tile", game.getUnitAt(c.getPosition()).getTypeString(), is(GameConstants.ARCHER));

        game.changeProductionInCityAt(c.getPosition(), GameConstants.LEGION);
        game.endTheRoundBy(3);

        assertThat("there is a legion north of the city", game.getUnitAt(new Position(1,2)).getTypeString(), is(GameConstants.LEGION));
    }

    @Test
    public void citiesCanProduceLegions() {
        CityImpl c = (CityImpl) game.getCityAt(new Position(1,1));
        assertNull("no unit is placed on city tile", game.getUnitAt(c.getPosition()));
        game.changeProductionInCityAt(c.getPosition(), GameConstants.LEGION);

        game.endTheRoundBy(3);

        assertThat("there is a legion unit on city tile", game.getUnitAt(c.getPosition()).getTypeString(), is(GameConstants.LEGION));
    }

    @Test
    public void redUnitsCanAttackAndDestroyBlueUnits() {
        //we know that a red settler is located at (4,3) and blue legion at (3,2)
        boolean moveAllowed = game.moveUnit(new Position(4,3),new Position(3,2));
        assertThat("red settler has moved to blue legion", moveAllowed, is(true));

        Unit u = game.getUnitAt(new Position(3,2));
        assertThat("the settler won and is located at (3,2)", u.getTypeString(),is(GameConstants.SETTLER));

        Unit uBefore = game.getUnitAt(new Position(4,3));
        assertNull("the attacking settler is no longer located at (4,3)", uBefore);
    }

    @Test
    public void blueUnitsCanAttackAndDestroyRedUnits() {
        //we know that a blue legion is located at (3,2) and a red settler is located at (4,3)

        boolean moveNotAllowed = game.moveUnit(new Position(3,2), new Position(4,3));
        assertThat("move is not allowed in red's turn", moveNotAllowed, is(false));

        game.endOfTurn();

        boolean moveAllowed = game.moveUnit(new Position(3,2), new Position(4,3));
        assertThat("blue legion can move to red settler", moveAllowed,is(true));

        Unit u = game.getUnitAt(new Position(4,3));
        assertThat("blue legion won the battle and is now standing at (4,3)", u.getTypeString(),is(GameConstants.LEGION));
        assertThat("blue legion is owned by blue", u.getOwner(),is(Player.BLUE));

        Unit u2 = game.getUnitAt(new Position(3,2));
        assertNull("attacking legion is no longer located at (3,2)",u2);
    }

    @Test
    public void producedUnitsGetsPlacedCorrectlyAroundCity() {
        //there is a city at (1,1)
        assertNull("no units are placed on the city tile",game.getUnitAt(new Position(1,1)));

        game.changeProductionInCityAt(new Position(1,1), GameConstants.ARCHER);
        game.endTheRoundBy(2);

        assertThat("archer is now placed on city tile", game.getUnitAt(new Position(1,1)).getTypeString(),is(GameConstants.ARCHER));

        game.endTheRoundBy(2);

        assertThat("archer is now placed north of city tile", game.getUnitAt(new Position(1,2)).getTypeString(),is(GameConstants.ARCHER));

        game.endTheRoundBy(2);
        assertThat("archer is now placed north east of city tile", game.getUnitAt(new Position(2,2)).getTypeString(),is(GameConstants.ARCHER));

        game.endTheRoundBy(2);
        assertThat("archer is now placed east of city tile", game.getUnitAt(new Position(2,1)).getTypeString(),is(GameConstants.ARCHER));

        //we already have a archer at (2,0)
        assertThat("archer is now placed southeast of city tile", game.getUnitAt(new Position(2,0)).getTypeString(),is(GameConstants.ARCHER));

        game.endTheRoundBy(2);
        assertThat("archer is now placed south of city tile", game.getUnitAt(new Position(1,0)).getTypeString(),is(GameConstants.ARCHER));

        game.endTheRoundBy(2);
        assertThat("archer is now placed southwest of city tile", game.getUnitAt(new Position(0,0)).getTypeString(),is(GameConstants.ARCHER));

        game.endTheRoundBy(2);
        assertThat("archer is now placed west of city tile", game.getUnitAt(new Position(0,1)).getTypeString(),is(GameConstants.ARCHER));

        //the adjecent tiles is now occupied
    }

    @Test
    public void onlyOneUnitOnEachTileIsAllowed() {
        //red unit at (2,0) and (4,3)
        game.moveUnit(new Position(2,0), new Position(3,1));
        game.endTheRoundBy(1);
        game.moveUnit(new Position(3,1), new Position(4,2));
        game.endTheRoundBy(1);
        boolean moveNotAllowed = game.moveUnit(new Position(4,2), new Position(4,3));
        assertFalse("you are not allowed to move on top of another unit", moveNotAllowed);
    }

    @Test
    public void attackingUnitAlwaysWins() {
        //red settler at  (4,3)
        //blue legion at (3,2)
        game.moveUnit(new Position(4,3),new Position(3,2));

        assertThat("settler won the battle", game.getUnitAt(new Position(3,2)).getTypeString(),is(GameConstants.SETTLER));
        assertFalse("legion is no longer there", game.getUnitAt(new Position(3,2)).getTypeString().equals(GameConstants.LEGION));
    }

    @Test
    public void citiesCanProduceUnitsAtSouthEastTile() {
        //there is a blue city at (4,1)
        //the city produces settlers, each settler costs 30 production.
        //Each round you gain 6 production, and we need 5 settler to reach the southeast tile
        //So (30*5)/6 = 25
        game.endTheRoundBy(25);
        assertThat("there should be a settler at (5,0)", game.getUnitAt(new Position(5,0)).getTypeString(), is(GameConstants.SETTLER));
    }

    @Test
    public void canOnlyMoveUnitsThatIsPlacedOnTheMap() {
        boolean moveNotAllowed = game.moveUnit(new Position(15,15), new Position(15,14));
        assertFalse("can only move units if they exist", moveNotAllowed);
    }

    @Test
    public void playerCanTakeOverCityByMovingToItWithAUnit() {
        //red settler at (4,3)
        //blue city at (4,1)
        game.moveUnit(new Position(4,3), new Position(4,2));
        game.endTheRoundBy(1);
        game.moveUnit(new Position(4,2), new Position(4,1));

        City c = game.getCityAt(new Position(4,1));
        assertThat("city at (4,1) has become red", c.getOwner(), is(Player.RED));
    }

    @Test
    public void unitCannotNotMove() {
        boolean moveNotAllowed = game.moveUnit(new Position(4,3), new Position(4,3));
        assertFalse("move should not be allowed", moveNotAllowed);
    }
}
