package uet.oop.bomberman.entities.StaticObject;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;

import java.util.List;

public class Grass extends StaticObject {

    public Grass(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update(List<Entity> entities, long now) {

    }


}
