package uet.oop.bomberman.entities.DynamicObject.Movable.Enemy;

import javafx.scene.image.Image;
import javafx.util.Pair;
import uet.oop.bomberman.Support.Direction;
import uet.oop.bomberman.entities.Entity;

import java.util.List;
import java.util.Random;

public class Balloom extends Enemy {
    public Balloom(int xUnit, int yUnit, Image... img) {
        super(xUnit, yUnit, img);
        moveSpeed = 2;
    }

    @Override
    public void enemyUpdate(List<Entity> entities, long now) {
        Random generator = new Random();
        moving = true;
        int randomDirection = generator.nextInt(Direction.numberOfDirection);
        int lastDirection = direction.getValue();
        int lucky = generator.nextInt(10);
        if (lucky < 8) {
            randomDirection = lastDirection;
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
