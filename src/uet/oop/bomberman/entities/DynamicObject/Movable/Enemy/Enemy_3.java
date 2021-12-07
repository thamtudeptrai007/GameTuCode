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

import java.util.*;

/// Boss thong minh
public class Enemy_3 extends Enemy {

    private final int distanceToRunAfter = 5;
    private final int speedToRunAfter = 4;
    private final boolean[][] visited = new boolean[50][50];
    private final int[][] f = new int[50][50];
    private final Pair<Integer, Integer>[][] last = new Pair[50][50];
    private final int[][] state = new int[50][50];
    private boolean ok = false;

    public Enemy_3(int xUnit, int yUnit, Image... img) {
        super(xUnit, yUnit, img);
        score = 1000;
    }

    @Override
    public void enemyUpdate(List<Entity> entities, long now) {
        for (Entity entity : entities) {
            if (entity instanceof Bomber) {
                BFS((Bomber) entity, entities);
                break;
            }
        }
        if (!ok) {
            randomMoving(entities);
        }
    }

    public void trace(int curXUnit, int curYUnit) {
        for (int i = 0; i < BombermanGame.WIDTH; i++) {
            for (int j = 0; j < BombermanGame.HEIGHT; j++) {

                state[i][j] = 0;
            }
        }
        while (true) {
            state[curXUnit][curYUnit] = 1;
            if (curXUnit == getXUnit() && curYUnit == getYUnit()) {
                break;
            }
            int lastXUnit = last[curXUnit][curYUnit].getKey();
            int lastYUnit = last[curXUnit][curYUnit].getValue();

            curXUnit = lastXUnit;
            curYUnit = lastYUnit;
        }

        if (x % Sprite.DEFAULT_SIZE == 0  && y % Sprite.DEFAULT_SIZE == 0) {
            for (int i = 0; i < 4; i++) {
                int newXUnit = getXUnit() + listNewXUnit[i];
                int newYUnit = getYUnit() + listNewYUnit[i];
                if (state[newXUnit][newYUnit] == 1) {
                    direction = Direction.getDirection(i);
                    state[newXUnit][newYUnit] = 0;
                }
            }
        }

        int realX = getXUnit() * Sprite.DEFAULT_SIZE;
        int realY = getYUnit() * Sprite.DEFAULT_SIZE;

        //System.out.println(x + " " + y);
        //System.out.println(realX + " " + realY);

        switch (direction.getValue()) {
            case 0:
                moveSpeedX = -moveSpeed;
                int distanceLeft = x - realX;
                if (0 < distanceLeft && distanceLeft < moveSpeed) {
                    moveSpeedX = -distanceLeft;
                }
                break;
            case 1:
                moveSpeedX = moveSpeed;
                int distanceRight = realX + Sprite.DEFAULT_SIZE - x;
                if (0 < distanceRight && distanceRight < moveSpeed) {
                    moveSpeedX = distanceRight;
                }
                break;
            case 2:
                moveSpeedY = -moveSpeed;
                int distanceUp = y - realY;
                if (0 < distanceUp && distanceUp < moveSpeed) {
                    moveSpeedY = -distanceUp;
                }
                break;
            case 3:
                moveSpeedY = moveSpeed;
                int distanceDown = realY + Sprite.DEFAULT_SIZE - y;
                if (0 < distanceDown && distanceDown < moveSpeed) {
                    moveSpeedY = distanceDown;
                }
                break;
        }
        //System.out.println();
    }

    public void BFS(Bomber bomber, List<Entity> entities) {
        Queue <Pair<Integer, Integer>> q = new LinkedList<>();
        for (int i = 0; i < BombermanGame.WIDTH; i++) {
            for (int j = 0; j < BombermanGame.HEIGHT; j++) {
                visited[i][j] = false;
                f[i][j] = 0;
                last[i][j] = new Pair<>(0, 0);
                state[i][j] = 0;
            }
        }

        q.offer(new Pair<>(getXUnit(), getYUnit()));
        visited[getXUnit()][getYUnit()] = true;
        while (!q.isEmpty()) {
            int curXUnit = q.element().getKey();
            int curYUnit = q.element().getValue();

            if (curXUnit == bomber.getXUnit() && curYUnit == bomber.getYUnit()) {
                if (f[curXUnit][curYUnit] <= distanceToRunAfter) {
                    moving = true;
                    ok = true;
                    moveSpeed = speedToRunAfter;
                    trace(curXUnit, curYUnit);
                } else {
                    ok = false;
                }
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
