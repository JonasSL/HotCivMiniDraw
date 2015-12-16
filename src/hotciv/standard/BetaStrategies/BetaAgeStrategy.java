package hotciv.standard.BetaStrategies;

import hotciv.standard.AgeStrategy;

public class BetaAgeStrategy implements AgeStrategy {

    @Override
    public int advanceAge(int year) {
        if (year < -100) {
            return year+100;
        } else if (year == -100) {
            return -1;
        } else if (year == -1) {
            return 1;
        } else if (year == 1) {
            return 50;
        } else if (year >= 50 && year < 1750) {
            return year+50;
        } else if (year >= 1750 && year < 1900) {
            return year+25;
        } else if (year >= 1900 && year < 1970) {
            return year+5;
        } else {
            return ++year;
        }
    }
}
