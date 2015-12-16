package hotciv;

import hotciv.framework.*;
import hotciv.standard.GameImpl;

public class TranscripedGame implements Game {
    Game game;

    public TranscripedGame(Game game) {
        this.game = game;
    }

    @Override
    public boolean moveUnit(Position from, Position to) {
        System.out.println("Unit is moved from " + from.toString() +" to " + to.toString() );
        return game.moveUnit(from,to);
    }

    @Override
    public void endOfTurn() {
        System.out.println("Turn has been ended.");
        game.endOfTurn();
    }

    @Override
    public Tile getTileAt(Position p) {
        return null;
    }

    @Override
    public Unit getUnitAt(Position p) {
        return null;
    }

    @Override
    public City getCityAt(Position p) {
        return null;
    }

    @Override
    public Player getPlayerInTurn() {
        return null;
    }

    @Override
    public Player getWinner() {
        return null;
    }

    @Override
    public int getAge() {
        return 0;
    }

    @Override
    public void changeWorkForceFocusInCityAt(Position p, String balance) {

    }

    @Override
    public void changeProductionInCityAt(Position p, String unitType) {

    }

    @Override
    public void performUnitActionAt(Position p) {

    }

    @Override
    public void addObserver(GameObserver observer) {

    }

    @Override
    public void setTileFocus(Position position) {

    }
}
