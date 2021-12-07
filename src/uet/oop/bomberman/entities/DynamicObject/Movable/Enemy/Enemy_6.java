package uet.oop.bomberman.entities.DynamicObject.Movable.Enemy;

import javafx.scene.image.Image;
import javafx.util.Pair;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Support.Direction;
import uet.oop.bomberman.entities.Entity;

import java.util.List;

/// Boss di xuyen doc

public class Enemy_6 extends Enemy{
    public Enemy_6(int xUnit, int yUnit, Image... img) {
        super(xUnit, yUnit, img);
        direction = Direction.DOWN;
        moveSpeed = 1;
    }

    @Override
    public void enemyUpdate(List<Entity> entities, long now) {
        moving = true;
        if (direction == Direction.DOWN) {
            if (getYUnit() == BombermanGame.HEIGHT - 2 || getYUnit() == BombermanGame.HEIGHT - 1) {
                direction = Direction.UP;
                moveSpeedY = -moveSpeed;
            } else {
                moveSpeedY = moveSpeed;
            }
        } else if (direction == Direction.UP) {
            if (getYUnit() == 0) {
                direction = Direction.DOWN;
                moveSpeedY = moveSpeed;
            } else {
                moveSpeedX = -moveSpeed;
            }
        }
    }

    public Pair<Integer, Integer> trueCoordinates(List<Entity> entities, int newX, int newY) {
        return new Pair<>(newX, newY);
    }
}
