package uet.oop.bomberman.entities.StaticObject.Items;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.DynamicObject.Movable.Bomber;
import uet.oop.bomberman.entities.DynamicObject.Movable.Enemy.Enemy;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.StaticObject.StaticObject;

import java.util.List;

public class Portal extends StaticObject {
    public Portal(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void update(List<Entity> entities, long now) {
        for (Entity entity : entities) {
            if (entity instanceof Enemy && ((Enemy) entity).isAlive()) {
                return;
            }
        }
        for (Entity entity : entities) {
            if (entity instanceof Bomber && x == entity.getX() && y == entity.getY()) {
                System.out.println("Complete!");
                System.exit(0);
            }
        }
    }
}
