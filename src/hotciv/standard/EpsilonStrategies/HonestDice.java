package hotciv.standard.EpsilonStrategies;

/**
 * Created by jonaslarsen on 24/11/2015.
 */
public class HonestDice implements Dice {
    @Override
    public int getValue() {
        return  (int) ((Math.random()*6)+1);
    }
}
