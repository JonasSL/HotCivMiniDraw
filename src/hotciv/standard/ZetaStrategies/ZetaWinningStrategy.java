package hotciv.standard.ZetaStrategies;

import hotciv.framework.Player;
import hotciv.standard.BetaStrategies.BetaWinningStrategy;
import hotciv.standard.EpsilonStrategies.EpsilonWinningStrategy;
import hotciv.standard.GameImpl;
import hotciv.standard.WinningStrategy;

/**
 * Created by jonaslarsen on 25/11/2015.
 */
public class ZetaWinningStrategy implements WinningStrategy {

    WinningStrategy betaWin = new BetaWinningStrategy();
    WinningStrategy epsWin = new EpsilonWinningStrategy();

    @Override
    public Player getWinner(GameImpl game) {
        WinningStrategy state = betaWin;

        if (game.getCurrentRound() > 20) {
            state = epsWin;
        }

        return state.getWinner(game);
    }

    @Override
    public void incrementPlayerCount(Player p, GameImpl game) {
        if (game.getCurrentRound() > 20) {
            epsWin.incrementPlayerCount(p,game);
        }
        //else do nothing
    }
}
