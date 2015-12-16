package hotciv.standard.AlphaStrategies;

import hotciv.standard.AgeStrategy;

public class AlphaAgeStrategy implements AgeStrategy {

    @Override
    public int advanceAge(int year) {
        return year+100;
    }
}
