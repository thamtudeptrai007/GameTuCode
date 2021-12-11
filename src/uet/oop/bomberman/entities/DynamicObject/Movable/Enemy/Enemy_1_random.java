package uet.oop.bomberman.entities.DynamicObject.Movable.Enemy;

import javafx.scene.image.Image;
import javafx.util.Pair;
import uet.oop.bomberman.Support.Direction;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

import java.util.List;
import java.util.Random;

public class Enemy_1_random extends Enemy {
    public Enemy_1_random(int xUnit, int yUnit, Image... img) {
        super(xUnit, yUnit, img);
        moveSpeed = 1;
        SPF = 0.11;
        score = 1000;
    }

    @Override
    public void enemyUpdate(List<Entity> entities, long now) {
        randomMoving(entities);
    }
}
