package uet.oop.bomberman.entities.DynamicObject.Movable.Enemy;

import javafx.scene.image.Image;
import javafx.util.Pair;
import uet.oop.bomberman.entities.Entity;

import java.util.List;

public class Balloom extends Enemy {
    public Balloom(int xUnit, int yUnit, Image... img) {
        super(xUnit, yUnit, img);
        moveSpeed = 2;
    }

    @Override
    public void enemyUpdate(List<Entity> entities, long now) {

    }

}
