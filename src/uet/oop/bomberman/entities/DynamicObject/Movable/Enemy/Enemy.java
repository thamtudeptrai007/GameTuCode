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

import java.util.ArrayList;
import java.util.List;

public abstract class Enemy extends Movable  {

    protected final int[] listNewXUnit = {-1, 1, 0, 0};
    protected final int[] listNewYUnit = {0, 0, -1, 1};
    protected int score = 100;

    public Enemy(int xUnit, int yUnit, Image... img) {
        super(xUnit, yUnit, img);
        SPF = 0.25;
    }

    @Override
    public Pair<Integer, Integer> trueCoordinates(List<Entity> entities, int newX, int newY) {
        for (Entity entity : entities) {
            if (entity instanceof Brick || entity instanceof Wall || entity instanceof Bomb) {
                if (checkCollision(newX, newY, entity)) {
                    return new Pair<>(x, y);
                }
            }
        }
        return new Pair<>(newX, newY);
    }

    @Override
    public void update(List<Entity> entities, long now) {
        if (!alive) {
            timer += 0.15;
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
        List<Direction> dir = new ArrayList<Direction>();

        for (int i = 0; i < 4; i++) {
            int newXUnit = xUnit + listNewXUnit[i];
            int newYUnit = yUnit + listNewYUnit[i];
            if (getAt(newXUnit, newYUnit, entities) == null)
                dir.add(Direction.getDirection(i));
        }
        return dir;
    }
    public abstract void enemyUpdate(List<Entity> entities, long now);
}
