package uet.oop.bomberman.entities.DynamicObject.Movable.Enemy;

import javafx.scene.image.Image;
import javafx.util.Pair;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Support.Direction;
import uet.oop.bomberman.entities.DynamicObject.Bomb.Bomb;
import uet.oop.bomberman.entities.DynamicObject.Brick;
import uet.oop.bomberman.entities.DynamicObject.Movable.Bomber;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.StaticObject.Wall;
import uet.oop.bomberman.graphics.Sprite;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/// Boss thong minh + tranh bomb
public class Enemy_4 extends Enemy {
    private final boolean[][] visited = new boolean[50][50];
    private final int[][] f = new int[50][50];
    private final Pair<Integer, Integer>[][] last = new Pair[50][50];
    private final int[][] state = new int[50][50];

    public Enemy_4(int xUnit, int yUnit, Image... img) {
        super(xUnit, yUnit, img);
        moveSpeed = 2;
        score = 10000;
    }

    @Override
    public void enemyUpdate(List<Entity> entities, long now) {
        moving = true;
        for (Entity entity : entities) {
            if (entity instanceof Bomber) {
                BFS((Bomber) entity, entities);
                //System.out.printf("%d %d\n", entity.getXUnit(), entity.getYUnit());
                break;
            }
        }
    }

    public void trace(int curXUnit, int curYUnit) {
        while (true) {
            //System.out.printf("%d %d\n", curXUnit, curYUnit);
            state[curXUnit][curYUnit] = 1;
            if (curXUnit == getXUnit() && curYUnit == getYUnit()) {
                break;
            }
            int lastXUnit = last[curXUnit][curYUnit].getKey();
            int lastYUnit = last[curXUnit][curYUnit].getValue();

            curXUnit = lastXUnit;
            curYUnit = lastYUnit;
        }
        if (x % Sprite.DEFAULT_SIZE == 0 && y % Sprite.DEFAULT_SIZE == 0) {
            for (int i = 0; i < 4; i++) {
                int newXUnit = getXUnit() + listNewXUnit[i];
                int newYUnit = getYUnit() + listNewYUnit[i];
                if (state[newXUnit][newYUnit] == 1) {
                    //System.out.printf("%d %d\n", newXUnit, newYUnit);
                    direction = Direction.getDirection(i);
                    state[newXUnit][newYUnit] = 0;
                }
            }
        }

        switch (direction.getValue()) {
            case 0:
                moveSpeedX = -moveSpeed;
                break;
            case 1:
                moveSpeedX = moveSpeed;
                break;
            case 2:
                moveSpeedY = -moveSpeed;
                break;
            case 3:
                moveSpeedY = moveSpeed;
                break;
        }
    }

    public void BFS(Bomber bomber, List<Entity> entities) {
        Queue<Pair<Integer, Integer>> q = new LinkedList<>();
        for (int i = 0; i < BombermanGame.WIDTH; i++) {
            for (int j = 0; j < BombermanGame.HEIGHT; j++) {
                visited[i][j] = false;
                f[i][j] = 0;
                last[i][j] = new Pair<>(0, 0);
                state[i][j] = 0;
            }
        }
        //System.out.printf("%d %d\n",bomber.getXUnit(), bomber.getYUnit());
        q.offer(new Pair<>(getXUnit(), getYUnit()));
        visited[getXUnit()][getYUnit()] = true;
        while (!q.isEmpty()) {
            int curXUnit = q.element().getKey();
            int curYUnit = q.element().getValue();

            if (curXUnit == bomber.getXUnit() && curYUnit == bomber.getYUnit()) {
                trace(curXUnit, curYUnit);
                return;
            }

            q.remove();
            for (int i = 0; i < 4; i++) {
                int newXUnit = curXUnit + listNewXUnit[i];
                int newYUnit = curYUnit + listNewYUnit[i];
                if (getAt(newXUnit, newYUnit, entities) == null) {
                    if (!visited[newXUnit][newYUnit]) {
                        visited[newXUnit][newYUnit] = true;
                        q.offer(new Pair<>(newXUnit, newYUnit));
                        f[newXUnit][newYUnit] = f[curXUnit][curYUnit] + 1;
                        last[newXUnit][newYUnit] = new Pair<>(curXUnit, curYUnit);
                    }
                }
            }
        }
        randomMoving(entities);
    }

    public Entity getAt(int XUnit, int YUnit, List<Entity> entities) {
        for (Entity entity : entities) {
            if (entity.getXUnit() == XUnit && entity.getYUnit() == YUnit) {
                if (entity instanceof Brick || entity instanceof Wall || entity instanceof Bomb)
                    return entity;
            }
        }
        return null;
    }
}
