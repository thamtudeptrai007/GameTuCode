package uet.oop.bomberman.entities.DynamicObject.Movable.Enemy;

import javafx.scene.image.Image;
import javafx.util.Pair;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Map.GameMap;
import uet.oop.bomberman.Support.Direction;
import uet.oop.bomberman.entities.DynamicObject.Bomb.Bomb;
import uet.oop.bomberman.entities.DynamicObject.Brick;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.StaticObject.Wall;
import uet.oop.bomberman.graphics.Sprite;

import java.util.List;

/// Boss xuyen ngang
public class Enemy_5 extends Enemy {
    public Enemy_5(int xUnit, int yUnit, Image... img) {
        super(xUnit, yUnit, img);
        moveSpeed = 3;
    }

    @Override
    public void enemyUpdate(List<Entity> entities, long now) {
        moving = true;
        if (direction == Direction.RIGHT) {
            if (getXUnit() == BombermanGame.WIDTH - 2 || getXUnit() == BombermanGame.WIDTH - 1) {
                direction = Direction.LEFT;
                moveSpeedX = -moveSpeed;
            } else {
                moveSpeedX = moveSpeed;
            }
        } else if (direction == Direction.LEFT) {
            if (getXUnit() == 0) {
                direction = Direction.RIGHT;
                moveSpeedX = moveSpeed;
            } else {
                moveSpeedX = -moveSpeed;
            }
        }
    }

    public Pair<Integer, Integer> trueCoordinates(List<Entity> entities, int newX, int newY) {
        return new Pair<>(newX, newY);
    }
}
