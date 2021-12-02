package uet.oop.bomberman.entities;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import uet.oop.bomberman.entities.DynamicObject.Brick;
import uet.oop.bomberman.entities.StaticObject.Wall;
import uet.oop.bomberman.graphics.Sprite;

import java.util.List;

public abstract class Entity {
    //Tọa độ X tính từ góc trái trên trong Canvas
    protected int x;

    //Tọa độ Y tính từ góc trái trên trong Canvas
    protected int y;

    protected Image img;

    //Khởi tạo đối tượng, chuyển từ tọa độ đơn vị sang tọa độ trong canvas
    public Entity( int xUnit, int yUnit, Image img) {
        this.x = xUnit * Sprite.DEFAULT_SIZE;
        this.y = yUnit * Sprite.DEFAULT_SIZE;
        this.img = img;
    }

    public Entity getAt(int XUnit, int YUnit, List<Entity> entities) {
        for (Entity entity : entities) {
            if (entity.getXUnit() == XUnit && entity.getYUnit() == YUnit) {
                if (entity instanceof Brick || entity instanceof Wall)
                    return entity;
            }
        }
        return null;
    }

    public boolean checkCollision(Entity other) {
        int curLeft = this.x;
        int curRight = curLeft + Sprite.DEFAULT_SIZE - 1;
        int curTop = this.y;
        int curBottom = curTop + Sprite.DEFAULT_SIZE - 1;
        int otherLeft = other.getX();
        int otherRight = otherLeft + Sprite.DEFAULT_SIZE - 1;
        int otherTop = other.getY();
        int otherBottom = otherTop + Sprite.DEFAULT_SIZE - 1;

        return !(curRight < otherLeft || curLeft > otherRight || curBottom < otherTop || curTop > otherBottom);
    }

    public static boolean checkCollision(int curLeft, int curTop, Entity other) {
        int curRight = curLeft + Sprite.DEFAULT_SIZE - 1;
        int curBottom = curTop + Sprite.DEFAULT_SIZE - 1;
        int otherLeft = other.getX();
        int otherRight = otherLeft + Sprite.DEFAULT_SIZE - 1;
        int otherTop = other.getY();
        int otherBottom = otherTop + Sprite.DEFAULT_SIZE - 1;

        return !(curRight < otherLeft || curLeft > otherRight || curBottom < otherTop || curTop > otherBottom);
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(img, x, y);
    }

    public abstract void update(List<Entity> entities, long now);

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getXUnit() { return x / Sprite.DEFAULT_SIZE; }

    public int getYUnit() { return y / Sprite.DEFAULT_SIZE; }
}
