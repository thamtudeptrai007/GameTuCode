package uet.oop.bomberman.Support;

public class Info {
    private int infoXUnit;
    private int infoYUnit;
    private Direction direction;

    public Info() {

    }

    public Info(int infoXUnit, int infoYUnit, Direction direction) {
        this.infoXUnit = infoXUnit;
        this.infoYUnit = infoYUnit;
        this.direction = direction;
    }

    public int getInfoXUnit() {
        return infoXUnit;
    }

    public int getInfoYUnit() {
        return infoYUnit;
    }

    public Direction getDirection() {
        return direction;
    }
}
