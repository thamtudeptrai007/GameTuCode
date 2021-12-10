package uet.oop.bomberman.entities.StaticObject.Items;

import javafx.scene.image.Image;
import uet.oop.bomberman.Support.Sound;
import uet.oop.bomberman.entities.DynamicObject.Movable.Bomber;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.StaticObject.StaticObject;

import java.util.List;

public class SpeedItem extends StaticObject {
    public static final int increaseSpeed = 1;
    public SpeedItem(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void update(List<Entity> entities, long now) {
        for (Entity entity : entities) {
            if (entity instanceof Bomber && checkCollision(entity, 0)) {
                Sound.playGetNewItem();
                entities.remove(this);
                ((Bomber) entity).increaseSpeed(increaseSpeed);
                return;
            }
        }
    }
}
