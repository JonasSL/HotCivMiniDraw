package hotciv.standard;

import hotciv.framework.GameConstants;
import hotciv.standard.DeltaStrategies.CustomLayoutStrategy;
import thirdparty.ThirdPartyFractalGenerator;

/**
 * Created by jonaslarsen on 11/12/2015.
 */
public class ThirdPartyAdapter implements LayoutStrategy {
    @Override
    public void generateWorld(GameImpl game) {
        ThirdPartyFractalGenerator generator =
                new ThirdPartyFractalGenerator();
        String[] map = new String[GameConstants.WORLDSIZE];
        String line;
        for ( int r = 0; r < GameConstants.WORLDSIZE; r++ ) {
            line = "";
            for ( int c = 0; c < GameConstants.WORLDSIZE; c++ ) {
                line = line + generator.getLandscapeAt(r,c);
            }
            map[r] = line;
        }

        CustomLayoutStrategy strat = new CustomLayoutStrategy(map);
        strat.generateWorld(game);
    }
}
