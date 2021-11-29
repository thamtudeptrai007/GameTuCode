package uet.oop.bomberman.entities.DynamicObject.Movable.Enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;

import java.util.List;

public class Enemy_4 extends Enemy {
    public Enemy_4(int xUnit, int yUnit, Image... img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void enemyUpdate(List<Entity> entities, long now) {

    }
}
