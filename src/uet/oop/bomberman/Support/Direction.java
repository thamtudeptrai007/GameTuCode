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
}
