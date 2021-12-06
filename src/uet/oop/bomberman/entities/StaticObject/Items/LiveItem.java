package uet.oop.bomberman.entities.StaticObject.Items;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.DynamicObject.Movable.Bomber;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.StaticObject.StaticObject;

import java.util.List;

public class LiveItem extends StaticObject {
    public LiveItem(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void update(List<Entity> entities, long now) {
        for (Entity entity : entities) {
            if (entity instanceof Bomber && checkCollision(entity, 0)) {
                entities.remove(this);
                ((Bomber) entity).increaseLive();
                return;
            }
        }
    }
}
