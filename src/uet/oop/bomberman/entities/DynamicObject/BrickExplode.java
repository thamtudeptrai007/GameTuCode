package uet.oop.bomberman.entities.DynamicObject;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.DynamicObject.Brick;
import uet.oop.bomberman.entities.Entity;

import java.util.List;

public class BrickExplode extends Brick {
    private static final double SPF = 0.25;
    public BrickExplode(int xUnit, int yUnit, Image... img) {
        super(xUnit, yUnit, img);
    }
    @Override
    public void update(List<Entity> entities, long now) {
        img = animation.get(currentImg);
        timer += SPF;
        currentImg = (int) timer;
        if (currentImg == animation.size()) {
            entities.remove(this);
        }
    }
}
