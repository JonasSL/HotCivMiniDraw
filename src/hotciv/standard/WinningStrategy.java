package hotciv.standard;

import hotciv.framework.Game;
import hotciv.framework.Player;

public interface WinningStrategy {
    Player getWinner(GameImpl game);
    void incrementPlayerCount(Player p, GameImpl game);
}
