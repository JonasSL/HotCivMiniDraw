package hotciv.standard.EpsilonStrategies;

import hotciv.framework.Player;
import hotciv.standard.GameImpl;
import hotciv.standard.WinningStrategy;

import java.util.HashMap;
import java.util.Map;

public class EpsilonWinningStrategy implements WinningStrategy {
    Map<Player, Integer> winningPlayerCounter;

    public EpsilonWinningStrategy() {
        winningPlayerCounter = new HashMap<>();
        for (Player p: Player.values()) {
            winningPlayerCounter.put(p,0);
        }

    }

    @Override
    public Player getWinner(GameImpl game) {
        for (Player p: winningPlayerCounter.keySet()) {
            if (winningPlayerCounter.get(p) == 3) {
                return p;
            }
        }
        return null;
    }

    @Override
    public void incrementPlayerCount(Player p, GameImpl game) {
        Integer temp = winningPlayerCounter.get(p) + 1;
        winningPlayerCounter.put(p,temp);
    }
}
