package hotciv.standard;

import hotciv.framework.*;

public class CityImpl implements City {


    private int currentProductionValue;
    private Position position;
    private Player owner;
    private String production;
    private String workForceFocus;

    public CityImpl(Position pos, Player owner) {
        currentProductionValue = 0;
        position = pos;
        this.owner = owner;
        production = GameConstants.SETTLER;
        workForceFocus = GameConstants.foodFocus;
    }

    @Override
    public Player getOwner() {
        return owner;
    }

    @Override
    public int getSize() {
        return 1;
    }

    @Override
    public String getProduction() {
        return production;
    }

    @Override
    public String getWorkforceFocus() {
        return workForceFocus;
    }

    public int getProductionValue() {
        return currentProductionValue;
    }

    public void setProductionValue(int value) {
        currentProductionValue = value;
    }

    public Position getPosition() {
        return position;
    }

    public void setProduction(String production) {
        this.production = production;
    }

    public void setWorkForceFocus(String workForceFocus) {
        this.workForceFocus = workForceFocus;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

}
