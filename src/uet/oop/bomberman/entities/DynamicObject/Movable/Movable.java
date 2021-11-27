package uet.oop.bomberman.entities.DynamicObject.Movable;

import javafx.scene.image.Image;
import javafx.util.Pair;
import uet.oop.bomberman.entities.DynamicObject.DynamicObject;
import uet.oop.bomberman.Support.Direction;
import uet.oop.bomberman.entities.Entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static uet.oop.bomberman.Support.Direction.numberOfDirection;

public abstract class Movable extends DynamicObject {

    public static final int defaultSpeed = 4;
    protected double SPF = 0.8;
    protected int moveSpeed = defaultSpeed;
    protected int moveSpeedX = 0;
    protected int moveSpeedY = 0;
    protected Direction direction = Direction.RIGHT;
    protected Direction animationDirection = Direction.RIGHT;
    protected boolean alive = true;
    protected boolean moving = false;
    protected List<List<Image>> moveAnimation = new ArrayList<>();
    protected List<Image> deadAnimation = new ArrayList<Image>();

    public Movable(int xUnit, int yUnit, Image... img) {
        super(xUnit, yUnit, img);
        for (int i = 0; i < numberOfDirection; i++) {
            moveAnimation.add(new ArrayList<>());
        }
    }

    public void move(List<Entity> entities) {
        int newX = x;
        int newY = y;
        boolean changeAnimationDir = false;
        switch (direction) {
            case LEFT:
            case RIGHT:
                newX += moveSpeedX;
                break;
            case UP:
            case DOWN:
                newY += moveSpeedY;
                break;
        }
        x = trueCoordinates(entities, newX, newY).getKey();
        y = trueCoordinates(entities, newX, newY).getValue();
    }

    public void setMoveAnimation(Direction direction, Image... images) {
        Collections.addAll(moveAnimation.get(direction.getValue()), images);
    }

    public abstract Pair<Integer, Integer> trueCoordinates(List<Entity> entities, int newX, int newY);

    public void dead() {
        alive = false;
        timer = -2;
    }
    public void setDeadAnimation(Image... images) {
        Collections.addAll(deadAnimation, images);
    }

    public boolean isAlive() {
        return alive;
    }
}
