package uet.oop.bomberman.entities.StaticObject;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;

public abstract class StaticObject extends Entity {

    public StaticObject(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }
}
