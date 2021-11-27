package uet.oop.bomberman.entities.DynamicObject.Movable.Enemy;

import javafx.scene.image.Image;
import javafx.util.Pair;
import uet.oop.bomberman.entities.DynamicObject.Bomb.Bomb;
import uet.oop.bomberman.entities.DynamicObject.Brick;
import uet.oop.bomberman.entities.DynamicObject.Movable.Movable;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.StaticObject.Wall;

import java.util.List;

public abstract class Enemy extends Movable  {

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
            }
            return;
        }
        if (moving) {
            move(entities);
        }
        timer += SPF;
        currentImg = (int) timer % moveAnimation.get(direction.getValue()).size();
        img = moveAnimation.get(direction.getValue()).get(currentImg);
    }

    public abstract void enemyUpdate(List<Entity> entities, long now);
}
