package uet.oop.bomberman.entities.DynamicObject.Movable.Enemy;

import javafx.scene.image.Image;
import javafx.util.Pair;
import uet.oop.bomberman.Support.Direction;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

import java.util.List;
import java.util.Random;

public class Enemy_1_random extends Enemy {
    public Enemy_1_random(int xUnit, int yUnit, Image... img) {
        super(xUnit, yUnit, img);
        moveSpeed = 2;
    }

    @Override
    public void enemyUpdate(List<Entity> entities, long now) {
        Random generator = new Random();
        moving = true;

        int randomDirection;
        if (x % Sprite.DEFAULT_SIZE == 0 && y % Sprite.DEFAULT_SIZE == 0) {
            List<Direction> dirCanMove = canMove(entities);
            if (dirCanMove.size() == 0) {
                randomDirection = direction.getValue();
            }
            else {
                randomDirection = dirCanMove.get(generator.nextInt(dirCanMove.size())).getValue();
                int lastDirection = direction.getValue();
                if (generator.nextInt(100) < 60) {
                    if (dirCanMove.contains(Direction.getDirection(lastDirection))) {
                        randomDirection = lastDirection;
                    }
                }
            }
        } else {
            randomDirection = direction.getValue();
        }

        switch (randomDirection) {
            case 0:
                direction = Direction.LEFT;
                moveSpeedX = -moveSpeed;
                break;
            case 1:
                direction = Direction.RIGHT;
                moveSpeedX = moveSpeed;
                break;
            case 2:
                direction = Direction.UP;
                moveSpeedY = -moveSpeed;
                break;
            case 3:
                direction = Direction.DOWN;
                moveSpeedY = moveSpeed;
                break;
        }
    }

}
