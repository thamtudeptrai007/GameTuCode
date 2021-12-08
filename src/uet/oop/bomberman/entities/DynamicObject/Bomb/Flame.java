package uet.oop.bomberman.entities.DynamicObject.Bomb;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.DynamicObject.DynamicObject;
import uet.oop.bomberman.entities.DynamicObject.Movable.Bomber;
import uet.oop.bomberman.entities.DynamicObject.Movable.Enemy.Enemy_4;
import uet.oop.bomberman.entities.DynamicObject.Movable.Enemy.Enemy_5;
import uet.oop.bomberman.entities.DynamicObject.Movable.Enemy.Enemy_6;
import uet.oop.bomberman.entities.DynamicObject.Movable.Movable;
import uet.oop.bomberman.entities.Entity;

import java.util.List;

public class Flame extends DynamicObject {
    private static final double SPF = 0.15;

    public Flame(int xUnit, int yUnit, Image... img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void update(List<Entity> entities, long now) {
        for (Entity entity : entities) {
            if (entity instanceof Movable && checkCollision(entity, cheatDistance)) {
                if (!(entity instanceof Enemy_5) && !(entity instanceof Enemy_6)) {
                    ((Movable) entity).dead();
                }
            }
        }

        img = animation.get(currentImg);
        timer += SPF;
        currentImg = (int) timer;
        if (currentImg == animation.size()) {
            destroy(entities, now);
        }
    }

    public void destroy(List<Entity> entities, long now) {
        entities.remove(this);
        for (int i = 0; i < entities.size(); i++) {
            if (entities.get(i) instanceof Bomb && checkCollision(entities.get(i), 0)) {
                Bomb bomb = (Bomb) entities.get(i);
                bomb.explode(entities, now);
            }
        }
    }
}
