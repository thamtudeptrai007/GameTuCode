package uet.oop.bomberman.entities.StaticObject.Items;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.DynamicObject.Movable.Bomber;
import uet.oop.bomberman.entities.DynamicObject.Movable.Enemy.Enemy;
import uet.oop.bomberman.entities.DynamicObject.Movable.Enemy.Enemy_5;
import uet.oop.bomberman.entities.DynamicObject.Movable.Enemy.Enemy_6;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.StaticObject.StaticObject;

import java.util.List;

public class Portal extends StaticObject {
    private boolean passLevel = false;
    public Portal(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void update(List<Entity> entities, long now) {
        for (Entity entity : entities) {
            if (entity instanceof Enemy && ((Enemy) entity).isAlive()) {
                if (!(entity instanceof Enemy_5) && !(entity instanceof Enemy_6)) {
                    return;
                }
            }
        }
        for (Entity entity : entities) {
            if (entity instanceof Bomber && x == entity.getX() && y == entity.getY()) {
                passLevel = true;
                System.out.println("Complete!");
                System.out.println("Your Score: " + ((Bomber) entity).getScore());
                //System.exit(0);
            }
        }
    }

    public boolean isPassLevel() {
        return passLevel;
    }
}
