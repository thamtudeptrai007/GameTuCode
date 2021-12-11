package uet.oop.bomberman.entities.DynamicObject.Movable.Enemy;

import javafx.scene.image.Image;
import javafx.util.Pair;
import uet.oop.bomberman.Support.Direction;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

import java.util.List;
import java.util.Random;

public class Enemy_2_random_speed extends Enemy {

    public Enemy_2_random_speed(int xUnit, int yUnit, Image... img) {
        super(xUnit, yUnit, img);
        moveSpeedY = 2;
        SPF = 0.2;
        score = 2000;
    }

    @Override
    public void enemyUpdate(List<Entity> entities, long now) {
        randomMoving(entities);
    }
}
