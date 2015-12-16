package hotciv.standard;

import hotciv.framework.*;
import hotciv.standard.Factories.HotCivFactory;

import java.util.ArrayList;

/**
 * Skeleton implementation of HotCiv.
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

public final class GameImpl implements Game {

    private int currentPlayer;
    private ArrayList<Player> players;
    private ArrayList<CityImpl> cities;
    private ArrayList<UnitImpl> units;
    private ArrayList<TileImpl> tiles;
    private int year;
    private WinningStrategy winningStrategy;
    private AgeStrategy ageStrategy;
    private ActionStrategy actionStrategy;
    private LayoutStrategy layoutStrategy;
    private AttackingStrategy attackingStrategy;
    private int currentRound;
    private UnitStrategy unitStrategy;
    private ArrayList<GameObserver> observers;

    public GameImpl(HotCivFactory factory) {
        currentPlayer = 0;
        cities = new ArrayList<>();
        units = new ArrayList<>();
        tiles = new ArrayList<>();
        players = new ArrayList<>();
        year = -4000;
        observers = new ArrayList<>();

        players.add(Player.RED);
        players.add(Player.BLUE);

        //setup game tiles
        for (int i = 0; i<GameConstants.WORLDSIZE; i++) {
            for (int j = 0; j<GameConstants.WORLDSIZE; j++) {
                addTileToGame(GameConstants.PLAINS, new Position(i,j));
            }
        }

        //setup for different game variants
        winningStrategy = factory.getWinningStrategy();
        ageStrategy = factory.getAgeStrategy();
        actionStrategy = factory.getActionStrategy();
        layoutStrategy = factory.getLayoutStrategy();
        attackingStrategy = factory.getAttackingStrategy();
        unitStrategy = factory.getUnitStrategy();

        layoutStrategy.generateWorld(this);
    }

    public Tile getTileAt(Position p) {
        Tile tile = new TileImpl("errorTile", new Position(-1, -1));
        for(TileImpl t: tiles) {
            if (t.getPosition().equals(p)) {
                tile = t;
            }
        }
        //if tile is not found, return tile at (0,0)
        return tile;
    }

    public Unit getUnitAt(Position p) {
        for(UnitImpl u: units) {
            if (u.getPosition().equals(p)) {
                return u;
            }
        }
        return null;

    }

    public City getCityAt(Position p) {
        for(CityImpl c: cities) {
            if (c.getPosition().equals(p)) {
                return c;
            }
        }
        return null;
    }

    public Player getPlayerInTurn() {
        return players.get(currentPlayer);
    }

    public Player getWinner() {
        return winningStrategy.getWinner(this);
    }

    public int getAge() {
        return year;
    }

    public boolean moveUnit(Position from, Position to) {
        int distance = Math.max(Math.abs(from.getColumn() - to.getColumn()), Math.abs(from.getRow() - to.getRow()));

        //distance has to be above 0
        if (distance == 0) {
            return false;
        }
        //unit can't move to mountain
        if (getTileAt(to).getTypeString().equals(GameConstants.MOUNTAINS)) {
            return false;
        }
        //unit can't swim
        if (getTileAt(to).getTypeString().equals(GameConstants.OCEANS)) {
            return false;
        }

        //check if to-position is empty
        if (getUnitAt(to) != null) {
            if (getUnitAt(to).getOwner().equals(players.get(currentPlayer))) {
                return false;
            } else {
                battleBetweenUnitsAt(from, to, distance);
                worldChangeNotify(from);
                worldChangeNotify(to);
                return true;
            }
        }
        //check if to position is city
        if (getCityAt(to) != null) {
            if (!getCityAt(to).getOwner().equals(players.get(currentPlayer))) {
                takeOverCity(to);
            }
        }

        UnitImpl u = null;
        for(UnitImpl temp: units) {
            if (temp.getPosition().equals(from)) {
                u = temp;
            }
        }

        //return false if no unit was found
        if(u == null) {
            return false;
        }

        //fortified units cant move
        UnitImpl uFort = (UnitImpl) getUnitAt(from);
        if (uFort.getFortified()) {
            return false;
        }

        //check if the unit is owned by the current player
        if (!u.getOwner().equals(players.get(currentPlayer))) {
            return false;
        }

        //check if unit has enough moves for the move
        if (!(u.getMoveCount() >= distance)) {
            return false;
        }

        u.setMoveCount(u.getMoveCount()-distance);
        u.setPosition(to);

        worldChangeNotify(from);
        worldChangeNotify(to);
        return true;
    }

    private void takeOverCity(Position pos) {
        CityImpl c = (CityImpl) getCityAt(pos);
        c.setOwner(players.get(currentPlayer));
    }

    private void battleBetweenUnitsAt(Position from, Position to, int distance) {
        attackingStrategy.battleBetweenUnitsAt(from, to, distance, this);
        winningStrategy.incrementPlayerCount(players.get(currentPlayer), this);
    }

    public void endTheRoundBy(int noOfRounds) {
        for(int i=0;i<noOfRounds*2;i++ ){
            endOfTurn();
        }
    }

    public void endOfTurn() {
        if (currentPlayer == players.size()-1) {
            currentPlayer = 0;
            advanceYear();
            increaseProductionValue(6);
            currentRound++;
        } else {
            currentPlayer++;
        }

        for(UnitImpl u:units) {
            u.setMoveCount(1);
        }

        unitStrategy.produceUnits(this);

        //Notify the observers
        for (GameObserver obs: observers) {
            obs.turnEnds(players.get(currentPlayer), year);
        }
    }

    private void advanceYear() {
        year = ageStrategy.advanceAge(year);
    }

    public void placeUnitAroundCityAt(Position p, String production) {
        ArrayList<Position> positions = new ArrayList<>();
        positions.add(p);
        positions.add(new Position(p.getRow(),p.getColumn()+1));
        positions.add(new Position(p.getRow()+1,p.getColumn()+1));
        positions.add(new Position(p.getRow()+1,p.getColumn()));
        positions.add(new Position(p.getRow()+1,p.getColumn()-1));
        positions.add(new Position(p.getRow(),p.getColumn()-1));
        positions.add(new Position(p.getRow()-1,p.getColumn()-1));
        positions.add(new Position(p.getRow()-1,p.getColumn()));
        positions.add(new Position(p.getRow()-1,p.getColumn()+1));

        for(Position pos: positions) {
            if(getUnitAt(pos) == null) {
                addUnitToGame(production, getCityAt(p).getOwner(), pos);
                worldChangeNotify(pos);
                break;
            }
        }
    }

    public void changeWorkForceFocusInCityAt(Position p, String balance) {
        CityImpl c = (CityImpl) getCityAt(p);
        c.setWorkForceFocus(balance);
    }



    public void changeProductionInCityAt(Position p, String unitType) {
        CityImpl c = (CityImpl) getCityAt(p);
        c.setProduction(unitType);
    }

    public void performUnitActionAt(Position p) {
        actionStrategy.performAction(p,this);
        worldChangeNotify(p);
    }

    @Override
    public void addObserver(GameObserver observer) {
        observers.add(observer);
    }

    @Override
    public void setTileFocus(Position position) {
        for (GameObserver obs: observers) {
            obs.tileFocusChangedAt(position);
        }
    }

    private void increaseProductionValue(int change) {
        for (CityImpl c: cities) {
            c.setProductionValue(c.getProductionValue() + change);
        }
    }

    public void addCityToGame(Position pos, Player owner) {
        CityImpl c = new CityImpl(pos,owner);
        cities.add(c);
    }
    public void addUnitToGame(String gc, Player p, Position pos) {
        UnitImpl u = new UnitImpl(gc,p,pos,unitStrategy);
        units.add(u);
    }
    public void addTileToGame(String type, Position pos) {
        TileImpl t = new TileImpl(type,pos);
        tiles.add(t);
    }

    public ArrayList<CityImpl> getCities(){
        return cities;
    }

    public void removeUnitAt(Position pos) {
        units.remove(getUnitAt(pos));
    }

    public int getCurrentRound() {
        return currentRound;
    }

    private void worldChangeNotify(Position pos) {
        for (GameObserver obs: observers) {
            obs.worldChangedAt(pos);
        }
    }
}
