package uet.oop.bomberman.entities.DynamicObject.Movable.Enemy;

import javafx.scene.image.Image;
import javafx.util.Pair;
import uet.oop.bomberman.Support.Direction;
import uet.oop.bomberman.Support.Sound;
import uet.oop.bomberman.entities.DynamicObject.Bomb.Bomb;
import uet.oop.bomberman.entities.DynamicObject.Brick;
import uet.oop.bomberman.entities.DynamicObject.Movable.Bomber;
import uet.oop.bomberman.entities.DynamicObject.Movable.Movable;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.StaticObject.Wall;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Enemy extends Movable  {

    protected final int[] listNewXUnit = {-1, 1, 0, 0};
    protected final int[] listNewYUnit = {0, 0, -1, 1};
    protected int score;

    public Enemy(int xUnit, int yUnit, Image... img) {
        super(xUnit, yUnit, img);
        SPF = 0.11;
    }

    @Override
    public Pair<Integer, Integer> trueCoordinates(List<Entity> entities, int newX, int newY) {
        for (Entity entity : entities) {
            if (entity instanceof Brick || entity instanceof Wall || entity instanceof Bomb) {
                if (checkCollision(newX, newY, entity, 1)) {
                    return new Pair<>(x, y);
                }
            }
        }
        return new Pair<>(newX, newY);
    }

    @Override
    public void update(List<Entity> entities, long now) {
        if (!alive) {
            timer += SPF;
            currentImg = (int) (timer >= 0 ? timer : 0) % deadAnimation.size();
            img = deadAnimation.get(currentImg);

            if (currentImg == deadAnimation.size() - 1) {
                entities.remove(this);
                for (Entity entity : entities) {
                    if (entity instanceof Bomber) {
                        ((Bomber) entity).increaseScore(score);
                        break;
                    }
                }
            }
            return;
        }

        enemyUpdate(entities, now);

        if (moving) {
            move(entities);
        }
        timer += SPF;
        currentImg = (int) timer % moveAnimation.get(direction.getValue()).size();
        img = moveAnimation.get(direction.getValue()).get(currentImg);
    }

    public List<Direction> canMove(List<Entity> entities) {
        int xUnit = getXUnit();
        int yUnit = getYUnit();
        List<Direction> dir = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            int newXUnit = xUnit + listNewXUnit[i];
            int newYUnit = yUnit + listNewYUnit[i];
            if (getAt(newXUnit, newYUnit, entities) == null)
                dir.add(Direction.getDirection(i));
        }
        return dir;
    }

    public void randomMoving(List<Entity> entities) {
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

    public abstract void enemyUpdate(List<Entity> entities, long now);
}
