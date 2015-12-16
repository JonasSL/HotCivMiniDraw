package hotciv.standard.EpsilonStrategies;

/**
 * Created by jonaslarsen on 24/11/2015.
 */
public class TestDice implements Dice {

    int value;

    public TestDice(int v) {
        value = v;
    }

    @Override
    public int getValue() {
        return value;
    }
}
