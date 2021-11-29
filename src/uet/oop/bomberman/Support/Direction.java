package uet.oop.bomberman.Support;

public enum Direction {
    LEFT(0), RIGHT(1), UP(2), DOWN(3);
    private int value;
    public static final int numberOfDirection = 4;

    Direction(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static Direction getDirection(int value) {
        if (value == 0) {
            return Direction.LEFT;
        }
        if (value == 1) {
            return Direction.RIGHT;
        }
        if (value == 2) {
            return Direction.UP;
        }
        return Direction.DOWN;
    }
}
