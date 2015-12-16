package hotciv.standard.AlphaStrategies;

import hotciv.framework.Game;
import hotciv.framework.Player;
import hotciv.standard.GameImpl;
import hotciv.standard.WinningStrategy;

public class AlphaWinningStrategy implements WinningStrategy {
    @Override
    public Player getWinner(GameImpl game) {
        if(game.getAge() == -3000) {
            return Player.RED;
        }
        return null;
    }

    @Override
    public void incrementPlayerCount(Player p, GameImpl game) {
        //do nothing
    }
}
