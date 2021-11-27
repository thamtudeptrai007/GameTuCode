package uet.oop.bomberman.entities.DynamicObject.Bomb;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.DynamicObject.Brick;
import uet.oop.bomberman.entities.DynamicObject.BrickExplode;
import uet.oop.bomberman.entities.DynamicObject.DynamicObject;
import uet.oop.bomberman.entities.DynamicObject.Movable.Bomber;
import uet.oop.bomberman.entities.DynamicObject.Movable.Enemy.Balloom;
import uet.oop.bomberman.entities.DynamicObject.Movable.Movable;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.StaticObject.Items.FlameItem;
import uet.oop.bomberman.graphics.Animation;

import java.util.List;


public class Bomb extends DynamicObject {
    public static final int timeToExplode = 2;
    private static final double SPF = 0.25;
    private long setupTime;
    private int size;
    private Bomber bomber;

    public Bomb(int xUnit, int yUnit, long setupTime, int size, Bomber bomber, Image... img) {
        super(xUnit, yUnit, img);
        this.setupTime = setupTime;
        this.size = size;
        this.bomber = bomber;
    }

    @Override
    public void update(List<Entity> entities, long now) {
        if ((now - setupTime) / 1000000000 >= timeToExplode) {
            explode(entities, now);
            return;
        }
        img = animation.get(currentImg);
        timer += SPF;
        currentImg = (int) timer % animation.size();
    }

    public void explode(List<Entity> entities, long now) {
        bomber.removeBomb(entities, this);
        int xUnit = getXUnit();
        int yUnit = getYUnit();
        entities.add(new Flame(xUnit, yUnit, Animation.bomb_exploded.getFxImages()));
        flameRight(entities, now);
        flameLeft(entities, now);
        flameTop(entities, now);
        flameDown(entities, now);
    }

    public void flameRight(List<Entity> entities, long now) {
        int xUnit = getXUnit();
        int yUnit = getYUnit();
        for (int i = 1; i <= size; i++) {
            Entity entity = getAt(xUnit + i, yUnit, entities);
            if (entity != null) {
                if (entity instanceof Brick) {
                    entities.add(new BrickExplode(xUnit + i, yUnit, Animation.brick_explode.getFxImages()));
                    entities.remove(entity);
                }
                break;
            }
            if (i < size) {
                entities.add(new Flame(xUnit + i, yUnit, Animation.explosion_horizontal.getFxImages()));
            } else {
                entities.add(new Flame(xUnit + i, yUnit, Animation.explosion_horizontal_right.getFxImages()));
            }
        }
    }

    public void flameLeft(List<Entity> entities, long now) {
        int xUnit = getXUnit();
        int yUnit = getYUnit();
        for (int i = 1; i <= size; i++) {
            Entity entity = getAt(xUnit - i, yUnit, entities);
            if (entity != null) {
                if (entity instanceof Brick) {
                    entities.add(new BrickExplode(xUnit - i, yUnit, Animation.brick_explode.getFxImages()));
                    entities.remove(entity);
                }
                break;
            }
            if (i < size) {
                entities.add(new Flame(xUnit - i, yUnit, Animation.explosion_horizontal.getFxImages()));
            } else {
                entities.add(new Flame(xUnit - i, yUnit, Animation.explosion_horizontal_left.getFxImages()));
            }
        }
    }

    public void flameTop(List<Entity> entities, long now) {
        int xUnit = getXUnit();
        int yUnit = getYUnit();
        for (int i = 1; i <= size; i++) {
            Entity entity = getAt(xUnit, yUnit - i, entities);
            if (entity != null) {
                if (entity instanceof Brick) {
                    entities.add(new BrickExplode(xUnit, yUnit - i, Animation.brick_explode.getFxImages()));
                    entities.remove(entity);
                }
                break;
            }
            if (i < size) {
                entities.add(new Flame(xUnit, yUnit - i, Animation.explosion_vertical.getFxImages()));
            } else {
                entities.add(new Flame(xUnit, yUnit - i, Animation.explosion_vertical_top.getFxImages()));
            }
        }
    }

    public void flameDown(List<Entity> entities, long now) {
        int xUnit = getXUnit();
        int yUnit = getYUnit();
        for (int i = 1; i <= size; i++) {
            Entity entity = getAt(xUnit, yUnit + i, entities);
            if (entity != null) {
                if (entity instanceof Brick) {
                    entities.add(new BrickExplode(xUnit, yUnit + i, Animation.brick_explode.getFxImages()));
                    entities.remove(entity);
                }
                break;
            }
            if (i < size) {
                entities.add(new Flame(xUnit, yUnit + i, Animation.explosion_vertical.getFxImages()));
            } else {
                entities.add(new Flame(xUnit, yUnit + i, Animation.explosion_vertical_down.getFxImages()));
            }
        }
    }
}
