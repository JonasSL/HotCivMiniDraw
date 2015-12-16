package hotciv.standard.BetaStrategies;

import hotciv.framework.Game;
import hotciv.framework.Player;
import hotciv.standard.CityImpl;
import hotciv.standard.GameImpl;
import hotciv.standard.WinningStrategy;

import java.util.ArrayList;

public class BetaWinningStrategy implements WinningStrategy {
    @Override
    public Player getWinner(GameImpl game) {
        ArrayList<CityImpl> cities = game.getCities();

        Player winner = cities.get(0).getOwner();
        for (CityImpl c:cities) {
            if (!c.getOwner().equals(winner)){
                return null;
            }
        }
        return winner;
    }

    @Override
    public void incrementPlayerCount(Player p, GameImpl game) {
        //do nothing
    }
}
