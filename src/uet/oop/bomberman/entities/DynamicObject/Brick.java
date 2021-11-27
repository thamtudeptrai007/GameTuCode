package uet.oop.bomberman.entities.DynamicObject;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.StaticObject.StaticObject;

import java.util.List;

public class Brick extends DynamicObject {

    public Brick(int xUnit, int yUnit, Image... img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void update(List<Entity> entities, long now) {

    }
}
