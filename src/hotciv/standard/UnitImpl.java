package hotciv.standard;

import hotciv.framework.*;

public class UnitImpl implements Unit {

    private String type;
    private Player player;
    private Position position;
    private int defensiveStrength;
    private int attackStrength;
    private int moveCount;
    private Boolean isFortified;
    private UnitStrategy unitStrategy;


    public UnitImpl(String t, Player p, Position pos, UnitStrategy unitStrategy) {
        type = t;
        player = p;
        position = pos;
        moveCount = 1;
        isFortified = false;
        this.unitStrategy = unitStrategy;
    }
    @Override
    public String getTypeString() {
        return type;
    }

    @Override
    public Player getOwner() {
        return player;
    }

    @Override
    public int getMoveCount() {
        return moveCount;
    }

    @Override
    public int getDefensiveStrength() {
        return unitStrategy.getDefensiveStrength(this);
    }

    @Override
    public int getAttackingStrength() {
        return unitStrategy.getAttackingStrength(this);
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void setMoveCount(int mc) {
        moveCount = mc;
    }

    public Boolean getFortified() {
        return isFortified;
    }

    public void setFortified(Boolean fortified) {
        isFortified = fortified;
    }
}